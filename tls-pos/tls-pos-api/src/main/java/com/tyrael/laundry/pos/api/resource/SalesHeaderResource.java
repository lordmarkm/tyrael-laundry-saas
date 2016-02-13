package com.tyrael.laundry.pos.api.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyrael.laundry.dto.inventory.SalesHeaderInfo;
import com.tyrael.laundry.pos.service.SalesHeaderService;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.HttpStatus.*;

/**
 * 
 * @author Mark Martinez, create Feb 10, 2016
 *
 */
@RestController
@RequestMapping("/sales-header")
public class SalesHeaderResource {

    private static final Logger LOG = LoggerFactory.getLogger(SalesHeaderResource.class);

    @Autowired
    private SalesHeaderService salesHeaderService;

    @RequestMapping(value = "/{id}", method = GET)
    public ResponseEntity<SalesHeaderInfo> view(@PathVariable Long id) {
        return new ResponseEntity<>(salesHeaderService.findOneInfo(id), OK);
    }

    @RequestMapping(method = POST)
    public ResponseEntity<SalesHeaderInfo> checkout(@RequestBody SalesHeaderInfo salesHeader) {
        LOG.debug("Sales request. header={}", salesHeader);
        return new ResponseEntity<SalesHeaderInfo>(salesHeaderService.makeSale(salesHeader), OK);
    }

}
