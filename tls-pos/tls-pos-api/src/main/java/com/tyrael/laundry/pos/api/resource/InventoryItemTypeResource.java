package com.tyrael.laundry.pos.api.resource;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tyrael.laundry.commons.resource.BaseResource;
import com.tyrael.laundry.dto.inventory.InventoryItemTypeInfo;
import com.tyrael.laundry.model.inventory.InventoryItemType;
import com.tyrael.laundry.pos.service.InventoryItemTypeService;

@RestController
@RequestMapping("/inv-item-type")
public class InventoryItemTypeResource
    extends BaseResource<InventoryItemType, InventoryItemTypeInfo, InventoryItemTypeService> {

    @RequestMapping(method = GET, params = "invItemTypeCode")
    public ResponseEntity<InventoryItemTypeInfo> findByCode(@RequestParam String invItemTypeCode) {
        return new ResponseEntity<>(service.findInfoByCode(invItemTypeCode), OK);
    }

    @RequestMapping(method = GET, params = "brandCode")
    public ResponseEntity<List<InventoryItemTypeInfo>> findByBrandCode(@RequestParam String brandCode) {
        return new ResponseEntity<>(service.findInfoByBrandCode(brandCode), OK);
    }

    @RequestMapping(method = GET, params = "branchCode")
    public ResponseEntity<List<InventoryItemTypeInfo>> findByBranchCode(@RequestParam String branchCode) {
        return new ResponseEntity<>(service.findInfoByBranchCode(branchCode), OK);
    }

}
