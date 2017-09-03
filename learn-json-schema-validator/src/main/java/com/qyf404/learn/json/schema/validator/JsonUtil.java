package com.qyf404.learn.json.schema.validator;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

public class JsonUtil {
    public static boolean validate(String jsonData, String jsonSchema) throws Exception {
        // create the Json nodes for schema and data
        JsonNode schemaNode = JsonLoader.fromString(jsonSchema); // throws JsonProcessingException if error
        JsonNode data = JsonLoader.fromString(jsonData);         // same here


        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        // load the schema and validate
        JsonSchema schema = factory.getJsonSchema(schemaNode);
        ProcessingReport report = schema.validate(data);

        return report.isSuccess();
    }
}
