package io.citrine.jpif.object.core.general;

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
     * @return This object.
     */
    public License setName(final String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the name of the license.
     *
     * @return String with the name of the license.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the description of the license.
     *
     * @param description String with the description of the license.
     * @return This object.
     */
    public License setDescription(final String description) {
        this.description = description;
        return this;
    }

    /**
     * Get the description of the license.
     *
     * @return String with the description of the license.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Set the URL to the license.
     *
     * @param url String with the URL to the license.
     * @return This object.
     */
    public License setUrl(final String url) {
        this.url = url;
        return this;
    }

    /**
     * Get the URL to the license.
     *
     * @return String with the URL to the license.
     */
    public String getUrl() {
        return this.url;
    }

    @Override
    @JsonSetter
    public License addUnsupportedField(final String key, final Object value) {
        super.addUnsupportedField(key, value);
        return this;
    }

    /** Name of the license. */
    private String name;

    /** Description of the license. */
    private String description;

    /** URL to the license. */
    private String url;
}