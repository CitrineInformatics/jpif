package io.citrine.jpif.object.core.general;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.Map;

/**
 * Information about a person.
 *
 * @author Kyle Michel
 */
public class Person extends Pio {

    /**
     * Set the name of the person.
     *
     * @param name {@link Name} object for the person.
     */
    @JsonSetter
    public void setName(final Name name) {
        this.name = name;
    }

    /**
     * Set the name of the person.
     *
     * @param name {@link Name} object of the person.
     * @return This object.
     */
    public Person withName(final Name name) {
        this.setName(name);
        return this;
    }

    /**
     * Get the name of the person.
     *
     * @return {@link Name} object for the person.
     */
    @JsonGetter
    public Name getName() {
        return this.name;
    }

    /**
     * Set the email address of the person.
     *
     * @param email String with the email address of the person.
     */
    @JsonSetter
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * Set the email address of the person.
     *
     * @param email String with the email address of the person.
     * @return This object.
     */
    public Person withEmail(final String email) {
        this.setEmail(email);
        return this;
    }

    /**
     * Get the email address of the person.
     *
     * @return String with the email address of the person.
     */
    @JsonGetter
    public String getEmail() {
        return this.email;
    }

    /**
     * Set the <a href="http://orcid.org">ORCID</a> identifier of the person.
     *
     * @param orcid String with the OCRID identifier of the person.
     */
    @JsonSetter
    public void setOrcid(final String orcid) {
        this.orcid = orcid;
    }

    /**
     * Set the <a href="http://orcid.org">ORCID</a> identifier of the person.
     *
     * @param orcid String with the Orcid identifier of the person.
     * @return This object.
     */
    public Person withOrcid(final String orcid) {
        this.orcid = orcid;
        return this;
    }

    /**
     * Get the <a href="http://orcid.org">ORCID</a> identifier of the person.
     *
     * @return String with the ORCID identifier of the person.
     */
    @JsonGetter
    public String getOrcid() {
        return this.orcid;
    }

    @Override
    public Person withUnsupportedField(final String key, final Object value) {
        super.withUnsupportedField(key, value);
        return this;
    }

    /** Name of the person. */
    protected Name name;

    /** Email address of the person. */
    protected String email;

    /** ORCID identifier of the person. */
    protected String orcid;
}