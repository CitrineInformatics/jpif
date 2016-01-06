package io.citrine.jpif.object.core.general;

import com.fasterxml.jackson.annotation.JsonAnySetter;

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
     * @return This object.
     */
    public Id setName(final String name) {
        this.name = name;
        return this;
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
     * @return This object.
     */
    public Id setValue(final String value) {
        this.value = value;
        return this;
    }

    /**
     * Get the value of the identifier.
     *
     * @return String with the value of the identifier.
     */
    public String getValue() {
        return this.value;
    }

    @Override
    @JsonAnySetter
    public Id addUnsupportedField(final String key, final Object value) {
        super.addUnsupportedField(key, value);
        return this;
    }

    /** Name of the identifier. */
    private String name;

    /** Value of the identifier. */
    private String value;
}