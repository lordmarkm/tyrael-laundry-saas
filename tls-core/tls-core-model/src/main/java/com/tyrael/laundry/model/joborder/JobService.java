package com.tyrael.laundry.model.joborder;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.baldy.commons.models.BaseEntity;

/**
 * @author mbmartinez
 */
@Entity(name = "JOB_SERVICE")
public class JobService extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "SERVICE_TYPE", nullable = false)
    private ServiceType serviceType;

    @Column(name = "WT_KG", nullable = false)
    private BigDecimal weightInKilos;

    @Column(name = "PRICE_KG", nullable = false)
    private BigDecimal pricePerKilo;

    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "JOB_ID", nullable = false)
    private JobOrder jobOrder;

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
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

    public JobOrder getJobOrder() {
        return jobOrder;
    }

    public void setJobOrder(JobOrder jobOrder) {
        this.jobOrder = jobOrder;
    }

}
