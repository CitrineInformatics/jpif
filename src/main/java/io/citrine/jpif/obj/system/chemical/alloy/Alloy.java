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

/**
 * Information about an alloy.
 *
 * <p>Supported fields:
 * <ul>
 *     <li>chemicalFormula - Chemical formula of the system.
 *     <li>composition - List of {@link Composition} objects defining the composition vector of the system.
 *     <li>names - Names of the system.
 *     <li>ids - List of {@link Id}s of the system.
 *     <li>properties - List of measured or calculated properties ({@link Property}) of the system.
 *     <li>preparation - List of preparation steps ({@link ProcessStep}) describing the making of the system.
 *     <li>subSystems - List of sub-systems ({@link System}) of the system.
 *     <li>references - List of {@link Reference}s with information about the system.
 *     <li>contacts - List of contacts ({@link Person}) for information about the system.
 *     <li>licenses - List of {@link License}s that apply to the system.
 * </ul>
 *
 * @author Kyle Michel
 */
@JsonTypeName("system.chemical.alloy")
public class Alloy extends ChemicalSystem {

    /**
     * Add a phase to this alloy.
     *
     * @param phase {@link System} object with the phase to add.
     * @return This object.
     */
    public Alloy addPhase(final System phase) {
        super.addSubSystem(phase);
        return this;
    }

    /**
     * Insert a single phase for this alloy.
     *
     * @param index Index at which to insert the input phase.
     * @param phase {@link System} object to add to this alloy.
     * @return This object.
     */
    public Alloy addPhase(final int index, final System phase) {
        super.addSubSystem(index, phase);
        return this;
    }

    /**
     * Remove a phase from the system.
     *
     * @param phase {@link System} object to delete.
     * @return True if the object was removed.
     */
    public boolean removePhase(final System phase) {
        return removeSubsystem(phase);
    }

    /**
     * Get the number of phases in this alloy.
     *
     * @return Number of phases in this alloy.
     */
    public int numPhases() {
        return this.numSubSystems();
    }

    /**
     * Get a phase in this alloy at a set index.
     *
     * @param index Index of the alloy to get.
     * @return {@link System} object with the system at the input index.
     * @throws IndexOutOfBoundsException if the index is out of range of the phase list.
     */
    @JsonIgnore
    public System getPhase(final int index) {
        return super.getSubSystem(index);
    }

    /**
     * Get an {@link Iterable} object to iterate over phases in this alloy.
     *
     * @return {@link Iterable} object for iterating over phases in this alloy.
     */
    public Iterable<System> phases() {
        return super.subSystems();
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
    public Alloy addTag(final String tag) {
        super.addTag(tag);
        return this;
    }

    @Override
    public Alloy addTag(final int index, final String tag) {
        super.addTag(index, tag);
        return this;
    }

    @Override
    @JsonAnySetter
    public Alloy addUnsupportedField(final String key, final Object value) {
        super.addUnsupportedField(key, value);
        return this;
    }
}