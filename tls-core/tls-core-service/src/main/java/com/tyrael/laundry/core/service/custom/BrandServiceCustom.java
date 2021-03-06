package com.tyrael.laundry.core.service.custom;

import java.util.List;

import com.tyrael.laundry.commons.dto.BrandDto;
import com.tyrael.laundry.commons.service.TyraelJpaServiceCustom;
import com.tyrael.laundry.model.branch.Brand;
import com.tyrael.laundry.model.user.User;

/**
 * 
 * @author Mark Martinez, created Nov 29, 2015
 *
 */
public interface BrandServiceCustom extends TyraelJpaServiceCustom<Brand, BrandDto> {

    BrandDto findInfoByCode(String brandCode);
    List<Brand> findByUser(User user);
    List<Brand> findByUserUsername(String username);
    List<BrandDto> findInfoByUserCode(String userCode);

}
