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

/**
 * Class that streams a serialized JSON source and converts to PIF systems.
 *
 * @author Kyle Michel
 */
public class JsonDeserializingPifSystemStream extends PifSystemStream {

    /**
     * Create an system stream from a string.
     *
     * @param string String to convert into PIF systems.
     * @throws IOException if the input string cannot be parsed.
     */
    public JsonDeserializingPifSystemStream(final String string) throws IOException {
        this.jsonParser = new JsonFactory().createParser(string);
        advanceToFirstSystem();
    }

    /**
     * Create a system stream from a {@link Reader} object.
     *
     * <p>The input reader will be closed when the {@link #close()} method is called on this object.
     *
     * @param reader {@link Reader} object with information to convert into PIF systems.
     * @throws IOException if the input reader cannot be parsed.
     */
    public JsonDeserializingPifSystemStream(final Reader reader) throws IOException {
        this.jsonParser = new JsonFactory().createParser(reader);
        advanceToFirstSystem();
    }

    /**
     * Create a system stream from an {@link InputStream} object. This function assumes UTF-8 encoding. Use
     * {@link #JsonDeserializingPifSystemStream(InputStream, String)} to change the character set.
     *
     * <p>The input stream will be closed when the {@link #close()} method is called on this object.
     *
     * @param inputStream {@link InputStream} object with information to convert into PIF systems.
     * @throws IOException if the input stream cannot be parsed.
     */
    public JsonDeserializingPifSystemStream(final InputStream inputStream) throws IOException {
        this(inputStream, "UTF-8");
    }

    /**
     * Create a system stream from an {@link InputStream} object.
     *
     * <p>The input stream will be closed when the {@link #close()} method is called on this object.
     *
     * @param inputStream {@link InputStream} object with information to convert into PIF systems.
     * @param charsetName String with the name of the charset to use (see {@link Charset}).
     * @throws IOException if the input stream cannot be parsed.
     */
    public JsonDeserializingPifSystemStream(final InputStream inputStream, final String charsetName)
            throws IOException {
        this.jsonParser = new JsonFactory().createParser(new InputStreamReader(inputStream, charsetName));
        advanceToFirstSystem();
    }

    @Override
    protected System advanceToNextSystem() throws IOException {
        final System result = PifObjectMapper.getInstance().readValue(this.jsonParser, System.class);
        if (result != null) {
            this.jsonParser.nextToken();
        }
        return result;
    }

    /**
     * Advance to the first system in the parser. This should only be called from a constructor, and only be
     * called one time.
     *
     * @throws IOException if the underlying stream cannot be parsed.
     */
    protected void advanceToFirstSystem() throws IOException {
        this.jsonParser.nextToken();
        if (this.jsonParser.getCurrentToken() == JsonToken.START_ARRAY) {
            this.jsonParser.nextToken();
        }
    }

    @Override
    protected boolean isFinished() {
        return (this.jsonParser.getCurrentToken() == JsonToken.END_ARRAY)
                || (this.jsonParser.getCurrentToken() == null);
    }

    @Override
    public void close() throws IOException {
        if (this.jsonParser != null) {
            this.jsonParser.close();
        }
    }

    /** Json parser to read a PIF-formatted JSON source. */
    protected final JsonParser jsonParser;
}