package com.tyrael.laundry.core.api.service.custom.impl;

import com.tyrael.laundry.commons.service.TyraelJpaServiceCustomImpl;
import com.tyrael.laundry.core.api.dto.BrandDto;
import com.tyrael.laundry.core.api.service.BrandService;
import com.tyrael.laundry.core.api.service.custom.BrandServiceCustom;
import com.tyrael.laundry.model.branch.Brand;

public class BrandServiceCustomImpl
    extends TyraelJpaServiceCustomImpl<Brand, BrandDto, BrandService>
    implements BrandServiceCustom {

}
