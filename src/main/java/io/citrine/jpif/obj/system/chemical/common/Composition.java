package io.citrine.jpif.obj.system.chemical.common;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.citrine.jpif.obj.common.Pio;
import io.citrine.jpif.obj.common.Scalar;

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
     * @param weightPercent {@link Scalar} with the weight percent of the element in the system.
     * @return This object.
     */
    @JsonSetter(value = "weightPercent")
    @JsonDeserialize(using = Scalar.Deserializer.class)
    public Composition setWeightPercent(final Scalar weightPercent) {
        this.weightPercent = weightPercent;
        return this;
    }

    /**
     * Set the weight percent of the element in the overall system.
     *
     * @param weightPercent String with the weight percent of the element in the system.
     * @return This object.
     */
    @JsonIgnore
    public Composition setWeightPercent(final String weightPercent) {
        return setWeightPercent(Scalar.valueOf(weightPercent));
    }

    /**
     * Set the weight percent of the element in the overall system.
     *
     * @param weightPercent Number with the weight percent of the element in the system.
     * @return This object.
     */
    @JsonIgnore
    public Composition setWeightPercent(final Number weightPercent) {
        return setWeightPercent(Scalar.valueOf(weightPercent));
    }

    /**
     * Get the weight percent of the element in the system.
     *
     * @return {@link Scalar} with the weight percent of the element in the system.
     */
    @JsonGetter(value = "weightPercent")
    public Scalar getWeightPercent() {
        return this.weightPercent;
    }

    /**
     * Set the atomic percent of the element in the system.
     *
     * @param atomicPercent {@link Scalar} with the atomic percent of the element in the system.
     * @return This object.
     */
    @JsonSetter(value = "atomicPercent")
    @JsonDeserialize(using = Scalar.Deserializer.class)
    public Composition setAtomicPercent(final Scalar atomicPercent) {
        this.atomicPercent = atomicPercent;
        return this;
    }

    /**
     * Set the atomic percent of the element in the system.
     *
     * @param atomicPercent String with the atomic percent of the element in the system.
     * @return This object.
     */
    @JsonIgnore
    public Composition setAtomicPercent(final String atomicPercent) {
        return setAtomicPercent(Scalar.valueOf(atomicPercent));
    }

    /**
     * Set the atomic percent of the element in the system.
     *
     * @param atomicPercent Number with the atomic percent of the element in the system.
     * @return This object.
     */
    @JsonIgnore
    public Composition setAtomicPercent(final Number atomicPercent) {
        return setAtomicPercent(Scalar.valueOf(atomicPercent));
    }

    /**
     * Get the atomic percent of the element in the system.
     *
     * @return {@link Scalar} with the atomic percent of the element in the system.
     */
    @JsonGetter(value = "atomicPercent")
    public Scalar getAtomicPercent() {
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
    private Scalar weightPercent;

    /** Atomic percent of the element. */
    private Scalar atomicPercent;
}