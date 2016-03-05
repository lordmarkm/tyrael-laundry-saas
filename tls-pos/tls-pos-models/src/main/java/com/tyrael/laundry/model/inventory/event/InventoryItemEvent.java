package com.tyrael.laundry.model.inventory.event;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tyrael.laundry.model.event.TlsEvent;
import com.tyrael.laundry.model.inventory.InventoryItem;

/**
 * 
 * @author Mark Martinez, created Mar 5, 2016
 *
 */
@Entity(name = "event_inv_item")
@DiscriminatorValue("INV_ITEM")
public class InventoryItemEvent extends TlsEvent {

    @ManyToOne
    @JoinColumn(name = "inv_item_id")
    private InventoryItem inventoryItem;

    public InventoryItemEvent() {
        //No-arg constructor
    }

    public InventoryItemEvent(String message, InventoryItem inventoryItem) {
        super(message);
        this.inventoryItem = inventoryItem;
        this.branch = inventoryItem.getBranch();
        this.brand = inventoryItem.getBranch().getBrand();
    }

    public InventoryItem getInventoryItem() {
        return inventoryItem;
    }

    public void setInventoryItem(InventoryItem inventoryItem) {
        this.inventoryItem = inventoryItem;
    }

}
