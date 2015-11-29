package com.tyrael.laundry.core.api.service;

import com.tyrael.laundry.commons.service.TyraelJpaService;
import com.tyrael.laundry.core.api.service.custom.BrandServiceCustom;
import com.tyrael.laundry.model.branch.Brand;

/**
 * 
 * @author Mark Martinez, created Nov 29, 2015
 *
 */
public interface BrandService extends BrandServiceCustom, TyraelJpaService<Brand> {

}
