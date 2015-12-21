package com.tyrael.laundry.core.service.custom.impl;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.mysema.query.types.Predicate;
import com.tyrael.laundry.commons.dto.BrandDto;
import com.tyrael.laundry.commons.dto.PageInfo;
import com.tyrael.laundry.commons.service.TyraelJpaServiceCustomImpl;
import com.tyrael.laundry.commons.util.AuthenticationUtil;
import com.tyrael.laundry.core.service.BrandService;
import com.tyrael.laundry.core.service.UserService;
import com.tyrael.laundry.core.service.custom.BrandServiceCustom;
import com.tyrael.laundry.model.branch.Brand;
import com.tyrael.laundry.model.branch.QBrand;
import com.tyrael.laundry.model.user.User;

@Transactional
public class BrandServiceCustomImpl
    extends TyraelJpaServiceCustomImpl<Brand, BrandDto, BrandService>
    implements BrandServiceCustom {

    @Autowired
    private UserService userService;

    @Override
    public PageInfo<BrandDto> pageInfo(Pageable page) {
        if (AuthenticationUtil.isAuthorized(AuthenticationUtil.ROLE_ADMIN)) {
            return super.pageInfo(page);
        } else {
            User user = userService.findByName(AuthenticationUtil.getLoggedInUsername());
            Preconditions.checkNotNull(user);
            Predicate predicate = QBrand.brand.users.contains(user);
            Page<Brand> brands = repo.findAll(predicate, page);
            return toPageInfo(brands);
        }
    }

    @Override
    public BrandDto saveInfo(BrandDto dto) {
        if (null == dto.getCode()) {
            //CREATE operation, Generate random candidate brand codes until we find a unique one
            Brand existing = null;
            String candidateCode;
            do {
                candidateCode = RandomStringUtils.randomAlphanumeric(5).toLowerCase();
                existing = repo.findByCode(candidateCode);
            } while (null != existing);
    
            //Use the generated code as default brand code
            dto.setCode(candidateCode);
            return super.saveInfo(dto);
        } else {
            //UPDATE operation
            Brand existing = repo.findByCode(dto.getCode());
            Preconditions.checkNotNull(existing);
            mapper.map(dto, existing);
            return toDto(existing);
        }
    }

    @Override
    public BrandDto findInfoByCode(String brandCode) {
        return toDto(repo.findByCode(brandCode));
    }

    @Override
    public List<BrandDto> findInfoByUserCode(String userCode) {
        User user = userService.findByCode(userCode);
        Preconditions.checkNotNull(user);
        Predicate predicate = QBrand.brand.users.contains(user);
        List<Brand> brands = (List<Brand>) repo.findAll(predicate);
        return toDto(brands);
    }

    @Override
    public List<Brand> findByUser(User user) {
        Preconditions.checkNotNull(user);
        Predicate predicate = QBrand.brand.users.contains(user);
        List<Brand> brands = (List<Brand>) repo.findAll(predicate);
        return brands;
    }

}
