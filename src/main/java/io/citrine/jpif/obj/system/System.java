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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Base representation for all systems.
 *
 * <p>Supported fields:
 * <ul>
 *     <li>names - Names of the system.
 *     <li>ids - List of {@link Id}s of the system.
 *     <li>properties - List of measured or calculated properties ({@link Property}) of the system.
 *     <li>preparation - List of preparation steps ({@link ProcessStep}) describing the making of the system.
 *     <li>subSystems - List of sub-systems ({@link System}) of the system.
 *     <li>references - List of {@link Reference}s with information about the system.
 *     <li>contacts - List of contacts ({@link Person}) for information about the system.
 *     <li>licenses - List of {@link License}s that apply to the system.
 *     <li>tags - List of strings with tags that apply to the system.
 * </ul>
 *
 * @author Kyle Michel
 */
@JsonTypeName("system")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "category")
@JsonSubTypes({
        @JsonSubTypes.Type(value = System.class),
        @JsonSubTypes.Type(value = ChemicalSystem.class),
        @JsonSubTypes.Type(value = Alloy.class),
        @JsonSubTypes.Type(name = "system.chemical.alloy.phase", value = ChemicalSystem.class)})  // Legacy support
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
     * Set the list of names of this system.
     *
     * @param name List of strings with the names of this system.
     */
    @JsonSetter(value = "name")
    protected void setName(final List<String> name) { // Private since only Jackson should use it
        setNames(name);
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
     * Insert a single name for this value.
     *
     * @param index Index at which to insert the input name.
     * @param name String with the name to add.
     * @return This object.
     */
    public System addName(final int index, final String name) {
        if (this.names == null) {
            this.names = new ArrayList<>();
        }
        this.names.add(index, name);
        return this;
    }

    /**
     * Remove a name from the system.
     *
     * @param name String with the name to remove.
     * @return True if the name was removed.
     */
    public boolean removeName(final String name) {
        return (this.names != null) && this.names.remove(name);
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
     * Set the list of IDs of this system.
     *
     * @param id List of {@link Id} objects with the IDs of this system.
     */
    @JsonSetter(value = "id")
    @JsonDeserialize(contentUsing = Id.Deserializer.class)
    protected void setId(final List<Id> id) { // Private since only Jackson should use it
        setIds(id);
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
     * Insert a single ID for this system.
     *
     * @param index Index at which to insert the input ID.
     * @param id {@link Id} object to add to this system.
     * @return This object.
     */
    public System addId(final int index, final Id id) {
        if (this.ids == null) {
            this.ids = new ArrayList<>();
        }
        this.ids.add(index, id);
        return this;
    }

    /**
     * Remove an ID from the system.
     *
     * @param id {@link Id} object to delete.
     * @return True if the object was removed.
     */
    public boolean removeId(final Id id) {
        return (this.ids != null) && this.ids.remove(id);
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
     * Set the list of properties of this system.
     *
     * @param property List of {@link Property} objects with the properties of this system.
     */
    @JsonSetter(value = "property")
    protected void setProperty(final List<Property> property) { // Private since only Jackson should use it
        setProperties(property);
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
     * Insert a single property for this system.
     *
     * @param index Index at which to insert the input property.
     * @param property {@link Property} object to add to this system.
     * @return This object.
     */
    public System addProperty(final int index, final Property property) {
        if (this.properties == null) {
            this.properties = new ArrayList<>();
        }
        this.properties.add(index, property);
        return this;
    }

    /**
     * Remove a property from the system.
     *
     * @param property {@link Property} object to delete.
     * @return True if the object was removed.
     */
    public boolean removeProperty(final Property property) {
        return (this.properties != null) && this.properties.remove(property);
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
     * Set the list of preparation steps of this system.
     *
     * @param preparations List of {@link ProcessStep} objects with the processing steps of this system.
     */
    @JsonSetter(value = "preparations")
    protected void setPreparations(final List<ProcessStep> preparations) { // Private since only Jackson should use it
        setPreparation(preparations);
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
     * Insert a single preparation step for this system.
     *
     * @param index Index at which to insert the input preparation step.
     * @param preparation {@link ProcessStep} object to add to this system.
     * @return This object.
     */
    public System addPreparation(final int index, final ProcessStep preparation) {
        if (this.preparation == null) {
            this.preparation = new ArrayList<>();
        }
        this.preparation.add(index, preparation);
        return this;
    }

    /**
     * Remove a preparation step from the system.
     *
     * @param preparation {@link ProcessStep} object to delete.
     * @return True if the object was removed.
     */
    public boolean removePreparation(final ProcessStep preparation) {
        return (this.preparation != null) && this.preparation.remove(preparation);
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
            throw new IndexOutOfBoundsException("Attempting to access preparation " + index + " of "
                    + this.numPreparation());
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
     * Set the list of subsystems of this system.
     *
     * @param subSystem List of {@link System} objects with the subsystems of this system.
     */
    @JsonSetter(value = "subSystem")
    protected void setSubSystem(final List<System> subSystem) { // Private since only Jackson should use it
        setSubSystems(subSystem);
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
     * Insert a single subsystem for this system.
     *
     * @param index Index at which to insert the input subsystem.
     * @param subSystem {@link System} object to add to this value.
     * @return This object.
     */
    public System addSubSystem(final int index, final System subSystem) {
        if (this.subSystems == null) {
            this.subSystems = new ArrayList<>();
        }
        this.subSystems.add(index, subSystem);
        return this;
    }

    /**
     * Remove a subsystem from the system.
     *
     * @param subsystem {@link System} object to delete.
     * @return True if the object was removed.
     */
    public boolean removeSubsystem(final System subsystem) {
        return (this.subSystems != null) && this.subSystems.remove(subsystem);
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
    public System addReference(final int index, final Reference reference) {
        super.addReference(index, reference);
        return this;
    }

    @Override
    public System addContact(final Person contact) {
        super.addContact(contact);
        return this;
    }

    @Override
    public System addContact(final int index, final Person contact) {
        super.addContact(index, contact);
        return this;
    }

    @Override
    public System addLicense(final License license) {
        super.addLicense(license);
        return this;
    }

    @Override
    public System addLicense(final int index, final License license) {
        super.addLicense(index, license);
        return this;
    }

    @Override
    public System addTag(final String tag) {
        super.addTag(tag);
        return this;
    }

    @Override
    public System addTag(final int index, final String tag) {
        super.addTag(index, tag);
        return this;
    }

    @Override
    @JsonAnySetter
    public System addUnsupportedField(final String key, final Object value) {
        super.addUnsupportedField(key, value);
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
}