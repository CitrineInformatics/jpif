package io.citrine.jpif.object.core.general;

import com.fasterxml.jackson.annotation.JsonAnySetter;

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
     * @return This object.
     */
    public Name setGiven(final String given) {
        this.given = given;
        return this;
    }

    /**
     * Get the given (first) name of a person.
     *
     * @return String with the given name of the person.
     */
    public String getGiven() {
        return this.given;
    }

    /**
     * Set the family (last) name of a person.
     *
     * @param family String with the family name of the person.
     * @return This object.
     */
    public Name setFamily(final String family) {
        this.family = family;
        return this;
    }

    /**
     * Get the family (last) name of a person.
     *
     * @return String with the family name of a person.
     */
    public String getFamily() {
        return this.family;
    }

    @Override
    @JsonAnySetter
    public Name addUnsupportedField(final String key, final Object value) {
        super.addUnsupportedField(key, value);
        return this;
    }

    /** Given name of the person. */
    private String given;

    /** Family name of the person. */
    private String family;
}