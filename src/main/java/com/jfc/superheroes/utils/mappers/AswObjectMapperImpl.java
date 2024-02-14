package com.jfc.superheroes.utils.mappers;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class AswObjectMapperImpl implements AswObjectMapper
{
    private final ObjectMapper objectMapper;

    public AswObjectMapperImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(new StringTrimModule());
    }

    private Module getModule(String moduleClassName) throws Exception {
        return (Module)Class.forName(moduleClassName).getDeclaredConstructor().newInstance();
    }

    private Set<Module> getModules(ObjectMapper objectMapper) throws Exception {
        Set<Module> modules = new LinkedHashSet();
        Iterator var3 = objectMapper.getRegisteredModuleIds().iterator();

        while(var3.hasNext()) {
            Object moduleClassName = var3.next();
            modules.add(this.getModule((String)moduleClassName));
        }

        return modules;
    }

    private void copyConfiguration(ObjectMapper sourceMapper, ObjectMapper targetMapper) throws Exception {
        targetMapper.setConfig(sourceMapper.getSerializationConfig());
        targetMapper.setConfig(sourceMapper.getDeserializationConfig());
        targetMapper.registerModules(this.getModules(sourceMapper));
    }

    public JsonMapper createJsonMapper() throws Exception {
        JsonMapper jsonMapper = new JsonMapper();
        this.copyConfiguration(this.objectMapper, jsonMapper);
        return jsonMapper;
    }

    public YAMLMapper createYamlMapper() throws Exception {
        YAMLMapper yamlMapper = new YAMLMapper();
        this.copyConfiguration(this.objectMapper, yamlMapper);
        return yamlMapper;
    }

    public void setPrettyPrinter(ObjectMapper objectMapper, boolean set) {
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, set);
    }

    public void setPrettyPrinter(boolean set) {
        this.setPrettyPrinter(this.objectMapper, set);
    }

    public <T> T readValue(ObjectMapper objectMapper, InputStream inputStream, Class<T> type) throws Exception {
        return objectMapper != null && inputStream != null ? objectMapper.readValue(inputStream, type) : null;
    }

    public <T> T readValue(ObjectMapper objectMapper, InputStream inputStream, TypeReference<T> type) throws Exception {
        return objectMapper != null && inputStream != null ? objectMapper.readValue(inputStream, type) : null;
    }

    public <T> T readValue(InputStream inputStream, Class<T> type) throws Exception {
        return this.readValue(this.objectMapper, inputStream, type);
    }

    public <T> T readValue(InputStream inputStream, TypeReference<T> type) throws Exception {
        return this.readValue(this.objectMapper, inputStream, type);
    }

    public <T> T readValue(ObjectMapper objectMapper, byte[] input, Class<T> type) throws Exception {
        return objectMapper != null && input != null ? objectMapper.readValue(input, type) : null;
    }

    public <T> T readValue(ObjectMapper objectMapper, byte[] input, TypeReference<T> type) throws Exception {
        return objectMapper != null && input != null ? objectMapper.readValue(input, type) : null;
    }

    public <T> T readValue(byte[] input, Class<T> type) throws Exception {
        return this.readValue(this.objectMapper, input, type);
    }

    public <T> T readValue(byte[] input, TypeReference<T> type) throws Exception {
        return this.readValue(this.objectMapper, input, type);
    }

    public <T> T readValue(ObjectMapper objectMapper, StringBuilder input, Class<T> type) throws Exception {
        return objectMapper != null && input != null ? objectMapper.readValue(input.toString(), type) : null;
    }

    public <T> T readValue(ObjectMapper objectMapper, StringBuilder input, TypeReference<T> type) throws Exception {
        return objectMapper != null && input != null ? objectMapper.readValue(input.toString(), type) : null;
    }

    public <T> T readValue(StringBuilder input, Class<T> type) throws Exception {
        return this.readValue(this.objectMapper, input, type);
    }

    public <T> T readValue(StringBuilder input, TypeReference<T> type) throws Exception {
        return this.readValue(this.objectMapper, input, type);
    }

    public <T> T readValue(ObjectMapper objectMapper, String input, Class<T> type) throws Exception {
        return objectMapper != null && input != null ? objectMapper.readValue(input, type) : null;
    }

    public <T> T readValue(ObjectMapper objectMapper, String input, TypeReference<T> type) throws Exception {
        return objectMapper != null && input != null ? objectMapper.readValue(input, type) : null;
    }

    public <T> T readValue(String input, Class<T> type) throws Exception {
        return this.readValue(this.objectMapper, input, type);
    }

    public <T> T readValue(String input, TypeReference<T> type) throws Exception {
        return this.readValue(this.objectMapper, input, type);
    }

    public void writeValue(ObjectMapper objectMapper, OutputStream outputStream, Object obj) throws Exception {
        if (objectMapper != null && outputStream != null) {
            objectMapper.writeValue(outputStream, obj);
        }

    }

    public void writeValue(ObjectMapper objectMapper, File file, Object obj) throws Exception {
        if (objectMapper != null && file != null) {
            objectMapper.writeValue(file, obj);
        }

    }

    public void writeValue(OutputStream outputStream, Object obj) throws Exception {
        this.writeValue(this.objectMapper, outputStream, obj);
    }

    public void writeValue(File file, Object obj) throws Exception {
        this.writeValue(this.objectMapper, file, obj);
    }

    public String writeValueAsString(ObjectMapper objectMapper, Object obj) throws Exception {
        return objectMapper != null && obj != null ? objectMapper.writeValueAsString(obj).trim() : null;
    }

    public String writeValueAsString(Object obj) throws Exception {
        return this.writeValueAsString(this.objectMapper, obj);
    }

    public byte[] writeValueAsBytes(ObjectMapper objectMapper, Object obj) throws Exception {
        return objectMapper != null && obj != null ? objectMapper.writeValueAsBytes(obj) : null;
    }

    public byte[] writeValueAsBytes(Object obj) throws Exception {
        return this.writeValueAsBytes(this.objectMapper, obj);
    }

    private static class StringTrimModule extends SimpleModule {
        public StringTrimModule() {
            this.addSerializer(String.class, new StdScalarSerializer<String>(String.class) {
                public void serialize(String str, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
                    if (str != null) {
                        jsonGenerator.writeString(str.trim());
                    } else {
                        jsonGenerator.writeNull();
                    }

                }
            });
            this.addDeserializer(String.class, new StdScalarDeserializer<String>(String.class) {
                public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                    return jsonParser.getValueAsString().trim();
                }
            });
        }
    }
}
