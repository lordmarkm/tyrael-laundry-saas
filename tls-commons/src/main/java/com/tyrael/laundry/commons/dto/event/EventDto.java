package com.tyrael.laundry.commons.dto.event;

import com.tyrael.laundry.commons.dto.BaseDto;
import com.tyrael.laundry.commons.dto.BranchDto;
import com.tyrael.laundry.commons.dto.BrandDto;
import com.tyrael.laundry.commons.dto.customer.CustomerInfo;
import com.tyrael.laundry.commons.dto.inventory.InventoryItemInfo;
import com.tyrael.laundry.commons.dto.inventory.InventoryItemTypeInfo;
import com.tyrael.laundry.commons.dto.joborder.JobOrderInfo;
import com.tyrael.laundry.reference.EventType;

/**
 *
 * @author Mark Baldwin B. Martinez on Feb 27, 2016
 *
 */
public class EventDto extends BaseDto {

    private EventType eventType;
    private String message;
    private BrandDto brand;
    private BranchDto branch;

    //InventoryItemType only
    private InventoryItemTypeInfo inventoryItemType;

    //InventoryItem only
    private InventoryItemInfo inventoryItem;

    //JobOrderEvent only
    private JobOrderInfo jobOrder;

    //CustomerEvent only
    private CustomerInfo customer;

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JobOrderInfo getJobOrder() {
        return jobOrder;
    }

    public void setJobOrder(JobOrderInfo jobOrder) {
        this.jobOrder = jobOrder;
    }

    public BranchDto getBranch() {
        return branch;
    }

    public void setBranch(BranchDto branch) {
        this.branch = branch;
    }

    public BrandDto getBrand() {
        return brand;
    }

    public void setBrand(BrandDto brand) {
        this.brand = brand;
    }

    public InventoryItemTypeInfo getInventoryItemType() {
        return inventoryItemType;
    }

    public void setInventoryItemType(InventoryItemTypeInfo inventoryItemType) {
        this.inventoryItemType = inventoryItemType;
    }

    public InventoryItemInfo getInventoryItem() {
        return inventoryItem;
    }

    public void setInventoryItem(InventoryItemInfo inventoryItem) {
        this.inventoryItem = inventoryItem;
    }

    public CustomerInfo getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerInfo customer) {
        this.customer = customer;
    }

}
