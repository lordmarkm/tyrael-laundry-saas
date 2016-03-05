package com.tyrael.laundry.pos.aspect;

import java.math.BigDecimal;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tyrael.laundry.commons.dto.inventory.InventoryItemInfo;
import com.tyrael.laundry.core.service.EventService;
import com.tyrael.laundry.model.inventory.InventoryItem;
import com.tyrael.laundry.model.inventory.event.InventoryItemEvent;
import com.tyrael.laundry.pos.service.InventoryItemService;

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
    private InventoryItemService inventoryItemService;

    @Around("execution(* com.tyrael.laundry.pos.service.custom.impl.InventoryItemServiceCustomImpl.saveInfo(..)) && args(dto)")
    public Object logSaveOrUpdateEvent(ProceedingJoinPoint jp, InventoryItemInfo dto) throws Throwable {
        LOG.debug("Creating inventory item create/update event. arg={}", dto);

        String argCode = dto.getCode();
        String message = null;
        if (null == argCode) {
            message = "Inventory item record has been created";
        } else {
            message = "Inventory item record been updated";
        }

        InventoryItemInfo inventoryItemInfo =  (InventoryItemInfo) jp.proceed();
        InventoryItem inventoryItem = inventoryItemService.findOne(inventoryItemInfo.getId());
        if (null != inventoryItem) {
            InventoryItemEvent iie = new InventoryItemEvent(message, inventoryItem);
            eventService.save(iie);
        }

        return inventoryItemInfo;
    }

    @AfterReturning("execution(* com.tyrael.laundry.pos.service.custom.impl.InventoryItemServiceCustomImpl.restock(..)) && args(invItemCode, quantity)")
    public void logRestockEvent(JoinPoint joinPoint, String invItemCode, BigDecimal quantity) {
        LOG.debug("Logging restock event. invItemCode={}, qty={}", invItemCode, quantity);
        InventoryItem inventoryItem = inventoryItemService.findByCode(invItemCode);
        if (null != inventoryItem) {
            InventoryItemEvent iie = new InventoryItemEvent("Inventory item has been restocked. Restocked qty = " + quantity, inventoryItem);
            eventService.save(iie);
        }
    }

    @AfterReturning("execution(* com.tyrael.laundry.pos.service.custom.impl.InventoryItemServiceCustomImpl.consume(..)) && args(invItemCode, quantity)")
    public void logConsumeEvent(JoinPoint joinPoint, String invItemCode, BigDecimal quantity) {
        LOG.debug("Logging consume event. invItemCode={}, qty={}", invItemCode, quantity);
        InventoryItem inventoryItem = inventoryItemService.findByCode(invItemCode);
        if (null != inventoryItem) {
            InventoryItemEvent iie = new InventoryItemEvent("Inventory item has been consumed. Consumed qty = " + quantity, inventoryItem);
            eventService.save(iie);
        }
    }
}
