package com.tyrael.laundry.core.api.resource;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tyrael.laundry.commons.dto.PageInfo;
import com.tyrael.laundry.commons.model.BaseEntity;
import com.tyrael.laundry.commons.service.TyraelJpaServiceCustom;

/**
 * 
 * @author Mark Martinez, created Nov 29, 2015
 *
 */
public abstract class BaseResource<E extends BaseEntity, D, S extends TyraelJpaServiceCustom<E, D>> {

    @Autowired
    private S service;

    @RequestMapping(method = GET)
    public ResponseEntity<PageInfo<D>> getPage(Pageable page) {
        return new ResponseEntity<>(service.pageInfo(page), OK);
    }

    @RequestMapping(value = "/{id}", method = GET)
    public ResponseEntity<D> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(service.findOneInfo(id), OK);
    }

    @RequestMapping(method = POST)
    public ResponseEntity<D> save(@RequestBody D dto) {
        return new ResponseEntity<>(service.saveInfo(dto), OK);
    }

}
