package com.tyrael.laundry.core.service;

import com.tyrael.laundry.commons.service.TyraelJpaService;
import com.tyrael.laundry.core.service.custom.CustomerServiceCustom;
import com.tyrael.laundry.model.customer.Customer;

/**
 * 
 * @author Mark Martinez, created Nov 29, 2015
 *
 */
public interface CustomerService extends CustomerServiceCustom, TyraelJpaService<Customer> {

    Customer findByCode(String code);

}
