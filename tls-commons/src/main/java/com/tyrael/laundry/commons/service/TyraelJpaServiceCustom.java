package com.tyrael.laundry.commons.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.tyrael.laundry.commons.dto.PageInfo;
import com.tyrael.laundry.commons.model.BaseEntity;

/**
 * 
 * @author Mark Martinez, created Nov 29, 2015
 *
 */
@Transactional
public interface TyraelJpaServiceCustom<E extends BaseEntity, D> {

    D findOneInfo(Long id);
    D saveInfo(D dto);
    E saveInfoAndGetEntity(D dto);
    List<D> saveInfo(Iterable<D> dtos);
    List<E> saveInfoAndGetEntity(Iterable<D> dtos);

    PageInfo<D> pageInfo(Pageable page);

}
