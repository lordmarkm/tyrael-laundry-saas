package com.tyrael.laundry.pos.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tyrael.laundry.commons.dto.inventory.InventoryItemTypeInfo;
import com.tyrael.laundry.core.service.EventService;
import com.tyrael.laundry.model.inventory.InventoryItemType;
import com.tyrael.laundry.model.inventory.event.InventoryItemTypeEvent;
import com.tyrael.laundry.pos.service.InventoryItemTypeService;

/**
 *
 * @author Mark Martinez, created Mar 3, 2016
 *
 */
@Aspect
@Component
public class InventoryItemEventCreator {

    private static final Logger LOG = LoggerFactory.getLogger(InventoryItemEventCreator.class);

    @Autowired
    private EventService eventService;

    @Autowired
    private InventoryItemTypeService inventoryItemTypeService;

    @Around("execution(* com.tyrael.laundry.pos.service.custom.impl.InventoryItemTypeServiceCustomImpl.saveInfo(..))")
    public Object logEvent(ProceedingJoinPoint jp) throws Throwable {
        InventoryItemTypeInfo arg = (InventoryItemTypeInfo) jp.getArgs()[0];
        String argCode = arg.getCode();
        String message = null;
        if (null == argCode) {
            message = "Inventory item type has been created";
        } else {
            message = "Inventory item type has been updated";
        }

        LOG.debug("Creating inventory item type event. arg={}", arg);

        InventoryItemTypeInfo inventoryItemTypeInfo =  (InventoryItemTypeInfo) jp.proceed();
        InventoryItemType inventoryItemType = inventoryItemTypeService.findOne(inventoryItemTypeInfo.getId());
        if (null != inventoryItemType) {
            InventoryItemTypeEvent iite = new InventoryItemTypeEvent(message, inventoryItemType);
            eventService.save(iite);
        }

        return inventoryItemTypeInfo;
    }

}
