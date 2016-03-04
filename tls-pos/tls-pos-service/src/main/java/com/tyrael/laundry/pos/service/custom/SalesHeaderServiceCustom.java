package com.tyrael.laundry.pos.service.custom;

import static com.tyrael.laundry.model.inventory.QSalesHeader.salesHeader;

import org.springframework.data.domain.Pageable;

import com.google.common.collect.ImmutableMap;
import com.mysema.query.types.Path;
import com.tyrael.laundry.commons.dto.PageInfo;
import com.tyrael.laundry.commons.dto.inventory.SalesHeaderInfo;
import com.tyrael.laundry.commons.service.TyraelJpaServiceCustom;
import com.tyrael.laundry.model.inventory.SalesHeader;

/**
 * 
 * @author Mark Martinez, create Feb 10, 2016
 *
 */
public interface SalesHeaderServiceCustom 
    extends TyraelJpaServiceCustom<SalesHeader, SalesHeaderInfo> {

    ImmutableMap<String, Path<?>> FIELD_MAPPING = ImmutableMap.<String, Path<?>>builder()
            .put("id", salesHeader.id)
            .put("deleted", salesHeader.deleted)
            .put("dateCreated", salesHeader.dateCreated)
            .put("itemCode", salesHeader.items.any().inventoryItem.code)
            .put("branchCode", salesHeader.items.any().inventoryItem.branch.code)
            .build();

    PageInfo<SalesHeaderInfo> rqlSearch(String term, Pageable pageRequest);
    int salesToday();

}
