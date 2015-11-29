package com.tyrael.laundry.core.api.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyrael.laundry.core.api.dto.BrandDto;
import com.tyrael.laundry.core.api.service.BrandService;
import com.tyrael.laundry.model.branch.Brand;

/**
 * 
 * @author Mark Martinez, created Nov 29, 2015
 *
 */
@RestController
@RequestMapping("/brand")
public class BrandResource extends BaseResource<Brand, BrandDto, BrandService> {

}
