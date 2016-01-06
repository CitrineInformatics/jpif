package io.citrine.jpif.object.core.general;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Information about a license that applies to some item.
 *
 * @author Kyle Michel
 */
public class License extends Pio {

    /**
     * Set the name of the license.
     *
     * @param name String with the name of the license.
     */
    @JsonSetter
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Set the name of the license.
     *
     * @param name String with the name of the license.
     * @return This object.
     */
    public License withName(final String name) {
        this.setName(name);
        return this;
    }

    /**
     * Get the name of the license.
     *
     * @return String with the name of the license.
     */
    @JsonGetter
    public String getName() {
        return this.name;
    }

    /**
     * Set the description of the license.
     *
     * @param description String with the description of the license.
     */
    @JsonSetter
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Set the description of the license.
     *
     * @param description String with the description of the license.
     * @return This object.
     */
    public License withDescription(final String description) {
        this.setDescription(description);
        return this;
    }

    /**
     * Get the description of the license.
     *
     * @return String with the description of the license.
     */
    @JsonGetter
    public String getDescription() {
        return this.description;
    }

    /**
     * Set the URL to the license.
     *
     * @param url String with the URL to the license.
     */
    @JsonSetter
    public void setUrl(final String url) {
        this.url = url;
    }

    /**
     * Set the URL to the license.
     *
     * @param url String with the URL to the license.
     * @return This object.
     */
    public License withUrl(final String url) {
        this.setUrl(url);
        return this;
    }

    /**
     * Get the URL to the license.
     *
     * @return String with the URL to the license.
     */
    @JsonGetter
    public String getUrl() {
        return this.url;
    }

    @Override
    public License withUnsupportedField(final String key, final Object value) {
        super.withUnsupportedField(key, value);
        return this;
    }

    /** Name of the license. */
    protected String name;

    /** Description of the license. */
    protected String description;

    /** URL to the license. */
    protected String url;
}