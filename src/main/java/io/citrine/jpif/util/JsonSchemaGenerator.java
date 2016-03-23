package io.citrine.jpif.util;

import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;

import java.io.IOException;

/**
 * Helper class used to generate JSON schema.
 *
 * @author Kyle Michel
 */
public class JsonSchemaGenerator {

    /**
     * Generate the JSON schema for an input object.
     *
     * @param clazz Class of the object to get JSON schema for.
     * @return String with the pretty-printed JSON schema for the input class.
     * @throws IOException if thrown from within this function.
     */
    public static String getJsonSchema(Class<?> clazz) throws IOException {
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        PifObjectMapper.getInstance().acceptJsonFormatVisitor(
                PifObjectMapper.getInstance().constructType(clazz), visitor);
        JsonSchema jsonSchema = visitor.finalSchema();
        return PifObjectMapper.getInstance().writerWithDefaultPrettyPrinter().writeValueAsString(jsonSchema);
    }
}