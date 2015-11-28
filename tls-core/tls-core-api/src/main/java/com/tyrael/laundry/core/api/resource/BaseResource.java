package com.tyrael.laundry.core.api.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyrael.laundry.commons.model.BaseEntity;
import com.tyrael.laundry.commons.service.TyraelJpaService;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import static org.springframework.http.HttpStatus.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author Mark Martinez, created Nov 29, 2015
 *
 */
@RestController
public abstract class BaseResource<E extends BaseEntity, D, S extends TyraelJpaService<E, D>> {

    @Autowired
    private S service;

    @RequestMapping(value = "/{id}", method = GET)
    public ResponseEntity<D> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(service.findOneInfo(id), OK);
    }

    @RequestMapping(method = POST)
    public ResponseEntity<D> save(@RequestBody D dto) {
        return new ResponseEntity<>(service.saveInfo(dto), OK);
    }

}
