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
import java.util.regex.Pattern;

/**
 * Representation of the starting and ending pages of a reference.
 *
 * @author Kyle Michel
 */
public class Pages extends Pio<Pages> {

    /**
     * Set the starting page.
     *
     * @param start String with the starting page.
     * @return This object.
     */
    @JsonSetter(value = "start")
    public Pages setStart(final String start) {
        this.start = start;
        return this;
    }

    /**
     * Get the starting page.
     *
     * @return String with the starting page.
     */
    @JsonGetter(value = "start")
    public String getStart() {
        return this.start;
    }

    /**
     * Set the ending page.
     *
     * @param end String with the ending page.
     * @return This object.
     */
    @JsonSetter(value = "end")
    public Pages setEnd(final String end) {
        this.end = end;
        return this;
    }

    /**
     * Get the ending page.
     *
     * @return String with the ending page.
     */
    @JsonGetter(value = "end")
    public String getEnd() {
        return this.end;
    }

    /**
     * Generate a {@link Pages} object from an input integer. This function assumes that the number represents the
     * first page.
     *
     * @param input Integer representing the first page.
     * @return New {@link Pages} object with the input number as the first page.
     */
    public static Pages valueOf(final Integer input) {
        return (input == null)
                ? null
                : new Pages().setStart(input.toString());
    }

    /**
     * Generate a {@link Pages} object from an input string. This function tries to break the input by spaces or
     * hyphens to determine start and end pages. If only a single page is found, it is assumed as the start page.
     *
     * @param input String to break into start and end pages.
     * @return New {@link Pages} object with the input string broken into components.
     */
    public static Pages valueOf(final String input) {
        return (input == null)
                ? null
                : decomposePages(input);
    }

    /**
     * Decompose the input string into start and end pages.
     *
     * @param input String to break into pages.
     * @return New {@link Pages} object with the input string broken into components.
     */
    protected static Pages decomposePages(final String input) {
        final Pages res = new Pages();
        final String[] parts = SPLIT_REGEX.split(input.trim());
        if (parts.length > 0) {
            res.setStart(parts[0]);
        }
        if (parts.length > 1) {
            res.setEnd(parts[1]);
        }
        return res;
    }

    /** Starting page. */
    private String start;

    /** Ending page. */
    private String end;

    /** Regular expression to split pages. */
    private static final Pattern SPLIT_REGEX = Pattern.compile("[\\s\\-]+");

    /**
     * Class used to deserialize a JSON value into a {@link Pages} object. If the input token is a string then an
     * attempt is made to decompose it into start and end pages. If the input token is an object, then it is
     * converted directly to a {@link Pages} object.
     *
     * @author Kyle Michel
     */
    public static class Deserializer extends JsonDeserializer<Pages> {

        @Override
        public Pages deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
                throws IOException {
            final JsonToken jsonToken = jsonParser.getCurrentToken();
            switch (jsonToken) {
                case VALUE_NUMBER_INT:
                    return Pages.valueOf(jsonParser.getIntValue());
                case VALUE_STRING:
                    return Pages.valueOf(jsonParser.getValueAsString());
                case START_OBJECT:
                    return PifObjectMapper.getInstance().readValue(jsonParser, Pages.class);
                default:
                    throw deserializationContext.mappingException(Pages.class, jsonToken);
            }
        }
    }
}