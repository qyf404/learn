package com.qyf404.learn.json.schema.validator;


import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * http://json.org/
 * http://json-schema.org/
 */
public class JsonTest {

    @Test
    public void should_validate_success() throws Exception {
        String jsonSchema = "{  \"type\" : \"string\"}";
        String json = "\"\"";

        boolean status = JsonUtil.validate(json, jsonSchema);
        assertTrue(status);
    }

    @Test
    public void should_validate_success_when_validate_simple_json() throws Exception {
        String jsonSchema = "{  \"type\" : \"string\"}";
        String json = "\"这是字符串\"";
        boolean status = JsonUtil.validate(json, jsonSchema);
        assertTrue(status);

        jsonSchema = "{  \"type\" : \"integer\"}";
        json = "100";
        assertTrue(JsonUtil.validate(json, jsonSchema));

        jsonSchema = "{  \"type\" : \"number\"}";
        json = "100";
        assertTrue(JsonUtil.validate(json, jsonSchema));

        jsonSchema = "{  \"type\" : \"number\"}";
        json = "100.01";
        assertTrue(JsonUtil.validate(json, jsonSchema));

        jsonSchema = "{  \"type\" : \"boolean\"}";
        json = "true";
        assertTrue(JsonUtil.validate(json, jsonSchema));

        jsonSchema = "{  \"type\" : \"null\"}";
        json = "null";
        assertTrue(JsonUtil.validate(json, jsonSchema));

        jsonSchema = "{  \"type\" : \"array\"}";
        json = "[]";
        assertTrue(JsonUtil.validate(json, jsonSchema));

        jsonSchema = "{  \"type\" : \"object\"}";
        json = "{}";
        assertTrue(JsonUtil.validate(json, jsonSchema));
    }

    @Test
    public void should_validate_success_when_multi_type() throws Exception {
        String jsonSchema = "{  \"type\" : [\"string\", \"number\"]}";
        String json1 = "\"这是字符串\"";
        String json2 = "100";
        String json3 = "true";

        assertTrue(JsonUtil.validate(json1, jsonSchema));
        assertTrue(JsonUtil.validate(json2, jsonSchema));
        assertFalse(JsonUtil.validate(json3, jsonSchema));
    }

    @Test
    public void should_validate_success_when_type_is_object() throws Exception {
        String jsonSchema = "{" +
                "  \"type\": \"object\"," +
                "  \"properties\": {" +
                "    \"number\":      { \"type\": \"number\" }," +
                "    \"street_name\": { \"type\": \"string\" }," +
                "    \"street_type\": { \"type\": \"string\"," +
                "                     \"enum\": [\"Street\", \"Avenue\", \"Boulevard\"]" +
                "                   }" +
                "  }," +
                "  \"required\": [\"number\", \"street_name\", \"street_type\"]" +
                "}";
        String json1 = "{ \"number\": 1600, \"street_name\": \"Pennsylvania\", \"street_type\": \"Avenue\" }";
        String json2 = "{ \"number\": 1600, \"street_name\": \"Pennsylvania\" }";
        String json3 = "{ \"number\": 1600, \"street_name\": \"Pennsylvania\", \"street_type\": \"x\" }";

        assertTrue(JsonUtil.validate(json1, jsonSchema));
        assertFalse(JsonUtil.validate(json2, jsonSchema));
        assertFalse(JsonUtil.validate(json3, jsonSchema));
    }

    @Test
    public void should_validate_success_when_type_is_array() throws Exception {
        String jsonSchema = "{\n" +
                "  \"type\": \"array\",\n" +
                "  \"items\": {\n" +
                "    \"type\": \"number\"\n" +
                "  }\n" +
                "}";
        String json1 = "[1, 2, 3, 4, 5]";
        String json2 = "[\"1\"]";

        assertTrue(JsonUtil.validate(json1, jsonSchema));
        assertFalse(JsonUtil.validate(json2, jsonSchema));
    }
}
