package io.citrine.jpif.obj.system.chemical.alloy;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.citrine.jpif.obj.common.Id;
import io.citrine.jpif.obj.common.License;
import io.citrine.jpif.obj.common.Person;
import io.citrine.jpif.obj.common.ProcessStep;
import io.citrine.jpif.obj.common.Property;
import io.citrine.jpif.obj.common.Reference;
import io.citrine.jpif.obj.system.System;
import io.citrine.jpif.obj.system.chemical.ChemicalSystem;
import io.citrine.jpif.obj.system.chemical.common.Composition;

import java.util.ArrayList;
import java.util.List;

/**
 * Information about an alloy.
 *
 * @author Kyle Michel
 */
@JsonTypeName("system.chemical.alloy")
public class Alloy extends ChemicalSystem {

    /**
     * Add a phase to this alloy.
     *
     * @param phase {@link AlloyPhase} object with the phase to add.
     * @return This object.
     */
    public Alloy addPhase(final AlloyPhase phase) {
        super.addSubSystem(phase);
        return this;
    }

    /**
     * Insert a single phase for this alloy.
     *
     * @param index Index at which to insert the input phase.
     * @param phase {@link AlloyPhase} object to add to this alloy.
     * @return This object.
     */
    public Alloy addPhase(final int index, final AlloyPhase phase) {
        super.addSubSystem(index, phase);
        return this;
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

    @Override
    @JsonSetter(value = "chemicalFormula")
    public Alloy setChemicalFormula(final String chemicalFormula) {
        super.setChemicalFormula(chemicalFormula);
        return this;
    }

    @Override
    public Alloy addComposition(final Composition composition) {
        super.addComposition(composition);
        return this;
    }

    @Override
    public Alloy addComposition(final int index, final Composition composition) {
        super.addComposition(index, composition);
        return this;
    }

    @Override
    public Alloy addReference(final Reference reference) {
        super.addReference(reference);
        return this;
    }

    @Override
    public Alloy addReference(final int index, final Reference reference) {
        super.addReference(index, reference);
        return this;
    }

    @Override
    public Alloy addContact(final Person contact) {
        super.addContact(contact);
        return this;
    }

    @Override
    public Alloy addContact(final int index, final Person contact) {
        super.addContact(index, contact);
        return this;
    }

    @Override
    public Alloy addLicense(final License license) {
        super.addLicense(license);
        return this;
    }

    @Override
    public Alloy addLicense(final int index, final License license) {
        super.addLicense(index, license);
        return this;
    }

    @Override
    public Alloy addName(final String name) {
        super.addName(name);
        return this;
    }

    @Override
    public Alloy addName(final int index, final String name) {
        super.addName(index, name);
        return this;
    }

    @Override
    public Alloy addId(final Id id) {
        super.addId(id);
        return this;
    }

    @Override
    public Alloy addId(final int index, final Id id) {
        super.addId(index, id);
        return this;
    }

    @Override
    public Alloy addProperty(final Property property) {
        super.addProperty(property);
        return this;
    }

    @Override
    public Alloy addProperty(final int index, final Property property) {
        super.addProperty(index, property);
        return this;
    }

    @Override
    public Alloy addPreparation(final ProcessStep preparation) {
        super.addPreparation(preparation);
        return this;
    }

    @Override
    public Alloy addPreparation(final int index, final ProcessStep preparation) {
        super.addPreparation(index, preparation);
        return this;
    }

    @Override
    public Alloy addSubSystem(final System subSystem) {
        super.addSubSystem(subSystem);
        return this;
    }

    @Override
    public Alloy addSubSystem(final int index, final System subSystem) {
        super.addSubSystem(index, subSystem);
        return this;
    }

    @Override
    @JsonAnySetter
    public Alloy addUnsupportedField(final String key, final Object value) {
        super.addUnsupportedField(key, value);
        return this;
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