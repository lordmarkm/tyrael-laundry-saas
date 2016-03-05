package com.tyrael.laundry.reference;

/**
 *
 * @author Mark Baldwin B. Martinez on Feb 24, 2016
 *
 */
public enum EventType {

    LOGIN("Login"),
    JOB_ORDER("Job Order"),
    INV_ITEM_TYPE("Inventory Item Type"),
    INV_ITEM("Inventory Item");

    private String label;

    private EventType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public String getCode() {
        return this.name();
    }

}
