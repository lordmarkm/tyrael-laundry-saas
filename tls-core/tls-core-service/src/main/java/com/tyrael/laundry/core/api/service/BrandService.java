package com.tyrael.laundry.core.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tyrael.laundry.commons.service.TyraelJpaService;
import com.tyrael.laundry.core.api.service.custom.BrandServiceCustom;
import com.tyrael.laundry.model.branch.Brand;
import com.tyrael.laundry.model.user.User;

/**
 * 
 * @author Mark Martinez, created Nov 29, 2015
 *
 */
public interface BrandService extends BrandServiceCustom, TyraelJpaService<Brand> {

    Brand findByCode(String candidateCode);
    Page<Brand> findByUsersContains(User user, Pageable page);

}
