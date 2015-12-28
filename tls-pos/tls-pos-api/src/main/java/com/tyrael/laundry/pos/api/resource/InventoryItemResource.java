package com.tyrael.laundry.pos.api.resource;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tyrael.laundry.commons.resource.BaseResource;
import com.tyrael.laundry.dto.inventory.InventoryItemInfo;
import com.tyrael.laundry.model.inventory.InventoryItem;
import com.tyrael.laundry.pos.service.InventoryItemService;

/**
 * 
 * @author Mark Martinez, create Dec 28, 2015
 *
 */
@RestController
@RequestMapping("/inv-item")
public class InventoryItemResource
    extends BaseResource<InventoryItem, InventoryItemInfo, InventoryItemService> {

    @RequestMapping(method = GET, params = "invItemCode")
    public ResponseEntity<InventoryItemInfo> findByCode(@RequestParam String invItemCode) {
        return new ResponseEntity<>(service.findInfoByCode(invItemCode), OK);
    }

}
