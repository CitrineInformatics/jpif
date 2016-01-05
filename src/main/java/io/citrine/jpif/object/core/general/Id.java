package io.citrine.jpif.object.core.general;

/**
 * Information about a generic identifier.
 *
 * @author Kyle Michel
 */
public class Id extends Pio {

    /**
     * Set the name of the identifier.
     *
     * @param name String with the name of the identifier.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Get the name of the identifier.
     *
     * @return String with the name of the identifier.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the value of the identifier.
     *
     * @param value String with the value of the identifier.
     */
    public void setValue(final String value) {
        this.value = value;
    }

    /**
     * Get the value of the identifier.
     *
     * @return String with the value of the identifier.
     */
    public String getValue() {
        return this.value;
    }

    /** Name of the identifier. */
    protected String name;

    /** Value of the identifier. */
    protected String value;
}