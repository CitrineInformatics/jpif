package io.citrine.jpif.io;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import io.citrine.jpif.obj.system.System;
import io.citrine.jpif.util.PifObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * Class to stream PIF objects from some source that is formatted in the PIF schema.
 *
 * <p>Usage: Create an instance of this class using one of the constructors. Use one of {@link #getNextSystem()} or
 * {@link #getNextSystem(Class)} to iterate through the objects in the data source:
 *
 * <pre>
 * {@code
 * PifObjectStream pifObjectStream = new PifObjectStream(inputStream);
 * ChemicalSystem chemicalSystem;
 * while ((chemicalSystem = pifObjectStream.getNextSystem(ChemicalSystem.class)) != null) {
 *      // do work on chemical system
 * }
 * pifObjectStream.close();
 * }
 * </pre>
 *
 * <p>Alternatively, an object of this class type can be created and directly iterated on:
 *
 * <pre>
 * {@code
 * PifObjectStream pifObjectStream = new PifObjectStream(inputStream);
 * for (System system : pifObjectStream) {
 *     // do work on system
 * }
 * pifObjectStream.close();
 * }
 * </pre>
 *
 * @author Kyle Michel
 */
public class PifObjectStream implements Iterable<System> {

    /**
     * Create an object stream from a string.
     *
     * @param string String to convert into PIF objects.
     * @throws IOException if the input string cannot be parsed.
     */
    public PifObjectStream(final String string) throws IOException {
        this.jsonParser = new JsonFactory().createParser(string);
        advanceToFirstObject();
    }

    /**
     * Create an object stream from a {@link Reader} object.
     *
     * <p>The input reader will be closed when the {@link #close()} method is called on this object.
     *
     * @param reader {@link Reader} object with information to convert into PIF objects.
     * @throws IOException if the input reader cannot be parsed.
     */
    public PifObjectStream(final Reader reader) throws IOException {
        this.jsonParser = new JsonFactory().createParser(reader);
        advanceToFirstObject();
    }

    /**
     * Create an object stream from an {@link InputStream} object. This function assumes UTF-8 encoding. Use
     * {@link #PifObjectStream(InputStream, String)} to change the character set.
     *
     * <p>The input stream will be closed when the {@link #close()} method is called on this object.
     *
     * @param inputStream {@link InputStream} object with information to convert into PIF objects.
     * @throws IOException if the input stream cannot be parsed.
     */
    public PifObjectStream(final InputStream inputStream) throws IOException {
        this(inputStream, "UTF-8");
    }

    /**
     * Create an object stream from an {@link InputStream} object.
     *
     * <p>The input stream will be closed when the {@link #close()} method is called on this object.
     *
     * @param inputStream {@link InputStream} object with information to convert into PIF objects.
     * @param charsetName String with the name of the charset to use (see {@link Charset}).
     * @throws IOException if the input stream cannot be parsed.
     */
    public PifObjectStream(final InputStream inputStream, final String charsetName) throws IOException {
        this.jsonParser = new JsonFactory().createParser(new InputStreamReader(inputStream, charsetName));
        advanceToFirstObject();
    }

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
    <T extends System> T advanceToNextSystem(final Class<T> systemClass) throws IOException {
        System currentSystem;
        while ((currentSystem = PifObjectMapper.getInstance().readValue(this.jsonParser, System.class)) != null) {
            this.jsonParser.nextToken();
            if (systemClass.isAssignableFrom(currentSystem.getClass())) {
                return (T) currentSystem;
            }
            if (isFinished()) {
                break;
            }
        }
        return null;
    }

    /**
     * Advance to the first object in the parser. This should only be called from a constructor, and only be
     * called one time.
     *
     * @throws IOException if the underlying stream cannot be parsed.
     */
    void advanceToFirstObject() throws IOException {
        this.jsonParser.nextToken();
        if (this.jsonParser.getCurrentToken() == JsonToken.START_ARRAY) {
            this.jsonParser.nextToken();
        }
    }

    /**
     * Return whether the end of the stream has been reached based on the value of the current token.
     *
     * @return True if the end of the stream has been reached.
     */
    boolean isFinished() {
        return (this.jsonParser.getCurrentToken() == JsonToken.END_ARRAY)
                || (this.jsonParser.getCurrentToken() == null);
    }

    /**
     * Close this object stream. This closes any of the streams or readers from which this object was created.
     *
     * @throws IOException if this object cannot be closed. This could occur, for example, if the underlying streams
     *      or readers throw an exception when they are closed.
     */
    public void close() throws IOException {
        this.jsonParser.close();
    }

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
     * Default constructor. This lets subclasses exists without having to implement one of the constructors 
     * of this class.
     */
    PifObjectStream() {
        this.jsonParser = null;
    }

    /** Json parser to read a PIF-formatted JSON source. */
    final JsonParser jsonParser;

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
        System getNextSystem() {
            try {
                return PifObjectStream.this.getNextSystem();
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        /** Next system in the stream. */
        System nextSystem;
    }
}