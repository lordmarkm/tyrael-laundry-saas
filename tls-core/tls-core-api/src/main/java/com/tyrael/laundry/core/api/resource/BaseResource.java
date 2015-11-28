package com.tyrael.laundry.core.api.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * 
 * @author Mark Martinez, created Nov 29, 2015
 *
 */
@RestController
public abstract class BaseResource<D, R extends TyraelJpaRepo> {

    @RequestMapping(value = "/{id}", method = GET)
    public abstract ResponseEntity<D> findOne(@PathVariable Long id);

    @RequestMapping(method = POST)
    public abstract ResponseEntity<D> save(D dto);

}
