package com.tyrael.laundry.core.service;

import java.util.List;

import com.tyrael.laundry.commons.service.TyraelJpaService;
import com.tyrael.laundry.core.service.custom.ServiceTypeServiceCustom;
import com.tyrael.laundry.model.joborder.ServiceType;

/**
 * @author mbmartinez
 */
public interface ServiceTypeService extends TyraelJpaService<ServiceType>, ServiceTypeServiceCustom {

    ServiceType findByCode(String code);
    List<ServiceType> findByEnabled(boolean enabled);

}
