package com.tyrael.laundry.core.service.custom;

import org.springframework.data.domain.Pageable;

import com.tyrael.laundry.commons.dto.PageInfo;
import com.tyrael.laundry.commons.dto.customer.CustomerInfo;
import com.tyrael.laundry.commons.service.TyraelJpaServiceCustom;
import com.tyrael.laundry.model.customer.Customer;

/**
 * 
 * @author Mark Martinez, create Dec 22, 2015
 *
 */
public interface CustomerServiceCustom extends TyraelJpaServiceCustom<Customer, CustomerInfo> {

    PageInfo<CustomerInfo> pageInfo(String term, Pageable page);
    CustomerInfo findInfoByCode(String customerCode);

}
