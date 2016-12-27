package io.citrine.jpif.io;

import io.citrine.jpif.obj.system.System;

import java.io.IOException;
import java.util.Iterator;

/**
 * Class to stream PIF systems from some source that is formatted in the PIF schema.
 *
 * <p>Usage: Create an instance of this class using one of the factory methods. Use one of {@link #getNextSystem()} or
 * {@link #getNextSystem(Class)} to iterate through the systems in the data source:
 *
 * <pre>
 * {@code
 * PifSystemStream pifSystemStream = new JsonDeserializingPifSystemStream(inputStream);
 * ChemicalSystem chemicalSystem;
 * while ((chemicalSystem = pifSystemStream.getNextSystem(ChemicalSystem.class)) != null) {
 *      // do work on chemical system
 * }
 * pifSystemStream.close();
 * }
 * </pre>
 *
 * <p>Alternatively, an object of this class type can be created and directly iterated on:
 *
 * <pre>
 * {@code
 * PifSystemStream pifSystemStream = new JsonDeserializingPifSystemStream(inputStream);
 * for (System system : pifSystemStream) {
 *     // do work on system
 * }
 * pifSystemStream.close();
 * }
 * </pre>
 *
 * @author Kyle Michel
 */
public abstract class PifSystemStream implements Iterable<System> {

    /**
     * Get the next {@link System} in this stream.
     *
     * @return Next {@link System} object in the stream or a null pointer if the end of the stream has been reached.
     * @throws IOException if the stream cannot be processed.
     */
    public System getNextSystem() throws IOException {
        return getNextSystem(System.class);
    }

    /**
     * Get the next object derived from {@link System} in this stream. This function only returns objects of input
     * class or one of its derived classes, and ignore all other systems in the stream.
     *
     * @param systemClass Class of the system to return.
     * @param <T> Type of the class to return.
     * @return Next object of type T in the stream or a null pointer if the end of the stream has been reached.
     * @throws IOException if the stream cannot be processed.
     */
    public <T extends System> T getNextSystem(final Class<T> systemClass) throws IOException {
        return isFinished() ? null : advanceToNextSystem(systemClass);
    }

    /**
     * Move to the next object derived from {@link System} in this stream.
     *
     * @param systemClass Class of the system to return.
     * @param <T> Type of the class to return.
     * @return Next object of type T in the stream or a null pointer if the end of the stream has been reached.
     * @throws IOException if the stream cannot be processed.
     */
    @SuppressWarnings("unchecked")
    protected <T extends System> T advanceToNextSystem(final Class<T> systemClass) throws IOException {
        System currentSystem;
        while ((currentSystem = advanceToNextSystem()) != null) {
            if (systemClass.isAssignableFrom(currentSystem.getClass())) {
                return (T) currentSystem;
            }
        }
        return null;
    }

    /**
     * Get the next system of any type in this stream.
     *
     * @return Next system in the stream or a null pointer if the end of the has been reached.
     * @throws IOException if the stream cannot be processed.
     */
    protected abstract System advanceToNextSystem() throws IOException;

    /**
     * Return whether the end of the stream has been reached based on the value of the current token.
     *
     * @return True if the end of the stream has been reached.
     */
    protected abstract boolean isFinished();

    /**
     * Close this system stream. This closes any of the streams or readers from which this object was created.
     *
     * @throws IOException if this object cannot be closed. This could occur, for example, if the underlying streams
     *      or readers throw an exception when they are closed.
     */
    public abstract void close() throws IOException;

    /**
     * Get an iterator for the stream. This iterator just reads from the stream underlying this object and will read
     * from the current system. This object must still be closed using the {@link #close()} method.
     *
     * @return Iterator for this stream.
     */
    @Override
    public Iterator<System> iterator() {
        return this.new SystemIterator();
    }

    /**
     * Iterator class for iterating over systems.
     *
     * @author Kyle Michel
     */
    public class SystemIterator implements Iterator<System> {

        /**
         * Return whether there is another item to iterate over.
         *
         * @return True if another item can be obtained.
         * @throws RuntimeException if an {@link IOException} is thrown from within this function.
         */
        @Override
        public boolean hasNext() {
            if (this.nextSystem == null) {
                this.nextSystem = getNextSystem();
            }
            return this.nextSystem != null;
        }

        /**
         * Get the next object.
         *
         * @return {@link System} object.
         * @throws RuntimeException if an {@link IOException} is thrown from within this function.
         */
        @Override
        public System next() {
            if (this.nextSystem != null) {
                System res = this.nextSystem;
                this.nextSystem = null;
                return res;
            }
            else {
                return getNextSystem();
            }
        }

        /**
         * Try to read the next system.
         *
         * @return Next {@link System} in the stream.
         * @throws RuntimeException if the stream cannot be read.
         */
        private System getNextSystem() {
            try {
                return PifSystemStream.this.getNextSystem();
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        /** Next system in the stream. */
        private System nextSystem;
    }
}