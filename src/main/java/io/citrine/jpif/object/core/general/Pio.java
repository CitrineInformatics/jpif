package io.citrine.jpif.object.core.general;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;

/**
 * Base class for all Physical Information Objects.
 *
 * @author Kyle Michel
 */
public abstract class Pio {

    /**
     * Add an unsupported field to this object.
     *
     * @param key String with the key of the field.
     * @param value Object with the value of the field.
     */
    @JsonAnySetter
    public void putUnsupportedField(final String key, final Object value) {
        this.unsupported.put(key, value);
    }

    /**
     * Get the map of all unsupported fields.
     *
     * @return Map of strings to objects with the unsupported fields.
     */
    @JsonAnyGetter
    public Map<String, Object> getUnsupportedFields() {
        return this.unsupported;
    }

    /**
     * Get the value of an unsupported field.
     *
     * @param key String with the key of the field.
     * @return Object with the value of the field or a null pointer if the field does not exist.
     */
    public Object getUnsupportedFieldValue(final String key) {
        return this.unsupported.get(key);
    }

    /**
     * Constructor. This just initializes the unsupported map.
     */
    protected Pio() {
        this.unsupported = new HashMap<>();
    }

    /** Map of unsupported field names to their values. */
    protected Map<String, Object> unsupported;
}