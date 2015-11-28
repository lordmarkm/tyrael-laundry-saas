package com.tyrael.laundry.commons.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.tyrael.laundry.commons.model.BaseEntity;

/**
 * 
 * @author Mark Martinez, created Nov 29, 2015
 *
 */
@NoRepositoryBean
public interface TyraelJpaService<E extends BaseEntity, D>
    extends TyraelJpaServiceCustom<E, D>,
            JpaRepository<E, Long>, QueryDslPredicateExecutor<E> {

}
