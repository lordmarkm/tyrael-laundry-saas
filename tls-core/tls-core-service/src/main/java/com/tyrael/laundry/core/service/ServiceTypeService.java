package com.tyrael.laundry.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tyrael.laundry.commons.service.TyraelJpaService;
import com.tyrael.laundry.core.service.custom.ServiceTypeServiceCustom;
import com.tyrael.laundry.model.joborder.ServiceType;

/**
 * @author mbmartinez
 */
public interface ServiceTypeService extends TyraelJpaService<ServiceType>, ServiceTypeServiceCustom {

    ServiceType findByCode(String code);
    Page<ServiceType> findByEnabled(boolean enabled, Pageable page);

}
