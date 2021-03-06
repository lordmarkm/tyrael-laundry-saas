package com.tyrael.laundry.pos.api.resource;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.math.BigDecimal;
import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tyrael.laundry.commons.dto.PageInfo;
import com.tyrael.laundry.commons.dto.inventory.InventoryItemInfo;
import com.tyrael.laundry.commons.resource.BaseResource;
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

    private static Logger LOG = LoggerFactory.getLogger(InventoryItemResource.class);

    @RequestMapping(method = GET, params = "term")
    public ResponseEntity<PageInfo<InventoryItemInfo>> page(Principal principal,
            Pageable page, @RequestParam String term) {
        LOG.debug("InventoryItem rql query. Principal={}, page={}, term={}", principal, page, term);

        return new ResponseEntity<>(service.rqlSearch(term, page), OK);
    }

    @RequestMapping(method = GET, params = "invItemCode")
    public ResponseEntity<InventoryItemInfo> findByCode(@RequestParam String invItemCode) {
        return new ResponseEntity<>(service.findInfoByCode(invItemCode), OK);
    }

    @RequestMapping(value = "/{invItemCode}/{quantity:.+}", method = PUT)
    public ResponseEntity<InventoryItemInfo> restock(@PathVariable String invItemCode, @PathVariable BigDecimal quantity) {
        LOG.debug("Restock request. code={}, qty={}", invItemCode, quantity);
        return new ResponseEntity<>(service.restock(invItemCode, quantity), OK);
    }

    @RequestMapping(value = "/consume/{invItemCode}/{quantity:.+}", method = PUT)
    public ResponseEntity<InventoryItemInfo> consume(@PathVariable String invItemCode, @PathVariable BigDecimal quantity) {
        LOG.debug("Restock request. code={}, qty={}", invItemCode, quantity);
        return new ResponseEntity<>(service.consume(invItemCode, quantity), OK);
    }

}
