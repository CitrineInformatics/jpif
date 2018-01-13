package io.citrine.jpif.obj.common;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import io.citrine.jpif.util.PifObjectMapper;
import io.citrine.jpif.util.PifSerializationUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * Information about a generic identifier.
 *
 * <p>Supported fields:
 * <ul>
 *     <li>name - Name of the identifier.
 *     <li>value - Value of the identifier.
 *     <li>tags - List of tags that apply to the identifier.
 * </ul>
 *
 * @author Kyle Michel
 */
public class Id extends Pio implements Serializable {

    /**
     * Set the name of the identifier.
     *
     * @param name String with the name of the identifier.
     * @return This object.
     */
    @JsonSetter(value = "name")
    public Id setName(final String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the name of the identifier.
     *
     * @return String with the name of the identifier.
     */
    @JsonGetter(value = "name")
    public String getName() {
        return this.name;
    }

    /**
     * Set the value of the identifier.
     *
     * @param value String with the value of the identifier.
     * @return This object.
     */
    @JsonSetter(value = "value")
    public Id setValue(final String value) {
        this.value = value;
        return this;
    }

    /**
     * Get the value of the identifier.
     *
     * @return String with the value of the identifier.
     */
    @JsonGetter(value = "value")
    public String getValue() {
        return this.value;
    }

    @Override
    public Id addTag(final String tag) {
        super.addTag(tag);
        return this;
    }

    @Override
    public Id addTag(final int index, final String tag) {
        super.addTag(index, tag);
        return this;
    }

    @Override
    @JsonAnySetter
    public Id addUnsupportedField(final String key, final Object value) {
        super.addUnsupportedField(key, value);
        return this;
    }

    /**
     * Create a new {@link Id} object from a string. This just sets the value of the Id to the input string and leaves
     * the name as empty.
     *
     * @param input String with the input to save.
     * @return New {@link Id} object with the input string as the value.
     */
    public static Id valueOf(final String input) {
        return (input == null)
                ? null
                : new Id().setValue(input);
    }

    /**
     * Create a new {@link Id} object from a number. This just sets the value of the Id to the input number and leaves
     * the name as empty.
     *
     * @param input Number with the input to save.
     * @return New {@link Id} object with the input number as the value.
     */
    public static Id valueOf(final Number input) {
        return (input == null)
                ? null
                : new Id().setValue(input.toString());
    }

    /**
     * Write this object to the output output stream.
     *
     * @param out {@link ObjectOutputStream} to write to.
     * @throws IOException if this object cannot be written.
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        PifSerializationUtil.write(out, this);
    }

    /**
     * Read into this object from the input stream.
     *
     * @param in {@link ObjectInputStream} to read from.
     * @throws IOException if thrown while reading the stream.
     * @throws ClassNotFoundException if thrown while reading the stream.
     */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        PifSerializationUtil.read(in, this);
    }

    /**
     * Read an object with no data.
     *
     * @throws ObjectStreamException if thrown while reading the stream.
     */
    private void readObjectNoData() throws ObjectStreamException {}

    private static final long serialVersionUID = 6536471749646038577L;

    /** Name of the identifier. */
    private String name;

    /** Value of the identifier. */
    private String value;

    /**
     * Class used to deserialize a JSON value into an {@link Id} object. If the input token is a string or number
     * then it is saved as the value of the Id object. If the input token is an object, then it is converted directly
     * to an {@link Id} object.
     *
     * @author Kyle Michel
     */
    public static class Deserializer extends JsonDeserializer<Id> {

        @Override
        public Id deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            final JsonToken jsonToken = jsonParser.getCurrentToken();
            switch (jsonToken) {
                case VALUE_STRING:
                    return Id.valueOf(jsonParser.getValueAsString());
                case VALUE_NUMBER_INT:
                case VALUE_NUMBER_FLOAT:
                    return Id.valueOf(jsonParser.getNumberValue());
                case START_OBJECT:
                    return PifObjectMapper.getInstance().readValue(jsonParser, Id.class);
                default:
                    throw deserializationContext.mappingException(Id.class, jsonToken);
            }
        }
    }
}