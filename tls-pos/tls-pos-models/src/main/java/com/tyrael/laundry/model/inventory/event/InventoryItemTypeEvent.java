package com.tyrael.laundry.model.inventory.event;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tyrael.laundry.model.event.TlsEvent;
import com.tyrael.laundry.model.inventory.InventoryItemType;

@Entity(name = "event_inv_item_type")
@DiscriminatorValue("INV_ITEM_TYPE")
public class InventoryItemTypeEvent extends TlsEvent {

    @ManyToOne
    @JoinColumn(name = "inv_item_type_id")
    private InventoryItemType inventoryItemType;

    public InventoryItemTypeEvent() {
        //No-arg constructor
    }

    public InventoryItemTypeEvent(String message, InventoryItemType inventoryItemType) {
        super(message);
        this.inventoryItemType = inventoryItemType;
        this.brand = inventoryItemType.getBrand();
    }

    public InventoryItemType getInventoryItemType() {
        return inventoryItemType;
    }

    public void setInventoryItemType(InventoryItemType inventoryItemType) {
        this.inventoryItemType = inventoryItemType;
    }

}
