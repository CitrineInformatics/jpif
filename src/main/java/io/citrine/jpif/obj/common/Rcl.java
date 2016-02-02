package io.citrine.jpif.obj.common;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class used as a base for objects that contain a reference, contact, and license.
 *
 * @author Kyle Michel
 */
public class Rcl extends Pio {

    /**
     * Set the list of references where information about this item is published.
     *
     * @param references List of {@link Reference} objects with the references for this item.
     */
    @JsonSetter(value = "references")
    protected void setReferences(final List<Reference> references) { // Protected since only Jackson should use it
        this.references = references;
    }

    /**
     * Add a reference where information about this item is published.
     *
     * @param reference {@link Reference} object with the reference to add for this item.
     * @return This object.
     */
    public Rcl addReference(final Reference reference) {
        if (this.references == null) {
            this.references = new ArrayList<>();
        }
        this.references.add(reference);
        return this;
    }

    /**
     * Get the number of references for this item.
     *
     * @return Number of references for this item.
     */
    public int numReferences() {
        return (this.references == null) ? 0 : this.references.size();
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
        if (this.references == null) {
            throw new IndexOutOfBoundsException("Attempting to access reference " + index + " of "
                    + this.numReferences());
        }
        return this.references.get(index);
    }

    /**
     * Get an {@link Iterable} object to iterate over references for this item.
     *
     * @return {@link Iterable} object for iterating over references for this item..
     */
    public Iterable<Reference> references() {
        return (this.references == null) ? Collections.emptyList() : this.references;
    }

    /**
     * Get the list of references for this item.
     *
     * @return List of {@link Reference} objects with references for this item.
     */
    @JsonGetter(value = "references")
    protected List<Reference> getReferences() { // Protected since only Jackson should use it
        return this.references;
    }

    /**
     * Set the list of people to contact for information about this item.
     *
     * @param contacts List of {@link Person} objects with the contacts for this item.
     */
    @JsonSetter(value = "contacts")
    @JsonDeserialize(using = Person.Deserializer.class)
    protected void setContacts(final List<Person> contacts) { // Protected since only Jackson should use it
        this.contacts = contacts;
    }

    /**
     * Add a person to contact for information about this item.
     *
     * @param contact {@link Person} object with the contact to add.
     * @return This object.
     */
    public Rcl addContact(final Person contact) {
        if (this.contacts == null) {
            this.contacts = new ArrayList<>();
        }
        this.contacts.add(contact);
        return this;
    }

    /**
     * Get the number of contacts for this item.
     *
     * @return Number of contacts for this item..
     */
    public int numContacts() {
        return (this.contacts == null) ? 0 : this.contacts.size();
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
        if (this.contacts == null) {
            throw new IndexOutOfBoundsException("Attempting to access contact " + index + " of "
                    + this.numContacts());
        }
        return this.contacts.get(index);
    }

    /**
     * Get an {@link Iterable} object to iterate over contacts for this item.
     *
     * @return {@link Iterable} object for iterating over contacts for this item.
     */
    public Iterable<Person> contacts() {
        return (this.contacts == null) ? Collections.emptyList() : this.contacts;
    }

    /**
     * Get the list of contacts for this item.
     *
     * @return List of {@link Person} objects with contacts for this item.
     */
    @JsonGetter(value = "contacts")
    protected List<Person> getContacts() { // Protected since only Jackson should use it
        return this.contacts;
    }

    /**
     * Set the list of licenses for this item.
     *
     * @param licenses List of {@link License} objects with licenses for this item.
     */
    @JsonSetter(value = "licenses")
    @JsonDeserialize(using = License.Deserializer.class)
    protected void setLicenses(final List<License> licenses) { // Protected since only Jackson should use it
        this.licenses = licenses;
    }

    /**
     * Add a license for this item.
     *
     * @param license {@link License} object with the license to add.
     * @return This object.
     */
    public Rcl addLicense(final License license) {
        if (this.licenses == null) {
            this.licenses = new ArrayList<>();
        }
        this.licenses.add(license);
        return this;
    }

    /**
     * Get the number of licenses for this item.
     *
     * @return Number of licenses for this item..
     */
    @JsonIgnore
    public int numLicenses() {
        return (this.licenses == null) ? 0 : this.licenses.size();
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
        if (this.licenses == null) {
            throw new IndexOutOfBoundsException("Attempting to access license " + index + " of "
                    + this.numLicenses());
        }
        return this.licenses.get(index);
    }

    /**
     * Get an {@link Iterable} object to iterate over licenses for this item.
     *
     * @return {@link Iterable} object for iterating over licenses for this item.
     */
    public Iterable<License> licenses() {
        return (this.licenses == null) ? Collections.emptyList() : this.licenses;
    }

    /**
     * Get the list of licenses for this item.
     *
     * @return List of {@link License} objects with licenses for this item.
     */
    @JsonGetter(value = "licenses")
    protected List<License> getLicenses() { // Protected since only Jackson should use it
        return this.licenses;
    }

    /** List of references for the item. */
    private List<Reference> references;

    /** List of contacts for the item. */
    private List<Person> contacts;

    /** List of licenses for the item. */
    private List<License> licenses;
}