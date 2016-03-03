package io.citrine.jpif.obj.system.chemical;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonGetter;
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
import io.citrine.jpif.obj.system.chemical.common.Composition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Base representation for the general chemical system.
 *
 * @author Kyle Michel
 */
@JsonTypeName("system.chemical")
public class ChemicalSystem extends System {

    /**
     * Set the chemical formula for this system.
     *
     * @param chemicalFormula String with the chemical formula for this system.
     * @return This object.
     */
    @JsonSetter(value = "chemicalFormula")
    public ChemicalSystem setChemicalFormula(final String chemicalFormula) {
        this.chemicalFormula = chemicalFormula;
        return this;
    }

    /**
     * Get the chemical formula for this system.
     *
     * @return String with the chemical formula for this system.
     */
    @JsonGetter(value = "chemicalFormula")
    public String getChemicalFormula() {
        return this.chemicalFormula;
    }

    /**
     * Set the list of compositions of this system.
     *
     * @param composition List of {@link Composition} objects with the composition of this system.
     */
    @JsonSetter(value = "composition")
    protected void setComposition(final List<Composition> composition) { // Private since only Jackson should use it
        this.composition = composition;
    }

    /**
     * Set the list of compositions of this system.
     *
     * @param compositions List of {@link Composition} objects with the composition of this system.
     */
    @JsonSetter(value = "compositions")
    protected void setCompositions(final List<Composition> compositions) { // Private since only Jackson should use it
        setComposition(compositions);
    }

    /**
     * Add to the composition for this system.
     *
     * @param composition {@link Composition} object with the composition to add.
     * @return This object.
     */
    public ChemicalSystem addComposition(final Composition composition) {
        if (this.composition == null) {
            this.composition = new ArrayList<>();
        }
        this.composition.add(composition);
        return this;
    }

    /**
     * Insert a single composition for this system.
     *
     * @param index Index at which to insert the input composition.
     * @param composition {@link Composition} object to add to this system.
     * @return This object.
     */
    public ChemicalSystem addComposition(final int index, final Composition composition) {
        if (this.composition == null) {
            this.composition = new ArrayList<>();
        }
        this.composition.add(index, composition);
        return this;
    }

    /**
     * Remove a composition from the system.
     *
     * @param composition {@link Composition} object to delete.
     * @return True if the object was removed.
     */
    public boolean removeComposition(final Composition composition) {
        return (this.composition != null) && this.composition.remove(composition);
    }

    /**
     * Get the number of elements in the composition vector for this system.
     *
     * @return Number of elements in the composition vector for this system.
     */
    public int compositionLength() {
        return (this.composition == null) ? 0 : this.composition.size();
    }

    /**
     * Get a composition for this system at a set index.
     *
     * @param index Index of the composition to get.
     * @return {@link Composition} object with the composition at the input index.
     * @throws IndexOutOfBoundsException if the index is out of range of the composition list.
     */
    @JsonIgnore
    public Composition getComposition(final int index) {
        if (this.composition == null) {
            throw new IndexOutOfBoundsException("Attempting to access composition " + index
                    + " of " + this.compositionLength());
        }
        return this.composition.get(index);
    }

    /**
     * Get the list of composition values for this system.
     *
     * @return List of {@link Composition} objects with composition for this system.
     */
    @JsonGetter(value = "composition")
    protected List<Composition> getComposition() { // Private since only Jackson should use it
        return this.composition;
    }

    /**
     * Get an {@link Iterable} object to iterate over values in the composition vector of this system.
     *
     * @return {@link Iterable} object for iterating over values in the composition vector of this system.
     */
    public Iterable<Composition> composition() {
        return (this.composition == null) ? Collections.emptyList() : this.composition;
    }

    @Override
    public ChemicalSystem addReference(final Reference reference) {
        super.addReference(reference);
        return this;
    }

    @Override
    public ChemicalSystem addReference(final int index, final Reference reference) {
        super.addReference(index, reference);
        return this;
    }

    @Override
    public ChemicalSystem addContact(final Person contact) {
        super.addContact(contact);
        return this;
    }

    @Override
    public ChemicalSystem addContact(final int index, final Person contact) {
        super.addContact(index, contact);
        return this;
    }

    @Override
    public ChemicalSystem addLicense(final License license) {
        super.addLicense(license);
        return this;
    }

    @Override
    public ChemicalSystem addLicense(final int index, final License license) {
        super.addLicense(index, license);
        return this;
    }

    @Override
    public ChemicalSystem addName(final String name) {
        super.addName(name);
        return this;
    }

    @Override
    public ChemicalSystem addName(final int index, final String name) {
        super.addName(index, name);
        return this;
    }

    @Override
    public ChemicalSystem addId(final Id id) {
        super.addId(id);
        return this;
    }

    @Override
    public ChemicalSystem addId(final int index, final Id id) {
        super.addId(index, id);
        return this;
    }

    @Override
    public ChemicalSystem addProperty(final Property property) {
        super.addProperty(property);
        return this;
    }

    @Override
    public ChemicalSystem addProperty(final int index, final Property property) {
        super.addProperty(index, property);
        return this;
    }

    @Override
    public ChemicalSystem addPreparation(final ProcessStep preparation) {
        super.addPreparation(preparation);
        return this;
    }

    @Override
    public ChemicalSystem addPreparation(final int index, final ProcessStep preparation) {
        super.addPreparation(index, preparation);
        return this;
    }

    @Override
    public ChemicalSystem addSubSystem(final System subSystem) {
        super.addSubSystem(subSystem);
        return this;
    }

    @Override
    public ChemicalSystem addSubSystem(final int index, final System subSystem) {
        super.addSubSystem(index, subSystem);
        return this;
    }

    @Override
    @JsonAnySetter
    public ChemicalSystem addUnsupportedField(final String key, final Object value) {
        super.addUnsupportedField(key, value);
        return this;
    }

    /** Chemical formula. */
    private String chemicalFormula;

    /** Composition vector. */
    private List<Composition> composition;
}