package com.tyrael.laundry.core.service.custom;

import static com.tyrael.laundry.model.acctspayable.QAccountsPayment.accountsPayment;

import org.springframework.data.domain.Pageable;

import com.google.common.collect.ImmutableMap;
import com.mysema.query.types.Path;
import com.tyrael.laundry.commons.dto.PageInfo;
import com.tyrael.laundry.commons.dto.acctspayable.AccountsPaymentInfo;
import com.tyrael.laundry.commons.service.TyraelJpaServiceCustom;
import com.tyrael.laundry.model.acctspayable.AccountsPayment;

/**
 *
 * @author Mark Baldwin B. Martinez on Feb 21, 2016
 *
 */
public interface AccountsPaymentServiceCustom extends
    TyraelJpaServiceCustom<AccountsPayment, AccountsPaymentInfo> {

    ImmutableMap<String, Path<?>> FIELD_MAPPING = ImmutableMap.<String, Path<?>>builder()
            .put("acctsPayableCode", accountsPayment.accountsPayable.code)
            .put("paymentDate", accountsPayment.paymentDate)
            .build();

    PageInfo<AccountsPaymentInfo> rqlSearch(String term, Pageable pageRequest);

}
