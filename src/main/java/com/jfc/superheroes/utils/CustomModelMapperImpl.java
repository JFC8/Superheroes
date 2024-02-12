package com.jfc.superheroes.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiConsumer;

@Configuration
public class CustomModelMapperImpl implements CustomModelMapper
{

    private final ModelMapper mergeModelMapper = this.createMergeModelMapper();
    private final ModelMapper copyModelMapper = this.createCopyModelMapper();
    private final ObjectMapper objectMapper;

    public CustomModelMapperImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public ModelMapper getMergeModelMapper() {
        return this.mergeModelMapper;
    }

    public ModelMapper getCopyModelMapper() {
        return this.copyModelMapper;
    }

    public ObjectMapper getObjectMapper(){return this.objectMapper;}

    public ModelMapper createMergeModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }

    public ModelMapper createCopyModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }

    public <S, T> void addConverter(Converter<S, T> converter) {
        if (converter != null) {
            this.mergeModelMapper.addConverter(converter);
            this.copyModelMapper.addConverter(converter);
        }

    }

    private <S, T> T map(ModelMapper modelMapper, S source, T target) {
        if (source != null && target != null) {
            modelMapper.map(source, target);
        }

        return target;
    }

    private <S, T> void map(ModelMapper modelMapper, Collection<S> sourceCollection, Collection<T> targetCollection) {
        if (sourceCollection != null && targetCollection != null) {
            Iterator<S> sourceIterator = sourceCollection.iterator();
            Iterator<T> targetIterator = targetCollection.iterator();

            while(sourceIterator.hasNext() && targetIterator.hasNext()) {
                this.map(modelMapper, sourceIterator.next(), targetIterator.next());
            }
        }

    }

    private <S, T> T map(ModelMapper modelMapper, S source, Class<T> targetClass) {
        try {
            if (source != null && targetClass != null) {
                T target = targetClass.getDeclaredConstructor().newInstance();
                modelMapper.map(source, target);
                return target;
            }
        } catch (Exception var5) {
        }

        return null;
    }

    private <S, T> List<T> map(ModelMapper modelMapper, Collection<S> sourceCollection, Class<T> targetClass, BiConsumer<S, T> consumer) {
        List<T> result = new ArrayList();
        if (sourceCollection != null && targetClass != null) {
            Iterator var6 = sourceCollection.iterator();

            while(var6.hasNext()) {
                S source = (S) var6.next(); //Remove cast
                T target = this.map(modelMapper, source, targetClass);
                if (target != null) {
                    if (consumer != null) {
                        consumer.accept(source, target);
                    }

                    result.add(target);
                }
            }
        }

        return result;
    }

    private <S, T> List<T> map(ModelMapper modelMapper, Collection<S> sourceCollection, Class<T> targetClass) {
        return this.map(modelMapper, sourceCollection, targetClass, (BiConsumer)null);
    }

    private ModelMapper getModelMapper(boolean copyNulls) {
        return copyNulls ? this.copyModelMapper : this.mergeModelMapper;
    }

    public <S, T> T map(S source, T target, boolean copyNulls) {
        return this.map(this.getModelMapper(copyNulls), source, target);
    }

    private <S, T> void map(Collection<S> sourceCollection, Collection<T> targetCollection, boolean copyNulls) {
        this.map(this.getModelMapper(copyNulls), sourceCollection, targetCollection);
    }

    public <S, T> T map(S source, Class<T> targetClass, boolean copyNulls) {
        return this.map(this.getModelMapper(copyNulls), source, targetClass);
    }

    public <S, T> List<T> map(Collection<S> sourceCollection, Class<T> targetClass, boolean copyNulls) {
        return this.map(this.getModelMapper(copyNulls), sourceCollection, targetClass);
    }

    public <S, T> List<T> map(Collection<S> sourceCollection, Class<T> targetClass, BiConsumer<S, T> consumer, boolean copyNulls) {
        return this.map(this.getModelMapper(copyNulls), sourceCollection, targetClass, consumer);
    }

    public <S, T> T map(S source, T target) {
        return this.map(source, target, false);
    }

    private <S, T> void map(Collection<S> sourceCollection, Collection<T> targetCollection) {
        this.map(sourceCollection, targetCollection, false);
    }

    public <S, T> T map(S source, Class<T> targetClass) {
        return this.map(source, targetClass, false);
    }

    public <S, T> List<T> map(Collection<S> sourceCollection, Class<T> targetClass) {
        return this.map(sourceCollection, targetClass, false);
    }

    public <S, T> List<T> map(Collection<S> sourceCollection, Class<T> targetClass, BiConsumer<S, T> consumer) {
        return this.map(sourceCollection, targetClass, consumer, false);
    }

    public String writeValueAsString(ObjectMapper objectMapper, Object obj) throws Exception {
        return objectMapper != null && obj != null ? objectMapper.writeValueAsString(obj).trim() : null;
    }
    public String writeValueAsString(Object obj) throws Exception {
        return this.writeValueAsString(this.objectMapper, obj);
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
}
