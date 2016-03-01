package io.citrine.jpif.obj.common;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Information about a single step in a processing pipeline.
 *
 * @author Kyle Michel
 */
public class ProcessStep extends Pio<ProcessStep> {

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
     * @param details List of {@link AbstractValue} objects describing this process step.
     */
    @JsonSetter(value = "details")
    protected void setDetails(final List<AbstractValue> details) { // Private since only Jackson should use it
        this.details = details;
    }

    /**
     * Set the details of the process step.
     *
     * @param detail List of {@link AbstractValue} objects describing this process step.
     */
    @JsonSetter(value = "detail")
    protected void setDetail(final List<AbstractValue> detail) { // Private since only Jackson should use it
        setDetails(details);
    }

    /**
     * Add single detail to the process step.
     *
     * @param detail {@link AbstractValue} object to add to the process step..
     * @return This object.
     */
    public ProcessStep addDetail(final AbstractValue detail) {
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
     * @param detail {@link AbstractValue} object to add to the process step.
     * @return This object.
     */
    public ProcessStep addDetail(final int index, final AbstractValue detail) {
        if (this.details == null) {
            this.details = new ArrayList<>();
        }
        this.details.add(index, detail);
        return this;
    }

    /**
     * Remove a detail from the step.
     *
     * @param detail {@link AbstractValue} object to delete.
     * @return True if the object was removed.
     */
    public boolean removeDetail(final AbstractValue detail) {
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
     * @return {@link AbstractValue} object at the input index.
     * @throws IndexOutOfBoundsException if the input index is out of range of the details list.
     */
    @JsonIgnore
    public AbstractValue getDetail(final int index) {
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
    public Iterable<AbstractValue> details() {
        return (this.details == null) ? Collections.emptyList() : this.details;
    }

    /**
     * Get the list of details of the process step.
     *
     * @return List of {@link AbstractValue} objects with details of the process step.
     */
    @JsonGetter(value = "details")
    protected List<AbstractValue> getDetails() { // Private since only Jackson should use it
        return this.details;
    }

    /** Name of the process step. */
    private String name;

    /** List of details of the process step. */
    private List<AbstractValue> details;
}