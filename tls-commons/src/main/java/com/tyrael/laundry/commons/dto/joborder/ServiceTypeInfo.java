package com.tyrael.laundry.commons.dto.joborder;

import java.math.BigDecimal;

import org.springframework.core.style.ToStringCreator;

import com.tyrael.laundry.commons.dto.BaseDto;

/**
 * @author mbmartinez
 */
public class ServiceTypeInfo extends BaseDto {

    private String code;
    private String label;
    private String icon;
    private boolean enabled;
    private BigDecimal pricePerKilo;

    @Override
    public ToStringCreator toStringCreator() {
        return super.toStringCreator()
                .append("code", code)
                .append("label", label)
                .append("icon", icon)
                .append("enabled", enabled)
                .append("price/kilo", pricePerKilo);
    }

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
