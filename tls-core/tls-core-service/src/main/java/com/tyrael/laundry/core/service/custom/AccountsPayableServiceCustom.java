package com.tyrael.laundry.core.service.custom;

import static com.tyrael.laundry.model.acctspayable.QAccountsPayable.accountsPayable;

import org.springframework.data.domain.Pageable;

import com.google.common.collect.ImmutableMap;
import com.mysema.query.types.Path;
import com.tyrael.laundry.commons.dto.PageInfo;
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

    ImmutableMap<String, Path<?>> FIELD_MAPPING = ImmutableMap.<String, Path<?>>builder()
            .put("code", accountsPayable.code)
            .put("branchCode", accountsPayable.branch.code)
            .put("lastPayment", accountsPayable.lastPayment)
            .build();

    AccountsPayableInfo findInfoByCode(String code);
    PageInfo<AccountsPayableInfo> rqlSearch(String term, Pageable pageRequest);
    int countDue();

}
