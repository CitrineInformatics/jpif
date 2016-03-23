package io.citrine.jpif.obj.common;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import io.citrine.jpif.util.PifObjectMapper;

import java.io.IOException;

/**
 * Information about a generic identifier.
 * <p>
 * Supported fields:
 * <ul>
 *     <li>name - Name of the identifier.
 *     <li>value - Value of the identifier.
 * </ul>
 *
 * @author Kyle Michel
 */
public class Id extends Pio {

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