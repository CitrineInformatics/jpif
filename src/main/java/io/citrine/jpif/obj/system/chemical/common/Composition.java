package io.citrine.jpif.obj.system.chemical.common;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.citrine.jpif.obj.common.Pio;

/**
 * Information about an element in a composition vector using weight or atomic percents.
 *
 * @author Kyle Michel
 */
public class Composition extends Pio {

    /**
     * Set the element of the composition.
     *
     * @param element String with the element of the composition.
     * @return This object.
     */
    @JsonSetter(value = "element")
    public Composition setElement(final String element) {
        this.element = element;
        return this;
    }

    /**
     * Get the element for the composition.
     *
     * @return String with the element in this composition.
     */
    @JsonGetter(value = "element")
    public String getElement() {
        return this.element;
    }

    /**
     * Set the weight percent of the element in the overall system.
     *
     * @param weightPercent String with the weight percent of the element in the system.
     * @return This object.
     */
    @JsonSetter(value = "weightPercent")
    public Composition setWeightPercent(final String weightPercent) {
        this.weightPercent = weightPercent;
        return this;
    }

    /**
     * Get the weight percent of the element in the system.
     *
     * @return String with the weight percent of the element in the system.
     */
    @JsonGetter(value = "weightPercent")
    public String getWeightPercent() {
        return this.weightPercent;
    }

    /**
     * Set the atomic percent of the element in the system.
     *
     * @param atomicPercent String with the atomic percent of the element in the system.
     * @return This object.
     */
    @JsonSetter(value = "atomicPercent")
    public Composition setAtomicPercent(final String atomicPercent) {
        this.atomicPercent = atomicPercent;
        return this;
    }

    /**
     * Get the atomic percent of the element in the system.
     *
     * @return String with the atomic percent of the element in the system.
     */
    @JsonGetter(value = "atomicPercent")
    public String getAtomicPercent() {
        return this.atomicPercent;
    }

    @Override
    @JsonAnySetter
    public Composition addUnsupportedField(final String key, final Object value) {
        super.addUnsupportedField(key, value);
        return this;
    }

    @Override
    public Composition removeUnsupportedField(final String key) {
        super.removeUnsupportedField(key);
        return this;
    }

    @Override
    public Composition clearUnsupportedFields() {
        super.clearUnsupportedFields();
        return this;
    }

    /** Element this composition represents. */
    private String element;

    /** Weight percent of the element. */
    private String weightPercent;

    /** Atomic percent of the element. */
    private String atomicPercent;
}