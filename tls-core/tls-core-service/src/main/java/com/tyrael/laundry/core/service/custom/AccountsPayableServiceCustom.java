package com.tyrael.laundry.core.service.custom;

import com.tyrael.laundry.commons.dto.acctspayable.AccountsPayableInfo;
import com.tyrael.laundry.commons.service.TyraelJpaServiceCustom;
import com.tyrael.laundry.model.acctspayable.AccountsPayable;

/**
 * 
 * @author Mark Martinez, created Feb 21, 2016
 *
 */
public interface AccountsPayableServiceCustom
    extends TyraelJpaServiceCustom<AccountsPayable, AccountsPayableInfo> {

    AccountsPayableInfo findInfoByCode(String code);

}
