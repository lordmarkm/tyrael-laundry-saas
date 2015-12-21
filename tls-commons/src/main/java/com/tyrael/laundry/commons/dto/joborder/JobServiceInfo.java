package com.tyrael.laundry.commons.dto.joborder;

import java.math.BigDecimal;

import org.springframework.core.style.ToStringCreator;

import com.tyrael.laundry.commons.dto.BaseDto;


public class JobServiceInfo extends BaseDto {

    private ServiceTypeInfo serviceType;
    private BigDecimal weightInKilos;
    private BigDecimal pricePerKilo;
    private BigDecimal amount;

    @Override
    protected ToStringCreator toStringCreator() {
        return new ToStringCreator(this)
                .append("serviceType", serviceType)
                .append("weight", weightInKilos + " Kg")
                .append("price/kilo", pricePerKilo)
                .append("amount", amount);
    }

    public ServiceTypeInfo getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceTypeInfo serviceType) {
        this.serviceType = serviceType;
    }

    public BigDecimal getWeightInKilos() {
        return weightInKilos;
    }

    public void setWeightInKilos(BigDecimal weightInKilos) {
        this.weightInKilos = weightInKilos;
    }

    public BigDecimal getPricePerKilo() {
        return pricePerKilo;
    }

    public void setPricePerKilo(BigDecimal pricePerKilo) {
        this.pricePerKilo = pricePerKilo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
