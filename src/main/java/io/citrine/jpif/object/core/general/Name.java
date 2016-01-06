package io.citrine.jpif.object.core.general;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Representation of the first and last name of a person.
 *
 * @author Kyle Michel
 */
public class Name extends Pio {

    /**
     * Set the given (first) name of a person.
     *
     * @param given String with the given name of the person.
     */
    @JsonSetter
    public void setGiven(final String given) {
        this.given = given;
    }

    /**
     * Set the given (first) name of a person.
     *
     * @param given String with the given name of the person.
     * @return This object.
     */
    public Name withGiven(final String given) {
        this.setGiven(given);
        return this;
    }

    /**
     * Get the given (first) name of a person.
     *
     * @return String with the given name of the person.
     */
    @JsonGetter
    public String getGiven() {
        return this.given;
    }

    /**
     * Set the family (last) name of a person.
     *
     * @param family String with the family name of the person.
     */
    @JsonSetter
    public void setFamily(final String family) {
        this.family = family;
    }

    /**
     * Set the family (last) name of a person.
     *
     * @param family String with the family name of the person.
     * @return This object.
     */
    public Name withFamily(final String family) {
        this.setFamily(family);
        return this;
    }

    /**
     * Get the family (last) name of a person.
     *
     * @return String with the family name of a person.
     */
    @JsonGetter
    public String getFamily() {
        return this.family;
    }

    @Override
    public Name withUnsupportedField(final String key, final Object value) {
        super.withUnsupportedField(key, value);
        return this;
    }

    /** Given name of the person. */
    protected String given;

    /** Family name of the person. */
    protected String family;
}