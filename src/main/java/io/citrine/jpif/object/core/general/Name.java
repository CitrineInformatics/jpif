package io.citrine.jpif.object.core.general;

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
    public void setGiven(final String given) {
        this.given = given;
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
     */
    public void setFamily(final String family) {
        this.family = family;
    }

    /**
     * Get the family (last) name of a person.
     *
     * @return String with the family name of a person.
     */
    public String getFamily() {
        return this.family;
    }

    /** Given name of the person. */
    protected String given;

    /** Family name of the person. */
    protected String family;
}