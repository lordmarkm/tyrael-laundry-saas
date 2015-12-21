package com.tyrael.laundry.commons.service;

import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;

import com.google.common.collect.Lists;
import com.tyrael.laundry.commons.model.BaseEntity;

/**
 * 
 * @author Mark Martinez, created Nov 29, 2015
 *
 */
public abstract class MappingService<E extends BaseEntity, D> {

    @Autowired
    protected Mapper mapper;

    private Class<E> entityClass;
    private Class<D> dtoClass;

    @SuppressWarnings("unchecked")
    public MappingService() {
        Class<?>[] genericTypes = GenericTypeResolver.resolveTypeArguments(getClass(), MappingService.class);
        entityClass = (Class<E>) genericTypes[0];
        dtoClass = (Class<D>) genericTypes[1];
    }

    protected D toDto(E entity) {
        return null == entity ? null : mapper.map(entity, dtoClass);
    }

    protected E toEntity(D dto) {
        return null == dto ? null : mapper.map(dto, entityClass);
    }

    protected List<D> toDto(Iterable<E> entities) {
        List<D> dtos = Lists.newArrayList();
        if (null != entities) {
            for (E entity : entities) {
                dtos.add(toDto(entity));
            }
        }
        return dtos;
    }

    protected List<E> toEntity(Iterable<D> dtos) {
        List<E> entities = Lists.newArrayList();
        if (null != dtos) {
            for (D dto : dtos) {
                entities.add(toEntity(dto));
            }
        }
        return entities;
    }
}
