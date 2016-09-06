package io.citrine.jpif.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import com.fasterxml.jackson.module.jsonSchema.factories.VisitorContext;
import com.fasterxml.jackson.module.jsonSchema.types.ArraySchema;
import com.fasterxml.jackson.module.jsonSchema.types.ObjectSchema;
import io.citrine.jpif.obj.common.Reference;
import io.citrine.jpif.obj.system.System;
import io.citrine.jpif.obj.system.chemical.ChemicalSystem;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
        java.lang.System.out.println(getJsonSchema(ChemicalSystem.class, true, false));
    }

    /**
     * Generate the JSON schema for an input object.
     *
     * @param clazz Class of the object to get JSON schema for.
     * @param addTitle True to add titles
     * @param prettyPrint True to pretty print the schema that is generted.
     * @return String with the JSON schema for the input class.
     * @throws IOException if thrown from within this function.
     */
    public static String getJsonSchema(
            final Class<?> clazz, final boolean addTitle, final boolean prettyPrint) throws IOException {
        return getJsonSchema(OBJECT_MAPPER.constructType(clazz), addTitle, prettyPrint);
    }

    /**
     * Get the JSON schema for the input type.
     *
     * @param javaType {@link JavaType} for the schema to generate.
     * @param addTitle True to add titles
     * @param prettyPrint True to pretty print the schema that is generted.
     * @return String with the JSON schema for the input class.
     * @throws IOException if thrown from within this function.
     */
    public static String getJsonSchema(
            final JavaType javaType, final boolean addTitle, final boolean prettyPrint) throws IOException {
        final SchemaFactoryWrapper visitor = new SchemaFactoryWrapper().setVisitorContext(new NoUriVisitorContext());
        OBJECT_MAPPER.acceptJsonFormatVisitor(javaType, visitor);
        JsonSchema jsonSchema = visitor.finalSchema();
        return addTitle
                ? serializeJsonSchema(injectTitles(jsonSchema, javaType.getRawClass().getSimpleName()), prettyPrint)
                : serializeJsonSchema(jsonSchema, prettyPrint);
    }

    /**
     * Inject titles into a {@link JsonSchema} object.
     *
     * @param jsonSchema {@link JsonSchema} to inject titles into.
     * @param field String with the name of the field.
     * @return The input object.
     */
    private static JsonSchema injectTitles(final JsonSchema jsonSchema, final String field) {

        // Set the title if possible
        if (jsonSchema.isSimpleTypeSchema()) {
            jsonSchema.asSimpleTypeSchema().setTitle(field);
        }

        // Process the schema depending on its type
        if (jsonSchema.isObjectSchema()) {
            injectTitles(jsonSchema.asObjectSchema(), field);
        }
        else if (jsonSchema.isArraySchema()) {
            injectTitles(jsonSchema.asArraySchema(), field);
        }

        // Return the input schema
        return jsonSchema;
    }

    /**
     * Inject titles into an object schema.
     *
     * @param objectSchema {@link ObjectSchema} to process.
     * @param field String with the name of the field.
     */
    private static void injectTitles(final ObjectSchema objectSchema, final String field) {
        for (Map.Entry<String, JsonSchema> entry : objectSchema.getProperties().entrySet()) {
            injectTitles(entry.getValue(), entry.getKey());
        }
    }

    /**
     * Inject titles into an array schema.
     *
     * @param arraySchema {@link ArraySchema} to process.
     * @param field String with the name of the field.
     */
    private static void injectTitles(final ArraySchema arraySchema, final String field) {
        if (arraySchema.getItems().isSingleItems()) {
            injectTitles(arraySchema.getItems().asSingleItems().getSchema(), null);
        }
        else if (arraySchema.getItems().isArrayItems()) {
            for (JsonSchema i : arraySchema.getItems().asArrayItems().getJsonSchemas()) {
                injectTitles(i, null);
            }
        }
    }

    /**
     * Serialize a {@link JsonSchema} object.
     *
     * @param jsonSchema {@link JsonSchema} to serialize.
     * @param prettyPrint True to pretty print the serialization.
     * @return String with the serialized JSON schema.
     * @throws IOException if thrown from within this function.
     */
    static String serializeJsonSchema(
            final JsonSchema jsonSchema, final boolean prettyPrint) throws IOException {
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
    public static final ObjectMapper OBJECT_MAPPER = getObjectMapper();

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
    public abstract static class SystemMixIn {

        @JsonIgnore
        abstract void setSubSystems(final List<System> reference);

        @JsonIgnore
        abstract List<System> getSubSystems();
    }

    /**
     * Mixin class for serializing {@link Reference} objects without recursive references.
     *
     * @author Kyle Michel
     */
    public abstract static class ReferenceMixIn {

        @JsonIgnore
        abstract void setReference(final List<Reference> reference);

        @JsonIgnore
        abstract List<Reference> getReferences();
    }
}