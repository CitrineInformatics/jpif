package io.citrine.jpif.obj.common;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Information about a property and conditions under which it exists.
 * <p>
 * Since java does not allow for multiple inheritance, this class encapsulates an {@link Rcl} object that adds
 * support for reference, contact, and license information.
 *
 * @author Kyle Michel
 */
public class Property extends Value {

    /**
     * Set the list of conditions for this property.
     *
     * @param conditions List of {@link Value} objects with the conditions for this property.
     */
    @JsonSetter(value = "conditions")
    protected void setConditions(final List<Value> conditions) { // Private since only Jackson should use it
        this.conditions = conditions;
    }

    /**
     * Add a condition for this property.
     *
     * @param condition {@link Value} object with the condition to add.
     * @return This object.
     */
    public Property addCondition(final Value condition) {
        if (this.conditions == null) {
            this.conditions = new ArrayList<>();
        }
        this.conditions.add(condition);
        return this;
    }

    /**
     * Get the number of conditions for this property.
     *
     * @return Number of conditions for this property.
     */
    public int numConditions() {
        return (this.conditions == null) ? 0 : this.conditions.size();
    }

    /**
     * Get a condition for this property at a set index.
     *
     * @param index Index of the condition to get.
     * @return {@link Value} object at the input index.
     * @throws IndexOutOfBoundsException if the index is out of range of the conditions list.
     */
    @JsonIgnore
    public Value getCondition(final int index) {
        if (this.conditions == null) {
            throw new IndexOutOfBoundsException("Attempting to access condition " + index + " of "
                    + this.numConditions());
        }
        return this.conditions.get(index);
    }

    /**
     * Get an {@link Iterable} object to iterate over conditions of this property.
     *
     * @return {@link Iterable} object for iterating over conditions of this property.
     */
    public Iterable<Value> conditions() {
        return (this.conditions == null) ? Collections.emptyList() : this.conditions;
    }

    /**
     * Get the list of conditions for this property.
     *
     * @return List of {@link Value} objects with conditions for this property.
     */
    @JsonGetter(value = "conditions")
    protected List<Value> getConditions() { // Private since only Jackson should use it
        return this.conditions;
    }

    /**
     * Set the method used to measure this property.
     *
     * @param method {@link Method} object with information about how this property was measured.
     * @return This object.
     */
    @JsonSetter(value = "method")
    public Property setMethod(final Method method) {
        this.method = method;
        return this;
    }

    /**
     * Get the method used to measure this property.
     *
     * @return {@link Method} object with information about how the property was measured.
     */
    @JsonGetter(value = "method")
    public Method getMethod() {
        return this.method;
    }

    /**
     * Set the type of the data for the property.
     *
     * @param dataType {@link DataType} object for the property.
     * @return This object.
     */
    @JsonSetter(value = "dataType")
    public Property setDataType(final DataType dataType) {
        this.dataType = dataType;
        return this;
    }

    /**
     * Get the data type of the property.
     *
     * @return {@link DataType}
     */
    @JsonGetter
    public DataType getDataType() {
        return this.dataType;
    }

    /**
     * Set the list of references where information about this item is published.
     *
     * @param references List of {@link Reference} objects with the references for this item.
     */
    @JsonSetter(value = "references")
    protected void setReferences(final List<Reference> references) { // Private since only Jackson should use it
        this.rcl.setReferences(references);
    }

    /**
     * Add a reference where information about this item is published.
     *
     * @param reference {@link Reference} object with the reference to add for this item.
     * @return This object.
     */
    public Property addReference(final Reference reference) {
        this.rcl.addReference(reference);
        return this;
    }

    /**
     * Get the number of references for this item.
     *
     * @return Number of references for this item.
     */
    public int numReferences() {
        return this.rcl.numReferences();
    }

    /**
     * Get a reference for this item at a set index.
     *
     * @param index Index of the reference to get.
     * @return {@link Reference} object at the input index.
     * @throws IndexOutOfBoundsException if the index is out of range of the reference list.
     */
    @JsonIgnore
    public Reference getReference(final int index) {
        return this.rcl.getReference(index);
    }

    /**
     * Get an {@link Iterable} object to iterate over references for this item.
     *
     * @return {@link Iterable} object for iterating over references for this item.
     */
    public Iterable<Reference> references() {
        return this.rcl.references();
    }

    /**
     * Get the list of references for this item.
     *
     * @return List of {@link Reference} objects with references for this item.
     */
    @JsonGetter(value = "references")
    protected List<Reference> getReferences() { // Private since only Jackson should use it
        return this.rcl.getReferences();
    }

    /**
     * Set the list of people to contact for information about this item.
     *
     * @param contacts List of {@link Person} objects with the contacts for this item.
     */
    @JsonSetter(value = "contacts")
    @JsonDeserialize(using = Person.Deserializer.class)
    protected void setContacts(final List<Person> contacts) { // Private since only Jackson should use it
        this.rcl.setContacts(contacts);
    }

    /**
     * Add a person to contact for information about this item.
     *
     * @param contact {@link Person} object with the contact to add.
     * @return This object.
     */
    public Property addContact(final Person contact) {
        this.rcl.addContact(contact);
        return this;
    }

    /**
     * Get the number of contacts for this item.
     *
     * @return Number of contacts for this item..
     */
    public int numContacts() {
        return this.rcl.numContacts();
    }

    /**
     * Get a contact at a set index.
     *
     * @param index Index of the contact to get.
     * @return {@link Person} object with the contact at the input index.
     * @throws IndexOutOfBoundsException if the index is out of range of the contact list.
     */
    @JsonIgnore
    public Person getContact(final int index) {
        return this.rcl.getContact(index);
    }

    /**
     * Get an {@link Iterable} object to iterate over contacts for this item.
     *
     * @return {@link Iterable} object for iterating over contacts for this item.
     */
    public Iterable<Person> contacts() {
        return this.rcl.contacts();
    }

    /**
     * Get the list of contacts for this item.
     *
     * @return List of {@link Person} objects with contacts for this item.
     */
    @JsonGetter(value = "contacts")
    private List<Person> getContacts() { // Private since only Jackson should use it
        return this.rcl.getContacts();
    }

    /**
     * Set the list of licenses for this item.
     *
     * @param licenses List of {@link License} objects with licenses for this item.
     */
    @JsonSetter(value = "licenses")
    @JsonDeserialize(using = License.Deserializer.class)
    protected void setLicenses(final List<License> licenses) { // Private since only Jackson should use it
        this.rcl.setLicenses(licenses);
    }

    /**
     * Add a license for this item.
     *
     * @param license {@link License} object with the license to add.
     * @return This object.
     */
    public Property addLicense(final License license) {
        this.rcl.addLicense(license);
        return this;
    }

    /**
     * Get the number of licenses for this item.
     *
     * @return Number of licenses for this item..
     */
    @JsonIgnore
    public int numLicenses() {
        return this.rcl.numLicenses();
    }

    /**
     * Get a license at a set index.
     *
     * @param index Index of the license to get.
     * @return {@link License} object with the license at the input index.
     * @throws IndexOutOfBoundsException if the index is out of range of the license list.
     */
    @JsonIgnore
    public License getLicense(final int index) {
        return this.rcl.getLicense(index);
    }

    /**
     * Get an {@link Iterable} object to iterate over licenses for this item.
     *
     * @return {@link Iterable} object for iterating over licenses for this item.
     */
    public Iterable<License> licenses() {
        return this.rcl.licenses();
    }

    /**
     * Get the list of licenses for this item.
     *
     * @return List of {@link License} objects with licenses for this item.
     */
    @JsonGetter(value = "licenses")
    protected List<License> getLicenses() { // Private since only Jackson should use it
        return this.rcl.getLicenses();
    }

    @Override
    @JsonSetter(value = "name")
    public Property setName(final String name) {
        super.setName(name);
        return this;
    }

    @Override
    public Property addScalar(final Scalar scalar) {
        super.addScalar(scalar);
        return this;
    }

    @Override
    public Property addVector(final Scalar[] vector) {
        super.addVector(vector);
        return this;
    }

    @Override
    public Property addMatrix(final Scalar[][] matrix) {
        super.addMatrix(matrix);
        return this;
    }

    @Override
    @JsonSetter(value = "units")
    public Property setUnits(final String units) {
        super.setUnits(units);
        return this;
    }

    @Override
    @JsonAnySetter
    public Property addUnsupportedField(final String key, final Object value) {
        super.addUnsupportedField(key, value);
        return this;
    }

    @Override
    public Property removeUnsupportedField(final String key) {
        super.removeUnsupportedField(key);
        return this;
    }

    @Override
    public Property clearUnsupportedFields() {
        super.clearUnsupportedFields();
        return this;
    }

    /** List of conditions for the property. */
    private List<Value> conditions;

    /** Method used to obtain the property. */
    private Method method;

    /** Type of the data represented by the property. */
    private DataType dataType;

    /** Encapsulated {@link Rcl} object. */
    private final Rcl rcl = new Rcl();

    /**
     * Enumeration of data types.
     *
     * @author Kyle Michel
     */
    public enum DataType {

        /** Computational data type. */
        COMPUTATIONAL(1, "COMPUTATIONAL", "CALCULATED"),

        /** Experimental data type */
        EXPERIMENTAL(2, "EXPERIMENTAL");

        /**
         * Get the name of the data type.
         *
         * @return Name of the data type.
         */
        @JsonValue
        public String getName() {
            return this.name;
        }

        /**
         * Get the code for the data type.
         *
         * @return Integer with the code for the data type.
         */
        @JsonIgnore
        public int getCode() {
            return this.code;
        }

        /**
         * Get a data type given a lookup string.
         *
         * @param dataTypeName String with the name of the data type.
         * @return {@link DataType} object corresponding to lookup or a null pointer if the name is not valid.
         */
        @JsonCreator
        public static DataType get(final String dataTypeName) {
            return (dataTypeName == null)
                    ? null
                    : NAME_TO_DATA_TYPE_MAP.get(normalizeName(dataTypeName));
        }

        /**
         * Constructor.
         *
         * @param  code Code to assign to the object.
         * @param  name Name to assign to the object.
         */
        DataType(final int code, final String... name) {
            this.code = code;
            this.name = name[0];
            this.lookup = Collections.unmodifiableList(getLookupStrings(name));
        }

        /**
         * Generate a list of lookup strings from a list of names.
         *
         * @param  name List of names to convert to lookups.
         * @return List of lookup strings.
         */
        private static List<String> getLookupStrings(final String[] name) {
            List<String> res = new ArrayList<>(name.length);
            for (String i : name) {
                res.add(normalizeName(i));
            }
            return res;
        }

        /**
         * Normalize the input string to get a lookup name.
         *
         * @param name String to normalize.
         * @return Normalized version of the input name.
         */
        private static String normalizeName(final String name) {
            return name.trim().substring(0, LOOKUP_LENGTH).toLowerCase();
        }

        /** Code for the data type. */
        protected final int code;

        /** Name of the data type. */
        protected final String name;

        /** Lookup of the data type. */
        protected final List<String> lookup;

        /** Number of characters to use in lookup. */
        protected static final int LOOKUP_LENGTH = 3;

        /** Map of lookup strings to their enumerated values. */
        protected static final Map<String, DataType> NAME_TO_DATA_TYPE_MAP =
                Collections.unmodifiableMap(buildLookupMap());

        /**
         * Build the map of lookup strings to their data type.
         *
         * @return Map of lookup strings to their data type.
         */
        private static Map<String, DataType> buildLookupMap() {
            Map<String, DataType> res = new HashMap<>(values().length);
            for (DataType i : values()) {
                for (String j : i.lookup) {
                    res.put(j, i);
                }
            }
            return res;
        }
    }
}