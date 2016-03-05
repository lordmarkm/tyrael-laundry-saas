package com.tyrael.laundry.commons.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.types.Predicate;
import com.tyrael.laundry.commons.dto.PageInfo;
import com.tyrael.laundry.commons.model.BaseEntity;

/**
 * 
 * @author Mark Martinez, created Nov 29, 2015
 *
 */
@Transactional
public abstract class TyraelJpaServiceCustomImpl<E extends BaseEntity, D, R extends TyraelJpaService<E>>
    extends MappingService<E, D> implements TyraelJpaServiceCustom<E, D> {

    @Autowired
    protected R repo;

    public D findOneInfo(Long id) {
        return toDto(repo.findOne(id));
    }

    public D saveInfo(D dto) {
        return toDto(repo.save(toEntity(dto)));
    }

    public E saveInfoAndGetEntity(D dto) {
        return repo.save(toEntity(dto));
    }

    public List<D> saveInfo(Iterable<D> dtos) {
        return toDto(repo.save(toEntity(dtos)));
    }

    public List<E> saveInfoAndGetEntity(Iterable<D> dtos) {
        return repo.save(toEntity(dtos));
    }

    public PageInfo<D> pageInfo(Pageable page) {
        Page<E> results = repo.findAll(page);
        return toPageInfo(results);
    }

    public PageInfo<D> pageInfo(Predicate predicate, Pageable page) {
        Page<E> results = repo.findAll(predicate, page);
        return toPageInfo(results);
    }

    public PageInfo<D> toPageInfo(Page<E> page) {
        List<D> infos = toDto(page);

        PageInfo<D> pageResponse = new PageInfo<>();
        pageResponse.setData(infos);
        pageResponse.setTotal(page.getTotalElements());
        return pageResponse;
    }

    @Override
    public D deleteInfo(Long id) {
        E entity = repo.findOne(id);
        entity.setDeleted(true);
        return toDto(entity);
    }

}
