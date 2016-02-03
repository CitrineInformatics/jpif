package io.citrine.jpif.obj.system.chemical.alloy;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.citrine.jpif.obj.common.Id;
import io.citrine.jpif.obj.common.License;
import io.citrine.jpif.obj.common.Person;
import io.citrine.jpif.obj.common.ProcessStep;
import io.citrine.jpif.obj.common.Property;
import io.citrine.jpif.obj.common.Reference;
import io.citrine.jpif.obj.system.System;
import io.citrine.jpif.obj.system.chemical.ChemicalSystem;

/**
 * Information about a single phase in an alloy.
 *
 * @author Kyle Michel
 */
@JsonTypeName("system.chemical.alloy.phase")
public class AlloyPhase extends ChemicalSystem {

    @Override
    public AlloyPhase addName(final String name) {
        super.addName(name);
        return this;
    }

    @Override
    public AlloyPhase addId(final Id id) {
        super.addId(id);
        return this;
    }

    @Override
    public AlloyPhase addProperty(final Property property) {
        super.addProperty(property);
        return this;
    }

    @Override
    public AlloyPhase addPreparation(final ProcessStep preparation) {
        super.addPreparation(preparation);
        return this;
    }

    @Override
    public AlloyPhase addSubSystem(final System subSystem) {
        super.addSubSystem(subSystem);
        return this;
    }

    @Override
    public AlloyPhase addReference(final Reference reference) {
        super.addReference(reference);
        return this;
    }

    @Override
    public AlloyPhase addContact(final Person contact) {
        super.addContact(contact);
        return this;
    }

    @Override
    public AlloyPhase addLicense(final License license) {
        super.addLicense(license);
        return this;
    }

    @Override
    @JsonAnySetter
    public AlloyPhase addUnsupportedField(final String key, final Object value) {
        super.addUnsupportedField(key, value);
        return this;
    }

    @Override
    public AlloyPhase removeUnsupportedField(final String key) {
        super.removeUnsupportedField(key);
        return this;
    }

    @Override
    public AlloyPhase clearUnsupportedFields() {
        super.clearUnsupportedFields();
        return this;
    }
}