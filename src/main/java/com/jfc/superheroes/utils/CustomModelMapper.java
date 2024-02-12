package com.jfc.superheroes.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;

public interface CustomModelMapper
{
    ModelMapper getMergeModelMapper();
    ModelMapper getCopyModelMapper();
    ModelMapper createMergeModelMapper();
    ModelMapper createCopyModelMapper();
    <S, T> void addConverter(Converter<S, T> converter);
    <S, T> T map(S source, T target, boolean copyNulls);
    <S, T> T map(S source, Class<T> targetClass, boolean copyNulls);
    <S, T> List<T> map(Collection<S> sourceCollection, Class<T> targetClass, boolean copyNulls);
    <S, T> List<T> map(Collection<S> sourceCollection, Class<T> targetClass, BiConsumer<S, T> consumer, boolean copyNulls);
    <S, T> T map(S source, T target);
    <S, T> T map(S source, Class<T> targetClass);
    <S, T> List<T> map(Collection<S> sourceCollection, Class<T> targetClass);
    <S, T> List<T> map(Collection<S> sourceCollection, Class<T> targetClass, BiConsumer<S, T> consumer);
    String writeValueAsString(ObjectMapper objectMapper, Object obj) throws Exception;
    String writeValueAsString(Object obj) throws Exception;

   <T> T readValue(ObjectMapper objectMapper, InputStream inputStream, Class<T> type) throws Exception;
   <T> T readValue(ObjectMapper objectMapper, InputStream inputStream, TypeReference<T> type) throws Exception ;
   <T> T readValue(InputStream inputStream, Class<T> type) throws Exception ;
   <T> T readValue(InputStream inputStream, TypeReference<T> type) throws Exception ;
   <T> T readValue(ObjectMapper objectMapper, byte[] input, Class<T> type) throws Exception ;
   <T> T readValue(ObjectMapper objectMapper, byte[] input, TypeReference<T> type) throws Exception ;
   <T> T readValue(byte[] input, Class<T> type) throws Exception ;
   <T> T readValue(byte[] input, TypeReference<T> type) throws Exception ;
   <T> T readValue(ObjectMapper objectMapper, StringBuilder input, Class<T> type) throws Exception ;
   <T> T readValue(ObjectMapper objectMapper, StringBuilder input, TypeReference<T> type) throws Exception ;
   <T> T readValue(StringBuilder input, Class<T> type) throws Exception ;
   <T> T readValue(StringBuilder input, TypeReference<T> type) throws Exception ;
   <T> T readValue(ObjectMapper objectMapper, String input, Class<T> type) throws Exception ;
   <T> T readValue(ObjectMapper objectMapper, String input, TypeReference<T> type) throws Exception ;
   <T> T readValue(String input, Class<T> type) throws Exception;
   <T> T readValue(String input, TypeReference<T> type) throws Exception;

}
