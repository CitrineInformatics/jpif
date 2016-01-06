package io.citrine.jpif.object.core.general;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.Map;

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
     */
    @JsonSetter
    public void setStart(final String start) {
        this.start = start;
    }

    /**
     * Set the starting page.
     *
     * @param start String with the starting page.
     * @return This object.
     */
    public Pages withStart(final String start) {
        this.setStart(start);
        return this;
    }

    /**
     * Get the starting page.
     *
     * @return String with the starting page.
     */
    @JsonGetter
    public String getStart() {
        return this.start;
    }

    /**
     * Set the ending page.
     *
     * @param end String with the ending page.
     */
    @JsonSetter
    public void setEnd(final String end) {
        this.end = end;
    }

    /**
     * Set the ending page.
     *
     * @param end String with the ending page.
     * @return This object.
     */
    public Pages withEnd(final String end) {
        this.setEnd(end);
        return this;
    }

    /**
     * Get the ending page.
     *
     * @return String with the ending page.
     */
    @JsonGetter
    public String getEnd() {
        return this.end;
    }

    @Override
    public Pages withUnsupportedField(final String key, final Object value) {
        super.withUnsupportedField(key, value);
        return this;
    }

    /** Starting page. */
    protected String start;

    /** Ending page. */
    protected String end;
}