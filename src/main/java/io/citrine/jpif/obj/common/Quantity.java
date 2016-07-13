package io.citrine.jpif.obj.common;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Information about the quantity of a system. The fields ending with massFraction, volumeFraction, and 
 * numberFraction are used to denote the relative quantity of this system when it is one of a set of subsystems.
 *
 * <p>Supported fields:
 * <ul>
 *     <li>actualMassFraction - Actual fraction of the total mass made up by this system.
 *     <li>actualVolumeFraction - Actual fraction of the total volume made up by this system.
 *     <li>actualNumberFraction - Actual fraction of the total numeric quantity made up by this system.
 *     <li>idealMassFraction - Ideal fraction of the total mass made up by this system.
 *     <li>idealVolumeFraction - Ideal fraction of the total volume made up by this system.
 *     <li>idealNumberFraction - Ideal fraction of the total numeric quantity made up by this system.
 *     <li>tags - List of tags that apply to the source.
 * </ul>
 *
 * @author Kyle Michel
 */
public class Quantity extends Pio {

    /**
     * Set the actual fraction of the total mass made up by this system.
     *
     * @param actualMassFraction {@link Scalar} object with the mass fraction.
     * @return This object.
     */
    @JsonSetter(value = "actualMassFraction")
    @JsonDeserialize(using = Scalar.Deserializer.class)
    public Quantity setActualMassFraction(final Scalar actualMassFraction) {
        this.actualMassFraction = actualMassFraction;
        return this;
    }

    /**
     * Set the actual fraction of the total mass made up by this system.
     *
     * @param actualMassFraction String with the mass fraction.
     * @return This object.
     */
    @JsonIgnore
    public Quantity setActualMassFraction(final String actualMassFraction) {
        return setActualMassFraction(Scalar.valueOf(actualMassFraction));
    }

    /**
     * Set the actual fraction of the total mass made up by this system.
     *
     * @param actualMassFraction Number with the mass fraction.
     * @return This object.
     */
    @JsonIgnore
    public Quantity setActualMassFraction(final Number actualMassFraction) {
        return setActualMassFraction(Scalar.valueOf(actualMassFraction));
    }

    /**
     * Get the actual fraction of the total mass made up by this system.
     *
     * @return {@link Scalar} object with the mass fraction.
     */
    @JsonGetter(value = "actualMassFraction")
    public Scalar getActualMassFraction() {
        return this.actualMassFraction;
    }

    /**
     * Set the actual fraction of the total volume made up by this system.
     *
     * @param actualVolumeFraction {@link Scalar} object with the volume fraction.
     * @return This object.
     */
    @JsonSetter(value = "actualVolumeFraction")
    @JsonDeserialize(using = Scalar.Deserializer.class)
    public Quantity setActualVolumeFraction(final Scalar actualVolumeFraction) {
        this.actualVolumeFraction = actualVolumeFraction;
        return this;
    }

    /**
     * Set the actual fraction of the total volume made up by this system.
     *
     * @param actualVolumeFraction String with the volume fraction.
     * @return This object.
     */
    @JsonIgnore
    public Quantity setActualVolumeFraction(final String actualVolumeFraction) {
        return setActualVolumeFraction(Scalar.valueOf(actualVolumeFraction));
    }

    /**
     * Set the actual fraction of the total volume made up by this system.
     *
     * @param actualVolumeFraction Number with the volume fraction.
     * @return This object.
     */
    @JsonIgnore
    public Quantity setActualVolumeFraction(final Number actualVolumeFraction) {
        return setActualVolumeFraction(Scalar.valueOf(actualVolumeFraction));
    }

    /**
     * Get the actual fraction of the total volume made up by this system.
     *
     * @return {@link Scalar} object with the volume fraction.
     */
    @JsonGetter(value = "actualVolumeFraction")
    public Scalar getActualVolumeFraction() {
        return this.actualVolumeFraction;
    }

    /**
     * Set the actual fraction of the total number made up by this system.
     *
     * @param actualNumberFraction {@link Scalar} object with the number fraction.
     * @return This object.
     */
    @JsonSetter(value = "actualNumberFraction")
    @JsonDeserialize(using = Scalar.Deserializer.class)
    public Quantity setActualNumberFraction(final Scalar actualNumberFraction) {
        this.actualNumberFraction = actualNumberFraction;
        return this;
    }

    /**
     * Set the actual fraction of the total number made up by this system.
     *
     * @param actualNumberFraction String with the number fraction.
     * @return This object.
     */
    @JsonIgnore
    public Quantity setActualNumberFraction(final String actualNumberFraction) {
        return setActualNumberFraction(Scalar.valueOf(actualNumberFraction));
    }

    /**
     * Set the actual fraction of the total number made up by this system.
     *
     * @param actualNumberFraction Number with the number fraction.
     * @return This object.
     */
    @JsonIgnore
    public Quantity setActualNumberFraction(final Number actualNumberFraction) {
        return setActualNumberFraction(Scalar.valueOf(actualNumberFraction));
    }

    /**
     * Get the actual fraction of the total number made up by this system.
     *
     * @return {@link Scalar} object with the number fraction.
     */
    @JsonGetter(value = "actualNumberFraction")
    public Scalar getActualNumberFraction() {
        return this.actualNumberFraction;
    }

    /**
     * Set the ideal fraction of the total mass made up by this system.
     *
     * @param idealMassFraction {@link Scalar} object with the mass fraction.
     * @return This object.
     */
    @JsonSetter(value = "idealMassFraction")
    @JsonDeserialize(using = Scalar.Deserializer.class)
    public Quantity setIdealMassFraction(final Scalar idealMassFraction) {
        this.idealMassFraction = idealMassFraction;
        return this;
    }

    /**
     * Set the ideal fraction of the total mass made up by this system.
     *
     * @param idealMassFraction String with the mass fraction.
     * @return This object.
     */
    @JsonIgnore
    public Quantity setIdealMassFraction(final String idealMassFraction) {
        return setIdealMassFraction(Scalar.valueOf(idealMassFraction));
    }

    /**
     * Set the ideal fraction of the total mass made up by this system.
     *
     * @param idealMassFraction Number with the mass fraction.
     * @return This object.
     */
    @JsonIgnore
    public Quantity setIdealMassFraction(final Number idealMassFraction) {
        return setIdealMassFraction(Scalar.valueOf(idealMassFraction));
    }

    /**
     * Get the ideal fraction of the total mass made up by this system.
     *
     * @return {@link Scalar} object with the mass fraction.
     */
    @JsonGetter(value = "idealMassFraction")
    public Scalar getIdealMassFraction() {
        return this.idealMassFraction;
    }

    /**
     * Set the ideal fraction of the total volume made up by this system.
     *
     * @param idealVolumeFraction {@link Scalar} object with the volume fraction.
     * @return This object.
     */
    @JsonSetter(value = "idealVolumeFraction")
    @JsonDeserialize(using = Scalar.Deserializer.class)
    public Quantity setIdealVolumeFraction(final Scalar idealVolumeFraction) {
        this.idealVolumeFraction = idealVolumeFraction;
        return this;
    }

    /**
     * Set the ideal fraction of the total volume made up by this system.
     *
     * @param idealVolumeFraction String with the volume fraction.
     * @return This object.
     */
    @JsonIgnore
    public Quantity setIdealVolumeFraction(final String idealVolumeFraction) {
        return setIdealVolumeFraction(Scalar.valueOf(idealVolumeFraction));
    }

    /**
     * Set the ideal fraction of the total volume made up by this system.
     *
     * @param idealVolumeFraction Number with the volume fraction.
     * @return This object.
     */
    @JsonIgnore
    public Quantity setIdealVolumeFraction(final Number idealVolumeFraction) {
        return setIdealVolumeFraction(Scalar.valueOf(idealVolumeFraction));
    }

    /**
     * Get the ideal fraction of the total volume made up by this system.
     *
     * @return {@link Scalar} object with the volume fraction.
     */
    @JsonGetter(value = "idealVolumeFraction")
    public Scalar getIdealVolumeFraction() {
        return this.idealVolumeFraction;
    }

    /**
     * Set the ideal fraction of the total number made up by this system.
     *
     * @param idealNumberFraction {@link Scalar} object with the number fraction.
     * @return This object.
     */
    @JsonSetter(value = "idealNumberFraction")
    @JsonDeserialize(using = Scalar.Deserializer.class)
    public Quantity setIdealNumberFraction(final Scalar idealNumberFraction) {
        this.idealNumberFraction = idealNumberFraction;
        return this;
    }

    /**
     * Set the ideal fraction of the total number made up by this system.
     *
     * @param idealNumberFraction String with the number fraction.
     * @return This object.
     */
    @JsonIgnore
    public Quantity setIdealNumberFraction(final String idealNumberFraction) {
        return setIdealNumberFraction(Scalar.valueOf(idealNumberFraction));
    }

    /**
     * Set the ideal fraction of the total number made up by this system.
     *
     * @param idealNumberFraction Number with the number fraction.
     * @return This object.
     */
    @JsonIgnore
    public Quantity setIdealNumberFraction(final Number idealNumberFraction) {
        return setIdealNumberFraction(Scalar.valueOf(idealNumberFraction));
    }

    /**
     * Get the ideal fraction of the total number made up by this system.
     *
     * @return {@link Scalar} object with the number fraction.
     */
    @JsonGetter(value = "idealNumberFraction")
    public Scalar getIdealNumberFraction() {
        return this.idealNumberFraction;
    }

    @Override
    public Quantity addTag(final String tag) {
        super.addTag(tag);
        return this;
    }

    @Override
    public Quantity addTag(final int index, final String tag) {
        super.addTag(index, tag);
        return this;
    }

    @Override
    @JsonAnySetter
    public Quantity addUnsupportedField(final String key, final Object value) {
        super.addUnsupportedField(key, value);
        return this;
    }

    /** Actual fraction of the total mass. */
    private Scalar actualMassFraction;

    /** Actual fraction of the total volume. */
    private Scalar actualVolumeFraction;

    /** Actual fraction of the total number. */
    private Scalar actualNumberFraction;
    
    /** Ideal fraction of the total mass. */
    private Scalar idealMassFraction;

    /** Ideal fraction of the total volume. */
    private Scalar idealVolumeFraction;

    /** Ideal fraction of the total number. */
    private Scalar idealNumberFraction;
}