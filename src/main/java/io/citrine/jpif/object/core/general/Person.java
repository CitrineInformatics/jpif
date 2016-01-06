package io.citrine.jpif.object.core.general;

import com.fasterxml.jackson.annotation.JsonAnySetter;

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
     * @return This object.
     */
    public Person setName(final Name name) {
        this.name = name;
        return this;
    }

    /**
     * Get the name of the person.
     *
     * @return {@link Name} object for the person.
     */
    public Name getName() {
        return this.name;
    }

    /**
     * Set the email address of the person.
     *
     * @param email String with the email address of the person.
     * @return This object.
     */
    public Person setEmail(final String email) {
        this.email = email;
        return this;
    }

    /**
     * Get the email address of the person.
     *
     * @return String with the email address of the person.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Set the <a href="http://orcid.org">ORCID</a> identifier of the person.
     *
     * @param orcid String with the OCRID identifier of the person.
     * @return This object.
     */
    public Person setOrcid(final String orcid) {
        this.orcid = orcid;
        return this;
    }

    /**
     * Get the <a href="http://orcid.org">ORCID</a> identifier of the person.
     *
     * @return String with the ORCID identifier of the person.
     */
    public String getOrcid() {
        return this.orcid;
    }

    @Override
    @JsonAnySetter
    public Person addUnsupportedField(final String key, final Object value) {
        super.addUnsupportedField(key, value);
        return this;
    }

    /** Name of the person. */
    private Name name;

    /** Email address of the person. */
    private String email;

    /** ORCID identifier of the person. */
    private String orcid;
}