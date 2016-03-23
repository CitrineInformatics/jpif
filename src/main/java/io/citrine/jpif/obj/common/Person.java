package io.citrine.jpif.obj.common;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.citrine.jpif.util.Orcid;
import io.citrine.jpif.util.PifObjectMapper;
import org.apache.commons.validator.routines.EmailValidator;

import java.io.IOException;

/**
 * Information about a person.
 *
 * <p>Supported fields:
 * <ul>
 *     <li>name - {@link Name} of the person.
 *     <li>email - Email address of the person.
 *     <li>orcid - <a href="http://orcid.org">ORCID</a> identifier of the person.
 * </ul>
 *
 * @author Kyle Michel
 */
public class Person extends Pio {

    /**
     * Set the name of the person.
     *
     * @param name {@link Name} object for the person.
     * @return This object.
     */
    @JsonSetter(value = "name")
    @JsonDeserialize(using = Name.Deserializer.class)
    public Person setName(final Name name) {
        this.name = name;
        return this;
    }

    /**
     * Get the name of the person.
     *
     * @return {@link Name} object for the person.
     */
    @JsonGetter(value = "name")
    public Name getName() {
        return this.name;
    }

    /**
     * Set the email address of the person.
     *
     * @param email String with the email address of the person.
     * @return This object.
     */
    @JsonSetter(value = "email")
    public Person setEmail(final String email) {
        this.email = email;
        return this;
    }

    /**
     * Get the email address of the person.
     *
     * @return String with the email address of the person.
     */
    @JsonGetter(value = "email")
    public String getEmail() {
        return this.email;
    }

    /**
     * Set the <a href="http://orcid.org">ORCID</a> identifier of the person.
     *
     * @param orcid String with the OCRID identifier of the person.
     * @return This object.
     */
    @JsonSetter(value = "orcid")
    public Person setOrcid(final String orcid) {
        this.orcid = orcid;
        return this;
    }

    /**
     * Get the <a href="http://orcid.org">ORCID</a> identifier of the person.
     *
     * @return String with the ORCID identifier of the person.
     */
    @JsonGetter(value = "orcid")
    public String getOrcid() {
        return this.orcid;
    }

    @Override
    @JsonAnySetter
    public Person addUnsupportedField(final String key, final Object value) {
        super.addUnsupportedField(key, value);
        return this;
    }

    /**
     * Generate a new {@link Person} object from an input string. This function checks whether the input matches an
     * email format, then ORCID format, then falls back to saving as a name.
     *
     * @param input String to convert to a {@link Person} object.
     * @return New {@link Person} object with the parsed out input.
     */
    public static Person valueOf(final String input) {
        return (input == null)
                ? null
                : interpretString(input);
    }

    /**
     * Interpret the input string as a person.
     *
     * @param input String to convert to a {@link Person} object.
     * @return New {@link Person} object with the parsed out input.
     */
    protected static Person interpretString(final String input) {
        final Person res = new Person();
        if (isEmailAddress(input)) {
            res.setEmail(input);
        }
        else if (isOrcid(input)) {
            res.setOrcid(input);
        }
        else {
            res.setName(Name.valueOf(input));
        }
        return res;
    }

    /**
     * Determine whether the input string is a valid email address.
     *
     * @param input String to check whether an email address.
     * @return True if the input string is an email address.
     */
    protected static boolean isEmailAddress(String input) {
        input = input.trim();
        return EmailValidator.getInstance().isValid(input);
    }

    /**
     * Determine whether an input string is a valid ORCID string.
     *
     * @param input String to check as an ORCID id.
     * @return True if the input string is an ORCID id.
     */
    protected static boolean isOrcid(String input) {
        input = input.trim();
        return Orcid.isValid(input);
    }

    /** Name of the person. */
    private Name name;

    /** Email address of the person. */
    private String email;

    /** ORCID identifier of the person. */
    private String orcid;

    /**
     * Class used to deserialize a JSON value into a {@link Person} object. If the input token is a string then an
     * attempt is made to determine the information that it contains. If the input token is an object, then it is
     * converted directly to a {@link Person} object.
     *
     * @author Kyle Michel
     */
    public static class Deserializer extends JsonDeserializer<Person> {

        @Override
        public Person deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
                throws IOException {
            final JsonToken jsonToken = jsonParser.getCurrentToken();
            switch (jsonToken) {
                case VALUE_STRING:
                    return Person.valueOf(jsonParser.getValueAsString());
                case START_OBJECT:
                    return PifObjectMapper.getInstance().readValue(jsonParser, Person.class);
                default:
                    throw deserializationContext.mappingException(Person.class, jsonToken);
            }
        }
    }
}