package io.citrine.jpif.obj.common;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Information about the quantity of a system. The fields ending with massPercent, volumePercent, and 
 * numberPercent are used to denote the relative quantity of this system when it is one of a set of subsystems.
 *
 * <p>Supported fields:
 * <ul>
 *     <li>actualMassPercent - Actual percent of the total mass made up by this system.
 *     <li>actualVolumePercent - Actual percent of the total volume made up by this system.
 *     <li>actualNumberPercent - Actual percent of the total numeric quantity made up by this system.
 *     <li>idealMassPercent - Ideal percent of the total mass made up by this system.
 *     <li>idealVolumePercent - Ideal percent of the total volume made up by this system.
 *     <li>idealNumberPercent - Ideal percent of the total numeric quantity made up by this system.
 *     <li>tags - List of tags that apply to the source.
 * </ul>
 *
 * @author Kyle Michel
 */
public class Quantity extends Pio {

    /**
     * Set the actual percent of the total mass made up by this system.
     *
     * @param actualMassPercent {@link Scalar} object with the mass percent.
     * @return This object.
     */
    @JsonSetter(value = "actualMassPercent")
    @JsonDeserialize(using = Scalar.Deserializer.class)
    public Quantity setActualMassPercent(final Scalar actualMassPercent) {
        this.actualMassPercent = actualMassPercent;
        return this;
    }

    /**
     * Set the actual percent of the total mass made up by this system.
     *
     * @param actualMassPercent String with the mass percent.
     * @return This object.
     */
    @JsonIgnore
    public Quantity setActualMassPercent(final String actualMassPercent) {
        return setActualMassPercent(Scalar.valueOf(actualMassPercent));
    }

    /**
     * Set the actual percent of the total mass made up by this system.
     *
     * @param actualMassPercent Number with the mass percent.
     * @return This object.
     */
    @JsonIgnore
    public Quantity setActualMassPercent(final Number actualMassPercent) {
        return setActualMassPercent(Scalar.valueOf(actualMassPercent));
    }

    /**
     * Get the actual percent of the total mass made up by this system.
     *
     * @return {@link Scalar} object with the mass percent.
     */
    @JsonGetter(value = "actualMassPercent")
    public Scalar getActualMassPercent() {
        return this.actualMassPercent;
    }

    /**
     * Set the actual percent of the total volume made up by this system.
     *
     * @param actualVolumePercent {@link Scalar} object with the volume percent.
     * @return This object.
     */
    @JsonSetter(value = "actualVolumePercent")
    @JsonDeserialize(using = Scalar.Deserializer.class)
    public Quantity setActualVolumePercent(final Scalar actualVolumePercent) {
        this.actualVolumePercent = actualVolumePercent;
        return this;
    }

    /**
     * Set the actual percent of the total volume made up by this system.
     *
     * @param actualVolumePercent String with the volume percent.
     * @return This object.
     */
    @JsonIgnore
    public Quantity setActualVolumePercent(final String actualVolumePercent) {
        return setActualVolumePercent(Scalar.valueOf(actualVolumePercent));
    }

    /**
     * Set the actual percent of the total volume made up by this system.
     *
     * @param actualVolumePercent Number with the volume percent.
     * @return This object.
     */
    @JsonIgnore
    public Quantity setActualVolumePercent(final Number actualVolumePercent) {
        return setActualVolumePercent(Scalar.valueOf(actualVolumePercent));
    }

    /**
     * Get the actual percent of the total volume made up by this system.
     *
     * @return {@link Scalar} object with the volume percent.
     */
    @JsonGetter(value = "actualVolumePercent")
    public Scalar getActualVolumePercent() {
        return this.actualVolumePercent;
    }

    /**
     * Set the actual percent of the total number made up by this system.
     *
     * @param actualNumberPercent {@link Scalar} object with the number percent.
     * @return This object.
     */
    @JsonSetter(value = "actualNumberPercent")
    @JsonDeserialize(using = Scalar.Deserializer.class)
    public Quantity setActualNumberPercent(final Scalar actualNumberPercent) {
        this.actualNumberPercent = actualNumberPercent;
        return this;
    }

    /**
     * Set the actual percent of the total number made up by this system.
     *
     * @param actualNumberPercent String with the number percent.
     * @return This object.
     */
    @JsonIgnore
    public Quantity setActualNumberPercent(final String actualNumberPercent) {
        return setActualNumberPercent(Scalar.valueOf(actualNumberPercent));
    }

    /**
     * Set the actual percent of the total number made up by this system.
     *
     * @param actualNumberPercent Number with the number percent.
     * @return This object.
     */
    @JsonIgnore
    public Quantity setActualNumberPercent(final Number actualNumberPercent) {
        return setActualNumberPercent(Scalar.valueOf(actualNumberPercent));
    }

    /**
     * Get the actual percent of the total number made up by this system.
     *
     * @return {@link Scalar} object with the number percent.
     */
    @JsonGetter(value = "actualNumberPercent")
    public Scalar getActualNumberPercent() {
        return this.actualNumberPercent;
    }

    /**
     * Set the ideal percent of the total mass made up by this system.
     *
     * @param idealMassPercent {@link Scalar} object with the mass percent.
     * @return This object.
     */
    @JsonSetter(value = "idealMassPercent")
    @JsonDeserialize(using = Scalar.Deserializer.class)
    public Quantity setIdealMassPercent(final Scalar idealMassPercent) {
        this.idealMassPercent = idealMassPercent;
        return this;
    }

    /**
     * Set the ideal percent of the total mass made up by this system.
     *
     * @param idealMassPercent String with the mass percent.
     * @return This object.
     */
    @JsonIgnore
    public Quantity setIdealMassPercent(final String idealMassPercent) {
        return setIdealMassPercent(Scalar.valueOf(idealMassPercent));
    }

    /**
     * Set the ideal percent of the total mass made up by this system.
     *
     * @param idealMassPercent Number with the mass percent.
     * @return This object.
     */
    @JsonIgnore
    public Quantity setIdealMassPercent(final Number idealMassPercent) {
        return setIdealMassPercent(Scalar.valueOf(idealMassPercent));
    }

    /**
     * Get the ideal percent of the total mass made up by this system.
     *
     * @return {@link Scalar} object with the mass percent.
     */
    @JsonGetter(value = "idealMassPercent")
    public Scalar getIdealMassPercent() {
        return this.idealMassPercent;
    }

    /**
     * Set the ideal percent of the total volume made up by this system.
     *
     * @param idealVolumePercent {@link Scalar} object with the volume percent.
     * @return This object.
     */
    @JsonSetter(value = "idealVolumePercent")
    @JsonDeserialize(using = Scalar.Deserializer.class)
    public Quantity setIdealVolumePercent(final Scalar idealVolumePercent) {
        this.idealVolumePercent = idealVolumePercent;
        return this;
    }

    /**
     * Set the ideal percent of the total volume made up by this system.
     *
     * @param idealVolumePercent String with the volume percent.
     * @return This object.
     */
    @JsonIgnore
    public Quantity setIdealVolumePercent(final String idealVolumePercent) {
        return setIdealVolumePercent(Scalar.valueOf(idealVolumePercent));
    }

    /**
     * Set the ideal percent of the total volume made up by this system.
     *
     * @param idealVolumePercent Number with the volume percent.
     * @return This object.
     */
    @JsonIgnore
    public Quantity setIdealVolumePercent(final Number idealVolumePercent) {
        return setIdealVolumePercent(Scalar.valueOf(idealVolumePercent));
    }

    /**
     * Get the ideal percent of the total volume made up by this system.
     *
     * @return {@link Scalar} object with the volume percent.
     */
    @JsonGetter(value = "idealVolumePercent")
    public Scalar getIdealVolumePercent() {
        return this.idealVolumePercent;
    }

    /**
     * Set the ideal percent of the total number made up by this system.
     *
     * @param idealNumberPercent {@link Scalar} object with the number percent.
     * @return This object.
     */
    @JsonSetter(value = "idealNumberPercent")
    @JsonDeserialize(using = Scalar.Deserializer.class)
    public Quantity setIdealNumberPercent(final Scalar idealNumberPercent) {
        this.idealNumberPercent = idealNumberPercent;
        return this;
    }

    /**
     * Set the ideal percent of the total number made up by this system.
     *
     * @param idealNumberPercent String with the number percent.
     * @return This object.
     */
    @JsonIgnore
    public Quantity setIdealNumberPercent(final String idealNumberPercent) {
        return setIdealNumberPercent(Scalar.valueOf(idealNumberPercent));
    }

    /**
     * Set the ideal percent of the total number made up by this system.
     *
     * @param idealNumberPercent Number with the number percent.
     * @return This object.
     */
    @JsonIgnore
    public Quantity setIdealNumberPercent(final Number idealNumberPercent) {
        return setIdealNumberPercent(Scalar.valueOf(idealNumberPercent));
    }

    /**
     * Get the ideal percent of the total number made up by this system.
     *
     * @return {@link Scalar} object with the number percent.
     */
    @JsonGetter(value = "idealNumberPercent")
    public Scalar getIdealNumberPercent() {
        return this.idealNumberPercent;
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

    /** Actual percent of the total mass. */
    private Scalar actualMassPercent;

    /** Actual percent of the total volume. */
    private Scalar actualVolumePercent;

    /** Actual percent of the total number. */
    private Scalar actualNumberPercent;
    
    /** Ideal percent of the total mass. */
    private Scalar idealMassPercent;

    /** Ideal percent of the total volume. */
    private Scalar idealVolumePercent;

    /** Ideal percent of the total number. */
    private Scalar idealNumberPercent;
}