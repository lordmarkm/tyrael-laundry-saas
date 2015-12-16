package com.tyrael.laundry.model.joborder;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.tyrael.laundry.commons.model.BaseEntity;

/**
 * @author mbmartinez
 */
@Entity(name = "service_type")
public class ServiceType extends BaseEntity {

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "label", nullable = false)
    private String label;

    @Column(name = "icon", nullable = false)
    private String icon;

    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

    @Column(name = "price_per_kilo", nullable = false)
    private BigDecimal pricePerKilo;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public BigDecimal getPricePerKilo() {
        return pricePerKilo;
    }

    public void setPricePerKilo(BigDecimal pricePerKilo) {
        this.pricePerKilo = pricePerKilo;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
