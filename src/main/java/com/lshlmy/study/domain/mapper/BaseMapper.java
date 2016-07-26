package com.lshlmy.study.domain.mapper;

/**
 * @author lshlmy
 * @description mybatis 增、删、改、查接口
 */
public interface BaseMapper<T> {

    Integer insert(T t) throws Exception;

    Integer deleteByEntity(T entity) throws Exception;

    Integer deleteById(String id) throws Exception;

    Integer update(T t) throws Exception;

    T selectById(String id) throws Exception;

}
