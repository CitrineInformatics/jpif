package io.citrine.jpif.obj.common;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Information about a method used in obtaining a property value.
 *
 * @author Kyle Michel
 */
public class Method extends Pio {

    /**
     * Set the name of the method.
     *
     * @param name String with the name of the method.
     * @return This object.
     */
    @JsonSetter(value = "name")
    public Method setName(final String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the name of the method.
     *
     * @return Name of the method.
     */
    @JsonGetter(value = "name")
    public String getName() {
        return this.name;
    }

    /**
     * Set the instruments used in the method.
     *
     * @param instrument List of {@link Instrument} objects for the method.
     */
    @JsonSetter(value = "instrument")
    protected void setInstrument(final List<Instrument> instrument) { // Private since only Jackson should use it
        setInstruments(instruments);
    }

    /**
     * Set the instruments used in the method.
     *
     * @param instruments List of {@link Instrument} objects for the method.
     */
    @JsonSetter(value = "instruments")
    protected void setInstruments(final List<Instrument> instruments) { // Private since only Jackson should use it
        this.instruments = instruments;
    }

    /**
     * Add a single instrument used in the method.
     *
     * @param instrument {@link Instrument} object to add for the method.
     * @return This object.
     */
    public Method addInstrument(final Instrument instrument) {
        if (this.instruments == null) {
            this.instruments = new ArrayList<>();
        }
        this.instruments.add(instrument);
        return this;
    }

    /**
     * Insert a single instrument used in the method at the input index.
     *
     * @param index Index at which to insert the input instrument.
     * @param instrument {@link Instrument} object to add for the method.
     * @return This object.
     */
    public Method addInstrument(final int index, final Instrument instrument) {
        if (this.instruments == null) {
            this.instruments = new ArrayList<>();
        }
        this.instruments.add(index, instrument);
        return this;
    }

    /**
     * Remove an instrument from the method.
     *
     * @param instrument {@link Instrument} object to delete.
     * @return True if the object was removed.
     */
    public boolean removeInstrument(final Instrument instrument) {
        return (this.instruments != null) && this.instruments.remove(instrument);
    }

    /**
     * Get the number of instruments attached to the method.
     *
     * @return Number of instruments for the method.
     */
    public int numInstruments() {
        return (this.instruments == null) ? 0 : this.instruments.size();
    }

    /**
     * Get the instrument at the input index.
     *
     * @param index Index of the instrument to get.
     * @return {@link Instrument} object at the input index.
     * @throws IndexOutOfBoundsException if the input index is out of range of the instrument list.
     */
    @JsonIgnore
    public Instrument getInstrument(final int index) {
        if (this.instruments == null) {
            throw new IndexOutOfBoundsException(
                    "Attempting to access instrument " + index + " of " + this.numInstruments());
        }
        return this.instruments.get(index);
    }

    /**
     * Get an {@link Iterable} object to iterate over the instruments used in the measurement.
     *
     * @return {@link Iterable} object for iterating over instruments.
     */
    public Iterable<Instrument> instruments() {
        return (this.instruments == null) ? Collections.emptyList() : this.instruments;
    }

    /**
     * Get the list of instruments used in the method.
     *
     * @return List of {@link Instrument} objects with instruments used in the method.
     */
    @JsonGetter(value = "instruments")
    protected List<Instrument> getInstruments() { // Private since only Jackson should use it
        return this.instruments;
    }

    /**
     * Set the list of software packages used in the method.
     *
     * @param software List of {@link Software} objects for software packages used in the method.
     */
    @JsonSetter(value = "software")
    protected void setSoftware(final List<Software> software) { // Private since only Jackson should use it
        this.software = software;
    }

    /**
     * Set the list of software packages used in the method.
     *
     * @param softwares List of {@link Software} objects for software packages used in the method.
     */
    @JsonSetter(value = "softwares")
    protected void setSoftwares(final List<Software> softwares) { // Private since only Jackson should use it
        setSoftware(software);
    }

    /**
     * Add a software package that was used in the method.
     *
     * @param software {@link Software} object to add to the method.
     * @return This object.
     */
    public Method addSoftware(final Software software) {
        if (this.software == null) {
            this.software = new ArrayList<>();
        }
        this.software.add(software);
        return this;
    }

    /**
     * Insert a single software package used in the method at the input index.
     *
     * @param index Index at which to insert the input software package.
     * @param software {@link Software} object to add for the method.
     * @return This object.
     */
    public Method addSoftware(final int index, final Software software) {
        if (this.software == null) {
            this.software = new ArrayList<>();
        }
        this.software.add(index, software);
        return this;
    }

    /**
     * Remove a software property from the method.
     *
     * @param software {@link Software} object to delete.
     * @return True if the object was removed.
     */
    public boolean removeSoftware(final Software software) {
        return (this.software != null) && this.software.remove(software);
    }

    /**
     * Get the number of software packages used in the method.
     *
     * @return Number of software packages used in the method.
     */
    public int numSoftware() {
        return (this.instruments == null) ? 0 : this.instruments.size();
    }

    /**
     * Get the software package at the input index.
     *
     * @param index Index of the software package to get.
     * @return {@link Software} object at the input index.
     * @throws IndexOutOfBoundsException if the input index is out of range of the software list.
     */
    @JsonIgnore
    public Software getSoftware(final int index) {
        if (this.software == null) {
            throw new IndexOutOfBoundsException(
                    "Attempting to access software " + index + " of " + this.numSoftware());
        }
        return this.software.get(index);
    }

    /**
     * Get the list of software packages used in the method.
     *
     * @return List of {@link Software} objects used in the method.
     */
    @JsonGetter(value = "software")
    protected List<Software> getSoftware() { // Private since only Jackson should use it
        return this.software;
    }

    /**
     * Get an {@link Iterable} object for iterating over the list of software packages.
     *
     * @return {@link Iterable} object to iterate over software packages.
     */
    public Iterable<Software> software() {
        return (this.software == null) ? Collections.emptyList() : this.software;
    }

    @Override
    @JsonAnySetter
    public Method addUnsupportedField(final String key, final Object value) {
        super.addUnsupportedField(key, value);
        return this;
    }

    @Override
    public Method removeUnsupportedField(final String key) {
        super.removeUnsupportedField(key);
        return this;
    }

    @Override
    public Method clearUnsupportedFields() {
        super.clearUnsupportedFields();
        return this;
    }

    /** Name of the measurement method. */
    private String name;

    /** Instruments used in the measurement. */
    private List<Instrument> instruments;

    /** Software packages used in the measurement. */
    private List<Software> software;
}