package com.jfc.superheroes.utils;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

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
}
