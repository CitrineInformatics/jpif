package io.citrine.jpif.obj.system.chemical.alloy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.citrine.jpif.obj.system.System;
import io.citrine.jpif.obj.system.chemical.ChemicalSystem;

import java.util.ArrayList;
import java.util.List;

/**
 * Information about an alloy.
 *
 * @author Kyle Michel
 */
@JsonTypeName("system.chemical.alloy")
public class Alloy<T extends Alloy<T>> extends ChemicalSystem<T> {

    /**
     * Add a phase to this alloy.
     *
     * @param phase {@link AlloyPhase} object with the phase to add.
     * @return This object.
     */
    @SuppressWarnings("unchecked")
    public T addPhase(final AlloyPhase phase) {
        super.addSubSystem(phase);
        return (T) this;
    }

    /**
     * Insert a single phase for this alloy.
     *
     * @param index Index at which to insert the input phase.
     * @param phase {@link AlloyPhase} object to add to this alloy.
     * @return This object.
     */
    @SuppressWarnings("unchecked")
    public T addPhase(final int index, final AlloyPhase phase) {
        super.addSubSystem(index, phase);
        return (T) this;
    }

    /**
     * Remove a phase from the system.
     *
     * @param phase {@link AlloyPhase} object to delete.
     * @return True if the object was removed.
     */
    public boolean removePhase(final AlloyPhase phase) {
        return removeSubsystem(phase);
    }

    /**
     * Get the number of phases in this alloy.
     *
     * @return Number of phases in this alloy.
     */
    public int numPhases() {
        updatePhases();
        return this.phases.size();
    }

    /**
     * Get a phase in this alloy at a set index.
     *
     * @param index Index of the alloy to get.
     * @return {@link AlloyPhase} object with the phase at the input index.
     * @throws IndexOutOfBoundsException if the index is out of range of the phase list.
     */
    @JsonIgnore
    public AlloyPhase getPhase(final int index) {
        updatePhases();
        return this.phases.get(index);
    }

    /**
     * Get an {@link Iterable} object to iterate over phases in this alloy.
     *
     * @return {@link Iterable} object for iterating over phases in this alloy.
     */
    public Iterable<AlloyPhase> phases() {
        updatePhases();
        return this.phases;
    }

    /**
     * Helper function that updates the list of phases in this alloy based on the current subsystems in the system.
     */
    protected void updatePhases() {
        this.phases = new ArrayList<>();
        for (System i : subSystems()) {
            if (i instanceof AlloyPhase) {
                this.phases.add((AlloyPhase) i);
            }
        }
    }

    /** List of phases in this alloy. */
    private List<AlloyPhase> phases;
}