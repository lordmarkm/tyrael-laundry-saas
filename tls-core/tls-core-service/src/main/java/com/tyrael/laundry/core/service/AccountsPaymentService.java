package com.tyrael.laundry.core.service;

import com.tyrael.laundry.commons.service.TyraelJpaService;
import com.tyrael.laundry.core.service.custom.AccountsPaymentServiceCustom;
import com.tyrael.laundry.model.acctspayable.AccountsPayment;

/**
 * 
 * @author Mark Martinez, created Feb 21, 2016
 *
 */
public interface AccountsPaymentService  extends AccountsPaymentServiceCustom, TyraelJpaService<AccountsPayment>{

}
