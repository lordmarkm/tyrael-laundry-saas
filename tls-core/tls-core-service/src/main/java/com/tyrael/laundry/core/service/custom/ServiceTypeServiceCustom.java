package com.tyrael.laundry.core.service.custom;

import org.springframework.data.domain.Pageable;

import com.tyrael.laundry.commons.dto.PageInfo;
import com.tyrael.laundry.commons.dto.joborder.ServiceTypeInfo;
import com.tyrael.laundry.commons.service.TyraelJpaServiceCustom;
import com.tyrael.laundry.model.joborder.ServiceType;

/**
 * @author mbmartinez
 */
public interface ServiceTypeServiceCustom extends TyraelJpaServiceCustom<ServiceType, ServiceTypeInfo> {

    PageInfo<ServiceTypeInfo> findInfoByEnabled(Pageable page);

}
