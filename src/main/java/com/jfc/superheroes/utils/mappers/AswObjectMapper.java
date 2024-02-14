package com.jfc.superheroes.utils.mappers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public interface AswObjectMapper
{
    JsonMapper createJsonMapper() throws Exception;
    YAMLMapper createYamlMapper() throws Exception;
    void setPrettyPrinter(ObjectMapper objectMapper, boolean set);
    void setPrettyPrinter(boolean set);
    <T> T readValue(ObjectMapper objectMapper, InputStream inputStream, Class<T> type) throws Exception;
    <T> T readValue(ObjectMapper objectMapper, InputStream inputStream, TypeReference<T> type) throws Exception;
    <T> T readValue(InputStream inputStream, Class<T> type) throws Exception;
    <T> T readValue(InputStream inputStream, TypeReference<T> type) throws Exception;
    <T> T readValue(ObjectMapper objectMapper, byte[] input, Class<T> type) throws Exception;
    <T> T readValue(ObjectMapper objectMapper, byte[] input, TypeReference<T> type) throws Exception;
    <T> T readValue(byte[] input, Class<T> type) throws Exception;
    <T> T readValue(byte[] input, TypeReference<T> type) throws Exception;
    <T> T readValue(ObjectMapper objectMapper, StringBuilder input, Class<T> type) throws Exception;
    <T> T readValue(ObjectMapper objectMapper, StringBuilder input, TypeReference<T> type) throws Exception;
    <T> T readValue(StringBuilder input, Class<T> type) throws Exception;
    <T> T readValue(StringBuilder input, TypeReference<T> type) throws Exception;
    <T> T readValue(ObjectMapper objectMapper, String input, Class<T> type) throws Exception;
    <T> T readValue(ObjectMapper objectMapper, String input, TypeReference<T> type) throws Exception;
    <T> T readValue(String input, Class<T> type) throws Exception;
    <T> T readValue(String input, TypeReference<T> type) throws Exception;
    void writeValue(ObjectMapper objectMapper, OutputStream outputStream, Object obj) throws Exception;
    void writeValue(ObjectMapper objectMapper, File file, Object obj) throws Exception;
    void writeValue(OutputStream outputStream, Object obj) throws Exception;
    void writeValue(File file, Object obj) throws Exception;
    String writeValueAsString(ObjectMapper objectMapper, Object obj) throws Exception;
    String writeValueAsString(Object obj) throws Exception;
    byte[] writeValueAsBytes(ObjectMapper objectMapper, Object obj) throws Exception;
    byte[] writeValueAsBytes(Object obj) throws Exception;

}
