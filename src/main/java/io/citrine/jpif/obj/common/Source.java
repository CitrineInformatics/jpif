package io.citrine.jpif.obj.common;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import io.citrine.jpif.util.PifObjectMapper;
import org.apache.commons.validator.routines.UrlValidator;

import java.io.IOException;

/**
 * Information about the source of a system.
 *
 * <p>Supported fields:
 * <ul>
 *     <li>producer - Name of the producer.
 *     <li>url - URL at which the information about the system can be accessed.
 *     <li>tags - List of tags that apply to the source.
 * </ul>
 *
 * @author Kyle Michel
 */
public class Source extends Pio {

    /**
     * Set the producer of this system.
     *
     * @param producer String with the name of the producer of the system.
     * @return This object.
     */
    @JsonSetter(value = "producer")
    public Source setProducer(final String producer) {
        this.producer = producer;
        return this;
    }

    /**
     * Get the producer for this system.
     *
     * @return String with the name of the producer for this system.
     */
    @JsonGetter(value = "producer")
    public String getProducer() {
        return this.producer;
    }

    /**
     * Set the URL of this system.
     *
     * @param url String with the URL of the system.
     * @return This object.
     */
    @JsonSetter(value = "url")
    public Source setUrl(final String url) {
        this.url = url;
        return this;
    }

    /**
     * Get the URL for this system.
     *
     * @return String with the URL for this system.
     */
    @JsonGetter(value = "url")
    public String getUrl() {
        return this.url;
    }

    @Override
    public Source addTag(final String tag) {
        super.addTag(tag);
        return this;
    }

    @Override
    public Source addTag(final int index, final String tag) {
        super.addTag(index, tag);
        return this;
    }

    @Override
    @JsonAnySetter
    public Source addUnsupportedField(final String key, final Object value) {
        super.addUnsupportedField(key, value);
        return this;
    }

    /**
     * Generate a new {@link Source} object from the input string. If the string contains a valid URL, then it is
     * assigned to the resulting object. Otherwise the string is assumed to be a producer.
     *
     * @param source String with the value to convert.
     * @return {@link Source} object.
     */
    public static Source valueOf(final String source) {
        return isUrl(source)
                ? new Source().setUrl(source)
                : new Source().setProducer(source);
    }

    /**
     * Determine whether the input string is a URL.
     *
     * @param source String to check as a URL.
     * @return True if the string is a URL.
     */
    private static boolean isUrl(final String source) {
        return (source != null) && UrlValidator.getInstance().isValid(source.trim());
    }

    /** Producer of the system. */
    private String producer;

    /** Url for the system. */
    private String url;

    /**
     * Class used to deserialize a JSON value into a {@link Source} object. If the input token is a string then an
     * attempt is made to determine the information that it contains. If the input token is an object, then it is
     * converted directly to a {@link Source} object.
     *
     * @author Kyle Michel
     */
    public static class Deserializer extends JsonDeserializer<Source> {

        @Override
        public Source deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
                throws IOException {
            final JsonToken jsonToken = jsonParser.getCurrentToken();
            switch (jsonToken) {
                case VALUE_STRING:
                    return Source.valueOf(jsonParser.getValueAsString());
                case START_OBJECT:
                    return PifObjectMapper.getInstance().readValue(jsonParser, Source.class);
                default:
                    throw deserializationContext.mappingException(Source.class, jsonToken);
            }
        }
    }
}