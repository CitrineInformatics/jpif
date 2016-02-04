package io.citrine.jpif.obj.system;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.citrine.jpif.obj.common.Id;
import io.citrine.jpif.obj.common.License;
import io.citrine.jpif.obj.common.Person;
import io.citrine.jpif.obj.common.ProcessStep;
import io.citrine.jpif.obj.common.Property;
import io.citrine.jpif.obj.common.Rcl;
import io.citrine.jpif.obj.common.Reference;
import io.citrine.jpif.obj.system.chemical.ChemicalSystem;
import io.citrine.jpif.obj.system.chemical.alloy.Alloy;
import io.citrine.jpif.obj.system.chemical.alloy.AlloyPhase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Base representation for all systems.
 *
 * @author Kyle Michel
 */
@JsonTypeName("system")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "category")
@JsonSubTypes({
        @JsonSubTypes.Type(value = System.class),
        @JsonSubTypes.Type(value = ChemicalSystem.class),
        @JsonSubTypes.Type(value = Alloy.class),
        @JsonSubTypes.Type(value = AlloyPhase.class)})
public class System extends Rcl {

    /**
     * Set the list of names of this system.
     *
     * @param names List of strings with the names of this system.
     */
    @JsonSetter(value = "names")
    protected void setNames(final List<String> names) { // Private since only Jackson should use it
        this.names = names;
    }

    /**
     * Add a name for this system.
     *
     * @param name String with the name to add.
     * @return This object.
     */
    public System addName(final String name) {
        if (this.names == null) {
            this.names = new ArrayList<>();
        }
        this.names.add(name);
        return this;
    }

    /**
     * Get the number of names for this system.
     *
     * @return Number of names for this system.
     */
    public int numNames() {
        return (this.names == null) ? 0 : this.names.size();
    }

    /**
     * Get a name for this system at a set index.
     *
     * @param index Index of the name to get.
     * @return String with the name at the input index.
     * @throws IndexOutOfBoundsException if the index is out of range of the names list.
     */
    @JsonIgnore
    public String getName(final int index) {
        if (this.names == null) {
            throw new IndexOutOfBoundsException("Attempting to access name " + index + " of " + this.numNames());
        }
        return this.names.get(index);
    }

    /**
     * Get an {@link Iterable} object to iterate over names of this system.
     *
     * @return {@link Iterable} object for iterating over names of this system.
     */
    public Iterable<String> names() {
        return (this.names == null) ? Collections.emptyList() : this.names;
    }

    /**
     * Get the list of names for this system.
     *
     * @return List of strings with names for this system.
     */
    @JsonGetter(value = "names")
    protected List<String> getNames() { // Private since only Jackson should use it
        return this.names;
    }

    /**
     * Set the list of IDs of this system.
     *
     * @param ids List of {@link Id} objects with the IDs of this system.
     */
    @JsonSetter(value = "ids")
    @JsonDeserialize(contentUsing = Id.Deserializer.class)
    protected void setIds(final List<Id> ids) { // Private since only Jackson should use it
        this.ids = ids;
    }

    /**
     * Add an ID for this system.
     *
     * @param id {@link Id} object with the ID to add.
     * @return This object.
     */
    public System addId(final Id id) {
        if (this.ids == null) {
            this.ids = new ArrayList<>();
        }
        this.ids.add(id);
        return this;
    }

    /**
     * Get the number of IDs for this system.
     *
     * @return Number of IDs for this system.
     */
    public int numIds() {
        return (this.ids == null) ? 0 : this.ids.size();
    }

    /**
     * Get an ID for this system at a set index.
     *
     * @param index Index of the ID to get.
     * @return {@link Id} object with the ID at the input index.
     * @throws IndexOutOfBoundsException if the index is out of range of the IDs list.
     */
    @JsonIgnore
    public Id getId(final int index) {
        if (this.ids == null) {
            throw new IndexOutOfBoundsException("Attempting to access ID " + index + " of " + this.numIds());
        }
        return this.ids.get(index);
    }

    /**
     * Get an {@link Iterable} object to iterate over IDs of this system.
     *
     * @return {@link Iterable} object for iterating over IDs of this system.
     */
    public Iterable<Id> ids() {
        return (this.ids == null) ? Collections.emptyList() : this.ids;
    }

    /**
     * Get the list of IDs for this system.
     *
     * @return List of {@link Id} objects with IDs for this system.
     */
    @JsonGetter(value = "ids")
    protected List<Id> getIds() { // Private since only Jackson should use it
        return this.ids;
    }

    /**
     * Set the list of properties of this system.
     *
     * @param properties List of {@link Property} objects with the properties of this system.
     */
    @JsonSetter(value = "properties")
    protected void setProperties(final List<Property> properties) { // Private since only Jackson should use it
        this.properties = properties;
    }

    /**
     * Add a property for this system.
     *
     * @param property {@link Property} object with the property to add.
     * @return This object.
     */
    public System addProperty(final Property property) {
        if (this.properties == null) {
            this.properties = new ArrayList<>();
        }
        this.properties.add(property);
        return this;
    }

    /**
     * Get the number of properties for this system.
     *
     * @return Number of properties for this system.
     */
    public int numProperties() {
        return (this.properties == null) ? 0 : this.properties.size();
    }

    /**
     * Get a property for this system at a set index.
     *
     * @param index Index of the property to get.
     * @return {@link Property} object with the property at the input index.
     * @throws IndexOutOfBoundsException if the index is out of range of the properties list.
     */
    @JsonIgnore
    public Property getProperty(final int index) {
        if (this.properties == null) {
            throw new IndexOutOfBoundsException("Attempting to access property " + index
                    + " of " + this.numProperties());
        }
        return this.properties.get(index);
    }

    /**
     * Get an {@link Iterable} object to iterate over properties of this system.
     *
     * @return {@link Iterable} object for iterating over properties of this system.
     */
    public Iterable<Property> properties() {
        return (this.properties == null) ? Collections.emptyList() : this.properties;
    }

    /**
     * Get the list of properties for this system.
     *
     * @return List of {@link Property} objects with properties for this system.
     */
    @JsonGetter(value = "properties")
    protected List<Property> getProperties() { // Private since only Jackson should use it
        return this.properties;
    }

    /**
     * Set the list of preparation steps of this system.
     *
     * @param preparation List of {@link ProcessStep} objects with the processing steps of this system.
     */
    @JsonSetter(value = "preparation")
    protected void setPreparation(final List<ProcessStep> preparation) { // Private since only Jackson should use it
        this.preparation = preparation;
    }

    /**
     * Add a preparation step for this system.
     *
     * @param preparation {@link ProcessStep} object with the preparation step to add.
     * @return This object.
     */
    public System addPreparation(final ProcessStep preparation) {
        if (this.preparation == null) {
            this.preparation = new ArrayList<>();
        }
        this.preparation.add(preparation);
        return this;
    }

    /**
     * Get the number of preparation steps for this system.
     *
     * @return Number of preparation steps for this system.
     */
    public int numPreparation() {
        return (this.preparation == null) ? 0 : this.preparation.size();
    }

    /**
     * Get a preparation step for this system at a set index.
     *
     * @param index Index of the preparation step to get.
     * @return {@link ProcessStep} object with the preparation step at the input index.
     * @throws IndexOutOfBoundsException if the index is out of range of the preparation list.
     */
    @JsonIgnore
    public ProcessStep getPreparation(final int index) {
        if (this.preparation == null) {
            throw new IndexOutOfBoundsException("Attempting to access ID " + index + " of " + this.numPreparation());
        }
        return this.preparation.get(index);
    }

    /**
     * Get the list of preparation steps for this system.
     *
     * @return List of {@link ProcessStep} objects with preparation steps for this system.
     */
    @JsonGetter(value = "preparation")
    protected List<ProcessStep> getPreparation() { // Private since only Jackson should use it
        return this.preparation;
    }

    /**
     * Get an {@link Iterable} object to iterate over preparation steps of this system.
     *
     * @return {@link Iterable} object for iterating over preparation steps of this system.
     */
    public Iterable<ProcessStep> preparation() {
        return (this.preparation == null) ? Collections.emptyList() : this.preparation;
    }

    /**
     * Set the list of subsystems of this system.
     *
     * @param subSystems List of {@link System} objects with the subsystems of this system.
     */
    @JsonSetter(value = "subSystems")
    protected void setSubSystems(final List<System> subSystems) { // Private since only Jackson should use it
        this.subSystems = subSystems;
    }

    /**
     * Add a subsystem for this system.
     *
     * @param subSystem {@link System} object with the subsystem to add.
     * @return This object.
     */
    public System addSubSystem(final System subSystem) {
        if (this.subSystems == null) {
            this.subSystems = new ArrayList<>();
        }
        this.subSystems.add(subSystem);
        return this;
    }

    /**
     * Get the number of subsystems for this system.
     *
     * @return Number of subsystems for this system.
     */
    public int numSubSystems() {
        return (this.subSystems == null) ? 0 : this.subSystems.size();
    }

    /**
     * Get a subsystem for this system at a set index.
     *
     * @param index Index of the subsystem to get.
     * @return {@link System} object with the subsystem at the input index.
     * @throws IndexOutOfBoundsException if the index is out of range of the subsystem list.
     */
    @JsonIgnore
    public System getSubSystem(final int index) {
        if (this.subSystems == null) {
            throw new IndexOutOfBoundsException("Attempting to access subsystem " + index
                    + " of " + this.numSubSystems());
        }
        return this.subSystems.get(index);
    }

    /**
     * Get an {@link Iterable} object to iterate over subsystems of this system.
     *
     * @return {@link Iterable} object for iterating over subsystems of this system.
     */
    public Iterable<System> subSystems() {
        return (this.subSystems == null) ? Collections.emptyList() : this.subSystems;
    }

    /**
     * Get the list of subsystems for this system.
     *
     * @return List of {@link System} objects with subsystems for this system.
     */
    @JsonGetter(value = "subSystems")
    protected List<System> getSubSystems() { // Private since only Jackson should use it
        return this.subSystems;
    }

    @Override
    public System addReference(final Reference reference) {
        super.addReference(reference);
        return this;
    }

    @Override
    public System addContact(final Person contact) {
        super.addContact(contact);
        return this;
    }

    @Override
    public System addLicense(final License license) {
        super.addLicense(license);
        return this;
    }

    @Override
    @JsonAnySetter
    public System addUnsupportedField(final String key, final Object value) {
        super.addUnsupportedField(key, value);
        return this;
    }

    @Override
    public System removeUnsupportedField(final String key) {
        super.removeUnsupportedField(key);
        return this;
    }

    @Override
    public System clearUnsupportedFields() {
        super.clearUnsupportedFields();
        return this;
    }

    /** List of names for this system. */
    private List<String> names;

    /** List of IDs for this system. */
    private List<Id> ids;

    /** List of properties of this system. */
    private List<Property> properties;

    /** List of processing steps in the preparation of this system. */
    private List<ProcessStep> preparation;

    /** List of subsystems of this system. */
    private List<System> subSystems;

    /** Category of the system. */
    private String category;
}