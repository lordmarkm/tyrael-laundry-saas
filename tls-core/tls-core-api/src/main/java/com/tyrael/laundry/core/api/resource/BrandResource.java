package com.tyrael.laundry.core.api.resource;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tyrael.laundry.commons.dto.BrandDto;
import com.tyrael.laundry.commons.resource.BaseResource;
import com.tyrael.laundry.core.service.BrandService;
import com.tyrael.laundry.model.branch.Brand;

/**
 * 
 * @author Mark Martinez, created Nov 29, 2015
 *
 */
@RestController
@RequestMapping("/brand")
public class BrandResource extends BaseResource<Brand, BrandDto, BrandService> {

    @RequestMapping(method = GET, params = "brandCode")
    public ResponseEntity<BrandDto> findByCode(@RequestParam String brandCode) {
        return new ResponseEntity<>(service.findInfoByCode(brandCode), OK);
    }

    @RequestMapping(method = GET, params = "userCode")
    public ResponseEntity<List<BrandDto>> findByUserCode(@RequestParam String userCode) {
        return new ResponseEntity<>(service.findInfoByUserCode(userCode), OK);
    }
}
