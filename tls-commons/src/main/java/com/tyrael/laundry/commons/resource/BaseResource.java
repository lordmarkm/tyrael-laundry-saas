package com.tyrael.laundry.commons.resource;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tyrael.laundry.commons.dto.PageInfo;
import com.tyrael.laundry.commons.model.BaseEntity;
import com.tyrael.laundry.commons.service.TyraelJpaServiceCustom;

/**
 * 
 * @author Mark Martinez, created Nov 29, 2015
 *
 */
public abstract class BaseResource<E extends BaseEntity, D, S extends TyraelJpaServiceCustom<E, D>> {

    private static Logger LOG = LoggerFactory.getLogger(BaseResource.class);

    @Autowired
    protected S service;

    protected <T> ResponseEntity<T> ok(T result) {
        return new ResponseEntity<>(result, OK);
    }

    @RequestMapping(method = GET)
    public ResponseEntity<PageInfo<D>> getPage(Pageable page) {
        LOG.debug("Page request. page={}", page);
        return new ResponseEntity<>(getPageImpl(page), OK);
    }

    /**
     * Override this to override getPage behavior
     */
    protected PageInfo<D> getPageImpl(Pageable page) {
        return service.pageInfo(page);
    }

    @RequestMapping(value = "/{id}", method = GET)
    public ResponseEntity<D> findOne(@PathVariable Long id) {
        LOG.debug("Find by id request. id={}", id);
        return new ResponseEntity<>(service.findOneInfo(id), OK);
    }

    @RequestMapping(method = POST)
    public ResponseEntity<D> save(@RequestBody D dto) {
        LOG.debug("Save request. dto={}", dto);
        return new ResponseEntity<>(service.saveInfo(dto), OK);
    }

    @RequestMapping(method = DELETE)
    public ResponseEntity<D> delete(@RequestParam Long id) {
        LOG.debug("Delete request. id={}", id);
        return ok(service.deleteInfo(id));
    }

}
