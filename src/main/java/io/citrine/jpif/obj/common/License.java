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
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Information about a license that applies to some item.
 *
 * <p>Supported fields:
 * <ul>
 *     <li>name - Name of the license.
 *     <li>description - Description of the license.
 *     <li>url - URL to the license.
 *     <li>tags - List of tags that apply to the license.
 * </ul>
 *
 * @author Kyle Michel
 */
public class License extends Pio {

    /**
     * Set the name of the license.
     *
     * @param name String with the name of the license.
     * @return This object.
     */
    @JsonSetter(value = "name")
    public License setName(final String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the name of the license.
     *
     * @return String with the name of the license.
     */
    @JsonGetter(value = "name")
    public String getName() {
        return this.name;
    }

    /**
     * Set the description of the license.
     *
     * @param description String with the description of the license.
     * @return This object.
     */
    @JsonSetter(value = "description")
    public License setDescription(final String description) {
        this.description = description;
        return this;
    }

    /**
     * Get the description of the license.
     *
     * @return String with the description of the license.
     */
    @JsonGetter(value = "description")
    public String getDescription() {
        return this.description;
    }

    /**
     * Set the URL to the license.
     *
     * @param url String with the URL to the license.
     * @return This object.
     */
    @JsonSetter(value = "url")
    public License setUrl(final String url) {
        this.url = url;
        return this;
    }

    /**
     * Get the URL to the license.
     *
     * @return String with the URL to the license.
     */
    @JsonGetter(value = "url")
    public String getUrl() {
        return this.url;
    }

    @Override
    public License addTag(final String tag) {
        super.addTag(tag);
        return this;
    }

    @Override
    public License addTag(final int index, final String tag) {
        super.addTag(index, tag);
        return this;
    }

    @Override
    @JsonAnySetter
    public License addUnsupportedField(final String key, final Object value) {
        super.addUnsupportedField(key, value);
        return this;
    }

    /**
     * Create a new {@link License} object from the input string. This function first checks whether the string is
     * formatted as a URL and saves it as such if so. It then assumes that the string is the name of the license
     * unless it contains more than 50 characters, in which case it assumes a description.
     *
     * @param input String to convert to {@link License} object.
     * @return {@link License} object with the value of the string.
     */
    public static License valueOf(final String input) {
        return (input == null)
                ? null
                : interpretLicenseString(input);
    }

    /**
     * Interpret an input license string as a url, name, or description.
     *
     * @param input String to convert to {@link License} object.
     * @return {@link License} object with the value of the string.
     */
    static License interpretLicenseString(final String input) {
        final License res = new License();
        if (isValidUrl(input)) {
            res.setUrl(input);
        }
        else if (input.length() < 50) {
            res.setName(input);
        }
        else {
            res.setDescription(input);
        }
        return res;
    }

    /**
     * Helper function that determines whether an input string is a valid URL.
     *
     * @param input String to check as a URL.
     * @return True if the input string is a URL.
     */
    static boolean isValidUrl(final String input) {
        // This purposely uses try catch logic to determine whether the input string is a valid url
        try {
            new URL(input);
            return true;
        }
        catch (MalformedURLException e) {
            return false;
        }
    }

    /** Name of the license. */
    private String name;

    /** Description of the license. */
    private String description;

    /** URL to the license. */
    private String url;

    /**
     * Class used to deserialize a JSON value into a {@link License} object. If the input value is a string, this
     * function tries to determine whether it should be marked as a name, description, or URL. If the input value
     * is an object, then it is converted directly to a {@link License} object.
     *
     * @author Kyle Michel
     */
    public static class Deserializer extends JsonDeserializer<License> {

        @Override
        public License deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
                throws IOException {
            final JsonToken jsonToken = jsonParser.getCurrentToken();
            switch (jsonToken) {
                case VALUE_STRING:
                    return License.valueOf(jsonParser.getValueAsString());
                case START_OBJECT:
                    return PifObjectMapper.getInstance().readValue(jsonParser, License.class);
                default:
                    throw deserializationContext.mappingException(License.class, jsonToken);
            }
        }
    }
}