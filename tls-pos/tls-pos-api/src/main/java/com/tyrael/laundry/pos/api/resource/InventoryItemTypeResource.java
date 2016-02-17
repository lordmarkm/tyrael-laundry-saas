package com.tyrael.laundry.pos.api.resource;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tyrael.laundry.commons.dto.PageInfo;
import com.tyrael.laundry.commons.resource.BaseResource;
import com.tyrael.laundry.dto.inventory.InventoryItemInfo;
import com.tyrael.laundry.dto.inventory.InventoryItemTypeInfo;
import com.tyrael.laundry.model.inventory.InventoryItemType;
import com.tyrael.laundry.pos.service.InventoryItemTypeService;

@RestController
@RequestMapping("/inv-item-type")
public class InventoryItemTypeResource
    extends BaseResource<InventoryItemType, InventoryItemTypeInfo, InventoryItemTypeService> {

    private static final Logger LOG = LoggerFactory.getLogger(InventoryItemTypeResource.class);

    @RequestMapping(method = GET, params = "term")
    public ResponseEntity<PageInfo<InventoryItemTypeInfo>> page(Principal principal,
            Pageable page, @RequestParam String term) {
        LOG.debug("InventoryItem rql query. Principal={}, page={}, term={}", principal, page, term);

        return new ResponseEntity<>(service.rqlSearch(term, page), OK);
    }

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
