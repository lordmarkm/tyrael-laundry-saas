package com.tyrael.laundry.core.service.custom;

import static com.tyrael.laundry.model.joborder.QJobOrder.jobOrder;

import org.springframework.data.domain.Pageable;

import com.google.common.collect.ImmutableMap;
import com.mysema.query.types.Path;
import com.tyrael.laundry.commons.dto.PageInfo;
import com.tyrael.laundry.commons.dto.joborder.JobOrderInfo;
import com.tyrael.laundry.commons.service.TyraelJpaServiceCustom;
import com.tyrael.laundry.model.joborder.JobOrder;

public interface JobOrderServiceCustom extends TyraelJpaServiceCustom<JobOrder, JobOrderInfo> {

    ImmutableMap<String, Path<?>> FIELD_MAPPING = ImmutableMap.<String, Path<?>>builder()
            .put("trackingNo", jobOrder.trackingNo)
            .put("id", jobOrder.id)
            .put("deleted", jobOrder.deleted)
            .put("dateReceived", jobOrder.dateReceived)
            .put("customerId", jobOrder.customer.id)
            .put("customerCode", jobOrder.customer.code)
            .put("customerSurname", jobOrder.customer.name.surname)
            .put("customerGivenName", jobOrder.customer.name.givenName)
            .put("status", jobOrder.status)
            .put("branchCode", jobOrder.branch.code)
            .put("brandCode", jobOrder.branch.brand.code)
            .build();

    JobOrderInfo findByTrackinNoInfo(String trackingNo);
    PageInfo<JobOrderInfo> rqlSearch(String term, Pageable pageRequest);

}
