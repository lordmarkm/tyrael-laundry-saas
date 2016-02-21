package com.tyrael.laundry.core.service;

import com.tyrael.laundry.commons.service.TyraelJpaService;
import com.tyrael.laundry.core.service.custom.AccountsPayableServiceCustom;
import com.tyrael.laundry.model.acctspayable.AccountsPayable;

/**
 * 
 * @author Mark Martinez, created Feb 21, 2016
 *
 */
public interface AccountsPayableService  extends AccountsPayableServiceCustom, TyraelJpaService<AccountsPayable>{

    AccountsPayable findByCode(String code);

}
