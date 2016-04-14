package io.citrine.jpif.io;

import io.citrine.jpif.obj.system.System;
import io.citrine.jpif.util.PifObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

/**
 * Class to create an {@link InputStream} from one or more PIF objects.
 *
 * @author Kyle Michel
 */
public class PifInputStream extends InputStream {

    /**
     * Constructor for a single PIF system.
     *
     * @param system {@link System} to iterate over.
     */
    public PifInputStream(final System system) {
        initialize();
        this.systemIterator = new SystemListIterator(system);
    }

    /**
     * Constructor for a list of PIF systems.
     *
     * @param systems List of {@link System}s to iterate over.
     */
    public PifInputStream(final List<System> systems) {
        initialize();
        this.systemIterator = new SystemListIterator(systems);
    }

    /**
     * Constructor for a stream of PIF objects.
     *
     * @param pifObjectStream {@link PifObjectStream} to iterate over.
     */
    public PifInputStream(final PifObjectStream pifObjectStream) {
        initialize();
        this.systemIterator = new PifObjectStreamIterator(pifObjectStream);
    }

    @Override
    public int read() throws IOException {
        return (this.index < this.byteArray.length)
                ? this.byteArray[this.index++]
                : getFirstByteFromNextArray();
    }

    /**
     * Move to the next byte array and return the first character.
     *
     * @return Int with the first character of the next byte array or -1 if finished.
     * @throws IOException if thrown from within this function.
     */
    private int getFirstByteFromNextArray() throws IOException {
        return this.finished
                ? -1
                : readNextByteArray();
    }

    /**
     * Read the next byte array and return the first character.
     *
     * @return Int with the first character of the next byte array.
     * @throws IOException if thrown from within this function.
     */
    private int readNextByteArray() throws IOException {
        final System nextSystem = this.systemIterator.getNextSystem();
        return (nextSystem == null)
                ? getFromEndOfStream()
                : getFromSystem(nextSystem);
    }

    /**
     * Get the first byte from the end of a stream.
     *
     * @return Int with the first character of the next byte array.
     */
    private int getFromEndOfStream() {
        if (this.finished) {
            return -1;
        }
        else {
            this.index = 0;
            this.finished = true;
            this.byteArray = "]".getBytes();
            return this.byteArray[this.index++];
        }
    }

    /**
     * Get the next byte from the input system.
     *
     * @param system {@link System} to convert to a byte array.
     * @return Int with the first character of the next byte array.
     * @throws IOException if thrown from within this function.
     */
    private int getFromSystem(final System system) throws IOException {
        final String systemString = this.first
                ? PifObjectMapper.getInstance().writeValueAsString(system)
                : "," + PifObjectMapper.getInstance().writeValueAsString(system);
        this.index = 0;
        this.first = false;
        this.byteArray = systemString.getBytes();
        return this.byteArray[this.index++];
    }

    /**
     * Initialize the variables in this object.
     */
    private void initialize() {
        this.index = 0;
        this.first = true;
        this.finished = false;
        this.byteArray = "[".getBytes();
    }

    @Override
    public void close() throws IOException {
        this.systemIterator.close();
    }

    /** Iterable of PIF systems. */
    private final SystemIterator systemIterator;

    /** Whether the input iterator is finished. */
    private boolean finished;

    /** Whether this is the first object being read. */
    private boolean first;

    /** Current position in the byte array. */
    private int index;

    /** Current byte array. */
    private byte[] byteArray;

    /**
     * Base class for iterators over PIF systems.
     *
     * @author Kyle Michel
     */
    private abstract static class SystemIterator {

        /**
         * Get the next system.
         *
         * @return Next {@link System} in the iterable or a null pointer if none remain.
         * @throws IOException if thrown from within this function.
         */
        abstract System getNextSystem() throws IOException;

        /**
         * Close the iterator.
         *
         * @throws IOException if thrown from within this function.
         */
        void close() throws IOException {}
    }

    /**
     * Class for iterating over a list of PIF system objects.
     *
     * @author Kyle Michel
     */
    private static class SystemListIterator extends SystemIterator {

        /**
         * Constructor.
         *
         * @param system {@link System} to iterate over.
         */
        SystemListIterator(final System system) {
            this.index = 0;
            this.systems = Collections.singletonList(system);
        }

        /**
         * Constructor.
         *
         * @param systems List of {@link System} objects to iterate over.
         */
        SystemListIterator(final List<System> systems) {
            this.index = 0;
            this.systems = systems;
        }

        @Override
        System getNextSystem() throws IOException {
            return (this.index >= this.systems.size())
                    ? null
                    : this.systems.get(this.index++);
        }

        /** Current position in the systems. */
        private int index;

        /** List of PIF systems. */
        private final List<System> systems;
    }

    /**
     * Class for iterator over a {@link PifObjectStream}.
     *
     * @author Kyle Michel
     */
    private static class PifObjectStreamIterator extends SystemIterator {

        /**
         * Constructor.
         *
         * @param pifObjectStream {@link PifObjectStream} to iterate over.
         */
        PifObjectStreamIterator(final PifObjectStream pifObjectStream) {
            this.pifObjectStream = pifObjectStream;
        }

        @Override
        System getNextSystem() throws IOException {
            return this.pifObjectStream.getNextSystem();
        }

        @Override
        void close() throws IOException {
            this.pifObjectStream.close();
        }

        /** Pif object stream being iterated over. */
        private final PifObjectStream pifObjectStream;
    }
}