package com.tyrael.laundry.core.api.service.custom.impl;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.tyrael.laundry.commons.service.TyraelJpaServiceCustomImpl;
import com.tyrael.laundry.core.api.dto.BrandDto;
import com.tyrael.laundry.core.api.service.BrandService;
import com.tyrael.laundry.core.api.service.custom.BrandServiceCustom;
import com.tyrael.laundry.model.branch.Brand;

@Transactional
public class BrandServiceCustomImpl
    extends TyraelJpaServiceCustomImpl<Brand, BrandDto, BrandService>
    implements BrandServiceCustom {

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

}
