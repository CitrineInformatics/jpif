package io.citrine.jpif.object.core.general;

import com.fasterxml.jackson.annotation.JsonAnySetter;

/**
 * Representation of the starting and ending pages of a reference.
 *
 * @author Kyle Michel
 */
public class Pages extends Pio {

    /**
     * Set the starting page.
     *
     * @param start String with the starting page.
     * @return This object.
     */
    public Pages setStart(final String start) {
        this.start = start;
        return this;
    }

    /**
     * Get the starting page.
     *
     * @return String with the starting page.
     */
    public String getStart() {
        return this.start;
    }

    /**
     * Set the ending page.
     *
     * @param end String with the ending page.
     * @return This object.
     */
    public Pages setEnd(final String end) {
        this.end = end;
        return this;
    }

    /**
     * Get the ending page.
     *
     * @return String with the ending page.
     */
    public String getEnd() {
        return this.end;
    }

    @Override
    @JsonAnySetter
    public Pages addUnsupportedField(final String key, final Object value) {
        super.addUnsupportedField(key, value);
        return this;
    }

    /** Starting page. */
    private String start;

    /** Ending page. */
    private String end;
}