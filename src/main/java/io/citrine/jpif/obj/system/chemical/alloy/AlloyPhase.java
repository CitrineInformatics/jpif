package io.citrine.jpif.obj.system.chemical.alloy;

import com.fasterxml.jackson.annotation.JsonAnySetter;
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
 * Information about a single phase in an alloy.
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
@JsonTypeName("system.chemical.alloy.phase")
public class AlloyPhase extends ChemicalSystem {

    @Override
    @JsonSetter(value = "chemicalFormula")
    public AlloyPhase setChemicalFormula(final String chemicalFormula) {
        super.setChemicalFormula(chemicalFormula);
        return this;
    }

    @Override
    public AlloyPhase addComposition(final Composition composition) {
        super.addComposition(composition);
        return this;
    }

    @Override
    public AlloyPhase addComposition(final int index, final Composition composition) {
        super.addComposition(index, composition);
        return this;
    }

    @Override
    public AlloyPhase addReference(final Reference reference) {
        super.addReference(reference);
        return this;
    }

    @Override
    public AlloyPhase addReference(final int index, final Reference reference) {
        super.addReference(index, reference);
        return this;
    }

    @Override
    public AlloyPhase addContact(final Person contact) {
        super.addContact(contact);
        return this;
    }

    @Override
    public AlloyPhase addContact(final int index, final Person contact) {
        super.addContact(index, contact);
        return this;
    }

    @Override
    public AlloyPhase addLicense(final License license) {
        super.addLicense(license);
        return this;
    }

    @Override
    public AlloyPhase addLicense(final int index, final License license) {
        super.addLicense(index, license);
        return this;
    }

    @Override
    public AlloyPhase addName(final String name) {
        super.addName(name);
        return this;
    }

    @Override
    public AlloyPhase addName(final int index, final String name) {
        super.addName(index, name);
        return this;
    }

    @Override
    public AlloyPhase addId(final Id id) {
        super.addId(id);
        return this;
    }

    @Override
    public AlloyPhase addId(final int index, final Id id) {
        super.addId(index, id);
        return this;
    }

    @Override
    public AlloyPhase addProperty(final Property property) {
        super.addProperty(property);
        return this;
    }

    @Override
    public AlloyPhase addProperty(final int index, final Property property) {
        super.addProperty(index, property);
        return this;
    }

    @Override
    public AlloyPhase addPreparation(final ProcessStep preparation) {
        super.addPreparation(preparation);
        return this;
    }

    @Override
    public AlloyPhase addPreparation(final int index, final ProcessStep preparation) {
        super.addPreparation(index, preparation);
        return this;
    }

    @Override
    public AlloyPhase addSubSystem(final System subSystem) {
        super.addSubSystem(subSystem);
        return this;
    }

    @Override
    public AlloyPhase addSubSystem(final int index, final System subSystem) {
        super.addSubSystem(index, subSystem);
        return this;
    }

    @Override
    @JsonAnySetter
    public AlloyPhase addUnsupportedField(final String key, final Object value) {
        super.addUnsupportedField(key, value);
        return this;
    }
}