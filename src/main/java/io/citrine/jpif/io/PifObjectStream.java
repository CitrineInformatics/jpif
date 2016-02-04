package io.citrine.jpif.io;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import io.citrine.jpif.obj.system.System;
import io.citrine.jpif.obj.system.chemical.ChemicalSystem;
import io.citrine.jpif.obj.system.chemical.alloy.Alloy;
import io.citrine.jpif.obj.system.chemical.alloy.AlloyPhase;
import io.citrine.jpif.util.PifObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

/**
 * Class to stream PIF objects from some source that is formatted in the PIF schema.
 *
 * <p>Usage: Create an instance of this class using one of the constructors. Use one of {@link #getNextSystem()} or
 * similar method to iterate through the objects in the data source. Call {@link #close()} when finished.
 *
 * @author Kyle Michel
 */
public class PifObjectStream {

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
     * {@link #PifObjectStream(InputStream, String)} to change the encoding to use.
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
        return isFinished() ? null : advanceToNextSystem();
    }

    /**
     * Move to the next {@link System} in this stream.
     *
     * @return Next {@link System} object in the stream or a null pointer if the end of the stream has been reached.
     * @throws IOException if the stream cannot be processed.
     */
    private System advanceToNextSystem() throws IOException {
        return PifObjectMapper.getInstance().readValue(this.jsonParser, System.class);
    }

    /**
     * Get the next {@link ChemicalSystem} in this stream. This ignores any records that are not of type
     * {@link ChemicalSystem} or one of its subclasses.
     *
     * @return Next {@link ChemicalSystem} object in the stream or a null pointer if the end of the stream
     *      has been reached.
     * @throws IOException if the stream cannot be processed.
     */
    public ChemicalSystem getNextChemicalSystem() throws IOException {
        return isFinished() ? null : advanceToNextChemicalSystem();
    }

    /**
     * Move to the next {@link ChemicalSystem} in this stream.
     *
     * @return Next {@link ChemicalSystem} object in the stream or a null pointer if the end of the stream
     *      has been reached.
     * @throws IOException if the stream cannot be processed.
     */
    private ChemicalSystem advanceToNextChemicalSystem() throws IOException {
        System currentSystem;
        while ((currentSystem = PifObjectMapper.getInstance().readValue(this.jsonParser, System.class)) != null) {
            if (currentSystem instanceof ChemicalSystem) {
                return (ChemicalSystem) currentSystem;
            }
        }
        return null;
    }

    /**
     * Get the next {@link Alloy} in this stream. This ignores any records that are not of type {@link Alloy} or one
     * of its subclasses.
     *
     * @return Next {@link Alloy} object in the stream or a null pointer if the end of the stream has been reached.
     * @throws IOException if the stream cannot be processed.
     */
    public Alloy getNextAlloy() throws IOException {
        return isFinished() ? null : advanceToNextAlloy();
    }

    /**
     * Move to the next {@link Alloy} in this stream.
     *
     * @return Next {@link Alloy} object in the stream or a null pointer if the end of the stream has been reached.
     * @throws IOException if the stream cannot be processed.
     */
    private Alloy advanceToNextAlloy() throws IOException {
        System currentSystem;
        while ((currentSystem = PifObjectMapper.getInstance().readValue(this.jsonParser, System.class)) != null) {
            if (currentSystem instanceof Alloy) {
                return (Alloy) currentSystem;
            }
        }
        return null;
    }

    /**
     * Get the next {@link AlloyPhase} in this stream. This ignores any records that are not of type
     * {@link AlloyPhase} or one of its subclasses.
     *
     * @return Next {@link AlloyPhase} object in the stream or a null pointer if the end of the stream has been reached.
     * @throws IOException if the stream cannot be processed.
     */
    public AlloyPhase getNextAlloyPhase() throws IOException {
        return isFinished() ? null : advanceToNextAlloyPhase();
    }

    /**
     * Move to the next {@link AlloyPhase} in this stream.
     *
     * @return Next {@link AlloyPhase} object in the stream or a null pointer if the end of the stream has been reached.
     * @throws IOException if the stream cannot be processed.
     */
    private AlloyPhase advanceToNextAlloyPhase() throws IOException {
        System currentSystem;
        while ((currentSystem = PifObjectMapper.getInstance().readValue(this.jsonParser, System.class)) != null) {
            if (currentSystem instanceof AlloyPhase) {
                return (AlloyPhase) currentSystem;
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
    private void advanceToFirstObject() throws IOException {
        this.currentToken = this.jsonParser.nextToken();
        if (this.currentToken == JsonToken.START_ARRAY) {
            this.currentToken = this.jsonParser.nextToken();
        }
    }

    /**
     * Return whether the end of the stream has been reached based on the value of the current token.
     *
     * @return True if the end of the stream has been reached.
     */
    private boolean isFinished() {
        return (this.currentToken == JsonToken.END_ARRAY) || (this.currentToken == null);
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

    /** Json parser to read a PIF-formatted JSON source. */
    private final JsonParser jsonParser;

    /** Current token. */
    private JsonToken currentToken;
}