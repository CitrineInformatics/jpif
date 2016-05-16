package io.citrine.jpif.obj.system.chemical.common;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.citrine.jpif.obj.common.Id;
import io.citrine.jpif.obj.common.Pio;
import io.citrine.jpif.obj.common.Scalar;

/**
 * Information about an element in a composition vector using weight or atomic percents.
 *
 * <p>Supported fields:
 * <ul>
 *     <li>element - Element that the atomic and weight percents apply to.
 *     <li>actualWeightPercent - Actual (measured) weight percent of the element.
 *     <li>idealWeightPercent - Ideal weight percent of the element.
 *     <li>actualAtomicPercent - Actual (measured) atomic percent of the element.
 *     <li>idealAtomicPercent - Ideal atomic percent of the element.
 * </ul>
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
     * Set the actual weight percent of the element in the overall system.
     *
     * @param weightPercent {@link Scalar} with the actual weight percent of the element in the system.
     */
    @JsonSetter(value = "weightPercent")
    @JsonDeserialize(using = Scalar.Deserializer.class)
    protected void setWeightPercent(final Scalar weightPercent) {  // Private since only Jackson should use it
        setActualWeightPercent(weightPercent);
    }

    /**
     * Set the actual weight percent of the element in the overall system.
     *
     * @param actualWeightPercent {@link Scalar} with the actual weight percent of the element in the system.
     * @return This object.
     */
    @JsonSetter(value = "actualWeightPercent")
    @JsonDeserialize(using = Scalar.Deserializer.class)
    public Composition setActualWeightPercent(final Scalar actualWeightPercent) {
        this.actualWeightPercent = actualWeightPercent;
        return this;
    }

    /**
     * Set the actual weight percent of the element in the overall system.
     *
     * @param actualWeightPercent String with the actual weight percent of the element in the system.
     * @return This object.
     */
    @JsonIgnore
    public Composition setActualWeightPercent(final String actualWeightPercent) {
        return setActualWeightPercent(Scalar.valueOf(actualWeightPercent));
    }

    /**
     * Set the actual weight percent of the element in the overall system.
     *
     * @param actualWeightPercent Number with the actual weight percent of the element in the system.
     * @return This object.
     */
    @JsonIgnore
    public Composition setActualWeightPercent(final Number actualWeightPercent) {
        return setActualWeightPercent(Scalar.valueOf(actualWeightPercent));
    }

    /**
     * Get the actual weight percent of the element in the system.
     *
     * @return {@link Scalar} with the actual weight percent of the element in the system.
     */
    @JsonGetter(value = "actualWeightPercent")
    public Scalar getActualWeightPercent() {
        return this.actualWeightPercent;
    }

    /**
     * Set the ideal weight percent of the element in the overall system.
     *
     * @param idealWeightPercent {@link Scalar} with the ideal weight percent of the element in the system.
     * @return This object.
     */
    @JsonSetter(value = "idealWeightPercent")
    @JsonDeserialize(using = Scalar.Deserializer.class)
    public Composition setIdealWeightPercent(final Scalar idealWeightPercent) {
        this.idealWeightPercent = idealWeightPercent;
        return this;
    }

    /**
     * Set the ideal weight percent of the element in the overall system.
     *
     * @param idealWeightPercent String with the ideal weight percent of the element in the system.
     * @return This object.
     */
    @JsonIgnore
    public Composition setIdealWeightPercent(final String idealWeightPercent) {
        return setIdealWeightPercent(Scalar.valueOf(idealWeightPercent));
    }

    /**
     * Set the ideal weight percent of the element in the overall system.
     *
     * @param idealWeightPercent Number with the ideal weight percent of the element in the system.
     * @return This object.
     */
    @JsonIgnore
    public Composition setIdealWeightPercent(final Number idealWeightPercent) {
        return setIdealWeightPercent(Scalar.valueOf(idealWeightPercent));
    }

    /**
     * Get the ideal weight percent of the element in the system.
     *
     * @return {@link Scalar} with the ideal weight percent of the element in the system.
     */
    @JsonGetter(value = "idealWeightPercent")
    public Scalar getIdealWeightPercent() {
        return this.idealWeightPercent;
    }

    /**
     * Set the actual atomic percent of the element in the system.
     *
     * @param atomicPercent {@link Scalar} with the actual atomic percent of the element in the system.
     */
    @JsonSetter(value = "atomicPercent")
    @JsonDeserialize(using = Scalar.Deserializer.class)
    protected void setAtomicPercent(final Scalar atomicPercent) {  // Private since only Jackson should use it
        setActualAtomicPercent(atomicPercent);
    }

    /**
     * Set the actual atomic percent of the element in the system.
     *
     * @param actualAtomicPercent {@link Scalar} with the actual atomic percent of the element in the system.
     * @return This object.
     */
    @JsonSetter(value = "actualAtomicPercent")
    @JsonDeserialize(using = Scalar.Deserializer.class)
    public Composition setActualAtomicPercent(final Scalar actualAtomicPercent) {
        this.actualAtomicPercent = actualAtomicPercent;
        return this;
    }

    /**
     * Set the actual atomic percent of the element in the system.
     *
     * @param actualAtomicPercent String with the actual atomic percent of the element in the system.
     * @return This object.
     */
    @JsonIgnore
    public Composition setActualAtomicPercent(final String actualAtomicPercent) {
        return setActualAtomicPercent(Scalar.valueOf(actualAtomicPercent));
    }

    /**
     * Set the actual atomic percent of the element in the system.
     *
     * @param actualAtomicPercent Number with the actual atomic percent of the element in the system.
     * @return This object.
     */
    @JsonIgnore
    public Composition setActualAtomicPercent(final Number actualAtomicPercent) {
        return setActualAtomicPercent(Scalar.valueOf(actualAtomicPercent));
    }

    /**
     * Get the actual atomic percent of the element in the system.
     *
     * @return {@link Scalar} with the actual atomic percent of the element in the system.
     */
    @JsonGetter(value = "actualAtomicPercent")
    public Scalar getActualAtomicPercent() {
        return this.actualAtomicPercent;
    }

    /**
     * Set the ideal atomic percent of the element in the system.
     *
     * @param idealAtomicPercent {@link Scalar} with the ideal atomic percent of the element in the system.
     * @return This object.
     */
    @JsonSetter(value = "idealAtomicPercent")
    @JsonDeserialize(using = Scalar.Deserializer.class)
    public Composition setIdealAtomicPercent(final Scalar idealAtomicPercent) {
        this.idealAtomicPercent = idealAtomicPercent;
        return this;
    }

    /**
     * Set the ideal atomic percent of the element in the system.
     *
     * @param idealAtomicPercent String with the ideal atomic percent of the element in the system.
     * @return This object.
     */
    @JsonIgnore
    public Composition setIdealAtomicPercent(final String idealAtomicPercent) {
        return setIdealAtomicPercent(Scalar.valueOf(idealAtomicPercent));
    }

    /**
     * Set the ideal atomic percent of the element in the system.
     *
     * @param idealAtomicPercent Number with the ideal atomic percent of the element in the system.
     * @return This object.
     */
    @JsonIgnore
    public Composition setIdealAtomicPercent(final Number idealAtomicPercent) {
        return setIdealAtomicPercent(Scalar.valueOf(idealAtomicPercent));
    }

    /**
     * Get the ideal atomic percent of the element in the system.
     *
     * @return {@link Scalar} with the ideal atomic percent of the element in the system.
     */
    @JsonGetter(value = "idealAtomicPercent")
    public Scalar getIdealAtomicPercent() {
        return this.idealAtomicPercent;
    }

    @Override
    public Composition addTag(final String tag) {
        super.addTag(tag);
        return this;
    }

    @Override
    public Composition addTag(final int index, final String tag) {
        super.addTag(index, tag);
        return this;
    }

    @Override
    @JsonAnySetter
    public Composition addUnsupportedField(final String key, final Object value) {
        super.addUnsupportedField(key, value);
        return this;
    }

    /** Element this composition represents. */
    private String element;

    /** Actual weight percent of the element. */
    private Scalar actualWeightPercent;

    /** Actual atomic percent of the element. */
    private Scalar actualAtomicPercent;

    /** Ideal weight percent of the element. */
    private Scalar idealWeightPercent;

    /** Ideal atomic percent of the element. */
    private Scalar idealAtomicPercent;
}