package io.citrine.jpif.object.core.general;

import com.fasterxml.jackson.annotation.JsonAnySetter;

/**
 * Representation of a single scalar value that could represent an absolute point, an uncertain point, a range of
 * values, a minimum, or a maximum.
 *
 * @author Kyle Michel
 */
public class Scalar extends Pio {

    /**
     * Set the exact value.
     *
     * @param value String with the exact value.
     * @return This object.
     */
    public Scalar setValue(final String value) {
        this.value = value;
        return this;
    }

    /**
     * Get the exact value.
     *
     * @return String with the exact value.
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Set the minimum of the value.
     *
     * @param minimum String with the minimum of the value.
     * @return This object.
     */
    public Scalar setMinimum(final String minimum) {
        this.minimum = minimum;
        return this;
    }

    /**
     * Get the minimum of the value.
     *
     * @return String with the minimum of the value.
     */
    public String getMinimum() {
        return this.minimum;
    }

    /**
     * Set the maximum of the value.
     *
     * @param maximum String with the maximum of the value.
     * @return This object.
     */
    public Scalar setMaximum(final String maximum) {
        this.maximum = maximum;
        return this;
    }

    /**
     * Get the maximum of the value.
     *
     * @return String with the maximum of the value.
     */
    public String getMaximum() {
        return this.maximum;
    }

    /**
     * Set the uncertainty of the value.
     *
     * @param uncertainty String with the uncertainty of the value.
     * @return This object.
     */
    public Scalar setUncertainty(final String uncertainty) {
        this.uncertainty = uncertainty;
        return this;
    }

    /**
     * Get the uncertainty of the value.
     *
     * @return String with the uncertainty of the value.
     */
    public String getUncertainty() {
        return this.uncertainty;
    }

    @Override
    @JsonAnySetter
    public Scalar addUnsupportedField(final String key, final Object value) {
        super.addUnsupportedField(key, value);
        return this;
    }

    /** Exact value. */
    private String value;

    /** Minimum of the value. */
    private String minimum;

    /** Maximum of the value. */
    private String maximum;

    /** Uncertainty of the value. */
    private String uncertainty;
}