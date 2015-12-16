package com.tyrael.laundry.reference;

/**
 * @author mbmartinez
 */
public enum LostAndFoundStatus {
    LOST("Lost"),
    FOUND("Found"),
    NOT_FOUND("Not found");

    private String label;
    private LostAndFoundStatus(String label) {
        this.label = label;
    }
    public String getLabel() {
        return label;
    }
}
