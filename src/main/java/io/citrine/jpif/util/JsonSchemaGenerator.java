package io.citrine.jpif.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import com.fasterxml.jackson.module.jsonSchema.factories.VisitorContext;
import io.citrine.jpif.obj.common.Reference;
import io.citrine.jpif.obj.system.System;
import io.citrine.jpif.obj.system.chemical.ChemicalSystem;

import java.io.IOException;
import java.util.List;

/**
 * Helper class used to generate JSON schema.
 *
 * @author Kyle Michel
 */
public class JsonSchemaGenerator {

    /**
     * Generate JSON schema for a single chemical system.
     */
    public static void main(String[] args) throws Exception {
        java.lang.System.out.println(getJsonSchema(ChemicalSystem.class, false));
    }

    /**
     * Generate the JSON schema for an input object.
     *
     * @param clazz Class of the object to get JSON schema for.
     * @param prettyPrint True to pretty print the schema that is generted.
     * @return String with the pretty-printed JSON schema for the input class.
     * @throws IOException if thrown from within this function.
     */
    public static String getJsonSchema(final Class<?> clazz, final boolean prettyPrint) throws IOException {
        final SchemaFactoryWrapper visitor = new SchemaFactoryWrapper().setVisitorContext(new NoUriVisitorContext());
        OBJECT_MAPPER.acceptJsonFormatVisitor(OBJECT_MAPPER.constructType(clazz), visitor);
        JsonSchema jsonSchema = visitor.finalSchema();
        return prettyPrint
                ? OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(jsonSchema)
                : OBJECT_MAPPER.writeValueAsString(jsonSchema);
    }

    /**
     * Custom visitor that does not track URIs.
     *
     * @author Kyle Michel
     */
    public static class NoUriVisitorContext extends VisitorContext {

        @Override
        public String addSeenSchemaUri(final JavaType seenSchema) {
            return null;
        }
    }

    /** Object mapper to use. */
    private static final ObjectMapper OBJECT_MAPPER = getObjectMapper();

    /**
     * Get an object mapper configured for schema generation.
     *
     * @return {@link ObjectMapper} used to generate JSON schema.
     */
    private static ObjectMapper getObjectMapper() {
        return new ObjectMapper()
                .addMixIn(System.class, SystemMixIn.class)
                .addMixIn(Reference.class, ReferenceMixIn.class);
    }

    /**
     * Mixin class for serializing {@link System} objects without recursive references.
     *
     * @author Kyle Michel
     */
    public static abstract class SystemMixIn {
        @JsonIgnore
        protected abstract void setSubSystems(final List<System> reference);
        @JsonIgnore
        protected abstract List<System> getSubSystems();
    }

    /**
     * Mixin class for serializing {@link Reference} objects without recursive references.
     *
     * @author Kyle Michel
     */
    public static abstract class ReferenceMixIn {
        @JsonIgnore
        protected abstract void setReference(final List<Reference> reference);
        @JsonIgnore
        protected abstract List<Reference> getReferences();
    }
}