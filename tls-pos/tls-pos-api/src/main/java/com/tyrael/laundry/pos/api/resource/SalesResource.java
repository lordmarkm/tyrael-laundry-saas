package com.tyrael.laundry.pos.api.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyrael.laundry.dto.inventory.SalesHeaderInfo;
import com.tyrael.laundry.pos.service.SalesHeaderService;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.HttpStatus.*;

/**
 * 
 * @author Mark Martinez, create Feb 10, 2016
 *
 */
@RestController
@RequestMapping("/sales")
public class SalesResource {

    @Autowired
    private SalesHeaderService salesHeaderService;

    @RequestMapping(method = POST)
    public ResponseEntity<SalesHeaderInfo> checkout(SalesHeaderInfo salesHeader) {
        return new ResponseEntity<SalesHeaderInfo>(salesHeaderService.makeSale(salesHeader), OK);
    }

}
