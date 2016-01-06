package io.citrine.jpif.object.core.general;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

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
    @JsonSetter
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Set the name of the identifier.
     *
     * @param name String with the name of the identifier.
     * @return This object.
     */
    public Id withName(final String name) {
        this.setName(name);
        return this;
    }

    /**
     * Get the name of the identifier.
     *
     * @return String with the name of the identifier.
     */
    @JsonGetter
    public String getName() {
        return this.name;
    }

    /**
     * Set the value of the identifier.
     *
     * @param value String with the value of the identifier.
     */
    @JsonSetter
    public void setValue(final String value) {
        this.value = value;
    }

    /**
     * Set the value of the identifier.
     *
     * @param value String with the value of the identifier.
     * @return This object.
     */
    public Id withValue(final String value) {
        this.setValue(value);
        return this;
    }

    /**
     * Get the value of the identifier.
     *
     * @return String with the value of the identifier.
     */
    @JsonGetter
    public String getValue() {
        return this.value;
    }

    @Override
    public Id withUnsupportedField(final String key, final Object value) {
        super.withUnsupportedField(key, value);
        return this;
    }

    /** Name of the identifier. */
    protected String name;

    /** Value of the identifier. */
    protected String value;
}