package io.citrine.jpif.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Class used to serialize and deserialize from the PIF schema.
 *
 * @author Kyle Michel
 */
public class PifObjectMapper extends ObjectMapper {

    /**
     * Get a static instance of this class.
     *
     * @return {@link PifObjectMapper} object.
     */
    public static PifObjectMapper getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Constructor. This configures the object mapper underlying this class.
     */
    private PifObjectMapper() {
        this.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        this.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    }

    /**
     * Holder class. Bill Pugh's Singleton pattern.
     *
     * @author Kyle Michel
     */
    private static class Holder {
        private static final PifObjectMapper INSTANCE = new PifObjectMapper();
    }
}