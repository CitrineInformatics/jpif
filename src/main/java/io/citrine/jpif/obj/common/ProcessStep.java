package io.citrine.jpif.obj.common;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Information about a single step in a processing pipeline.
 *
 * <p>Supported fields:
 * <ul>
 *     <li>name - Name of the process step.
 *     <li>details - List of details ({@link Value}s) of the process step.
 *     <li>instruments - List of {@link Instrument}s that were used in the process step.
 *     <li>software - List of {@link Software} packages that were used in the process step.
 *     <li>tags - List of tags that apply to the process step.
 * </ul>
 *
 * @author Kyle Michel
 */
public class ProcessStep extends Pio {

    /**
     * Set the name of this process step.
     *
     * @param name String with the name of this process step.
     * @return This object.
     */
    @JsonSetter(value = "name")
    public ProcessStep setName(final String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the name of this process step.
     *
     * @return Name of this process step.
     */
    @JsonGetter(value = "name")
    public String getName() {
        return this.name;
    }

    /**
     * Set the details of the process step.
     *
     * @param details List of {@link Value} objects describing this process step.
     */
    @JsonSetter(value = "details")
    protected void setDetails(final List<Value> details) { // Private since only Jackson should use it
        this.details = details;
    }

    /**
     * Set the details of the process step.
     *
     * @param detail List of {@link Value} objects describing this process step.
     */
    @JsonSetter(value = "detail")
    protected void setDetail(final List<Value> detail) { // Private since only Jackson should use it
        setDetails(detail);
    }

    /**
     * Add single detail to the process step.
     *
     * @param detail {@link Value} object to add to the process step..
     * @return This object.
     */
    public ProcessStep addDetail(final Value detail) {
        if (this.details == null) {
            this.details = new ArrayList<>();
        }
        this.details.add(detail);
        return this;
    }

    /**
     * Insert a single detail of the process step at the input index.
     *
     * @param index Index at which to insert the input detail.
     * @param detail {@link Value} object to add to the process step.
     * @return This object.
     */
    public ProcessStep addDetail(final int index, final Value detail) {
        if (this.details == null) {
            this.details = new ArrayList<>();
        }
        this.details.add(index, detail);
        return this;
    }

    /**
     * Remove a detail from the step.
     *
     * @param detail {@link Value} object to delete.
     * @return True if the object was removed.
     */
    public boolean removeDetail(final Value detail) {
        return (this.details != null) && this.details.remove(detail);
    }

    /**
     * Get the number of details attached to this process step.
     *
     * @return Number of details for the process step.
     */
    public int numDetails() {
        return (this.details == null) ? 0 : this.details.size();
    }

    /**
     * Get the process step detail at the input index.
     *
     * @param index Index of the detail to get.
     * @return {@link Value} object at the input index.
     * @throws IndexOutOfBoundsException if the input index is out of range of the details list.
     */
    @JsonIgnore
    public Value getDetail(final int index) {
        if (this.details == null) {
            throw new IndexOutOfBoundsException(
                    "Attempting to access detail " + index + " of " + this.numDetails());
        }
        return this.details.get(index);
    }

    /**
     * Get an {@link Iterable} object to iterate over the details of the process step.
     *
     * @return {@link Iterable} object for iterating over details of the process step.
     */
    public Iterable<Value> details() {
        return (this.details == null) ? Collections.emptyList() : this.details;
    }

    /**
     * Get the list of details of the process step.
     *
     * @return List of {@link Value} objects with details of the process step.
     */
    @JsonGetter(value = "details")
    protected List<Value> getDetails() { // Private since only Jackson should use it
        return this.details;
    }

    /**
     * Set the instruments used in the process step.
     *
     * @param instruments List of {@link Instrument} objects used in this process step.
     */
    @JsonSetter(value = "instruments")
    protected void setInstruments(final List<Instrument> instruments) { // Private since only Jackson should use it
        this.instruments = instruments;
    }

    /**
     * Set the instruments used in the process step.
     *
     * @param instruments List of {@link Instrument} objects used in this process step.
     */
    @JsonSetter(value = "instrument")
    protected void setInstrument(final List<Instrument> instruments) { // Private since only Jackson should use it
        setInstruments(instruments);
    }

    /**
     * Add single instruments to the process step.
     *
     * @param instrument {@link Instrument} object to add to the process step.
     * @return This object.
     */
    public ProcessStep addInstrument(final Instrument instrument) {
        if (this.instruments == null) {
            this.instruments = new ArrayList<>();
        }
        this.instruments.add(instrument);
        return this;
    }

    /**
     * Insert a single instrument for the process step at the input index.
     *
     * @param index Index at which to insert the input instrument.
     * @param instrument {@link Instrument} object to add to the process step.
     * @return This object.
     */
    public ProcessStep addInstrument(final int index, final Instrument instrument) {
        if (this.instruments == null) {
            this.instruments = new ArrayList<>();
        }
        this.instruments.add(index, instrument);
        return this;
    }

    /**
     * Remove an instrument from the step.
     *
     * @param instrument {@link Instrument} object to delete.
     * @return True if the object was removed.
     */
    public boolean removeInstrument(final Instrument instrument) {
        return (this.instruments != null) && this.instruments.remove(instrument);
    }

    /**
     * Get the number of instruments attached to this process step.
     *
     * @return Number of instruments for the process step.
     */
    public int numInstruments() {
        return (this.instruments == null) ? 0 : this.instruments.size();
    }

    /**
     * Get the process step instrument at the input index.
     *
     * @param index Index of the instrument to get.
     * @return {@link Instrument} object at the input index.
     * @throws IndexOutOfBoundsException if the input index is out of range of the instruments list.
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
     * Get an {@link Iterable} object to iterate over the instruments of the process step.
     *
     * @return {@link Iterable} object for iterating over instruments of the process step.
     */
    public Iterable<Instrument> instruments() {
        return (this.instruments == null) ? Collections.emptyList() : this.instruments;
    }

    /**
     * Get the list of instruments used in the process step.
     *
     * @return List of {@link Instrument} objects with instruments of the process step.
     */
    @JsonGetter(value = "instruments")
    protected List<Instrument> getInstruments() { // Private since only Jackson should use it
        return this.instruments;
    }

    /**
     * Set the software used in the process step.
     *
     * @param software List of {@link Software} objects used in this process step.
     */
    @JsonSetter(value = "software")
    protected void setSoftware(final List<Software> software) { // Private since only Jackson should use it
        this.software = software;
    }

    /**
     * Set the software used in the process step.
     *
     * @param softwares sList of {@link Software} objects used in this process step.
     */
    @JsonSetter(value = "softwares")
    protected void setSoftwares(final List<Software> softwares) { // Private since only Jackson should use it
        setSoftware(softwares);
    }

    /**
     * Add single software to the process step.
     *
     * @param software {@link Software} object to add to the process step.
     * @return This object.
     */
    public ProcessStep addSoftware(final Software software) {
        if (this.software == null) {
            this.software = new ArrayList<>();
        }
        this.software.add(software);
        return this;
    }

    /**
     * Insert a single software for the process step at the input index.
     *
     * @param index Index at which to insert the input software.
     * @param software {@link Software} object to add to the process step.
     * @return This object.
     */
    public ProcessStep addSoftware(final int index, final Software software) {
        if (this.software == null) {
            this.software = new ArrayList<>();
        }
        this.software.add(index, software);
        return this;
    }

    /**
     * Remove an software from the step.
     *
     * @param software {@link Software} object to delete.
     * @return True if the object was removed.
     */
    public boolean removeSoftware(final Software software) {
        return (this.software != null) && this.software.remove(software);
    }

    /**
     * Get the number of software packages attached to this process step.
     *
     * @return Number of software packages for the process step.
     */
    public int numSoftware() {
        return (this.software == null) ? 0 : this.software.size();
    }

    /**
     * Get the process step software at the input index.
     *
     * @param index Index of the software to get.
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
     * Get the list of software used in the process step.
     *
     * @return List of {@link Software} objects with software of the process step.
     */
    @JsonGetter(value = "software")
    protected List<Software> getSoftware() { // Private since only Jackson should use it
        return this.software;
    }

    /**
     * Get an {@link Iterable} object to iterate over the software packages of the process step.
     *
     * @return {@link Iterable} object for iterating over software of the process step.
     */
    public Iterable<Software> software() {
        return (this.software == null) ? Collections.emptyList() : this.software;
    }

    @Override
    public ProcessStep addTag(final String tag) {
        super.addTag(tag);
        return this;
    }

    @Override
    public ProcessStep addTag(final int index, final String tag) {
        super.addTag(index, tag);
        return this;
    }

    @Override
    @JsonAnySetter
    public ProcessStep addUnsupportedField(final String key, final Object value) {
        super.addUnsupportedField(key, value);
        return this;
    }

    /** Name of the process step. */
    private String name;

    /** List of details of the process step. */
    private List<Value> details;

    /** List of instruments used in the process step. */
    private List<Instrument> instruments;

    /** List of software that was used in the process step. */
    private List<Software> software;
}