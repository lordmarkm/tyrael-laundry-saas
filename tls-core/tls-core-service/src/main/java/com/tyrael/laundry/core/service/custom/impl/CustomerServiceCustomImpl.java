package com.tyrael.laundry.core.service.custom.impl;

import static com.tyrael.laundry.model.customer.QCustomer.customer;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.mysema.query.types.expr.BooleanExpression;
import com.tyrael.laundry.commons.dto.PageInfo;
import com.tyrael.laundry.commons.dto.customer.CustomerInfo;
import com.tyrael.laundry.commons.service.TyraelJpaServiceCustomImpl;
import com.tyrael.laundry.commons.util.AuthenticationUtil;
import com.tyrael.laundry.core.service.BrandService;
import com.tyrael.laundry.core.service.CustomerService;
import com.tyrael.laundry.core.service.custom.CustomerServiceCustom;
import com.tyrael.laundry.model.branch.Brand;
import com.tyrael.laundry.model.customer.Customer;

/**
 * 
 * @author Mark Martinez, created Nov 29, 2015
 *
 */
@Transactional
public class CustomerServiceCustomImpl
    extends TyraelJpaServiceCustomImpl<Customer, CustomerInfo, CustomerService>
    implements CustomerServiceCustom {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerServiceCustomImpl.class);

    @Autowired
    private BrandService brandService;

    private BooleanExpression addBrandFilter(final BooleanExpression predicate) {
        if (AuthenticationUtil.isAuthorized(AuthenticationUtil.ROLE_ADMIN)) {
            return predicate;
        } else {
            List<Brand> brands = brandService.findByUserUsername(AuthenticationUtil.getLoggedInUsername());
            return predicate.and(customer.brand.in(brands));
        }
    }

    @Override
    public PageInfo<CustomerInfo> pageInfo(String term, Pageable page) {
        LOG.debug("Searching customers. term={}", term);

        BooleanExpression nameSearch = customer.name.surname.startsWithIgnoreCase(term)
            .or(customer.name.surname.startsWithIgnoreCase(term))
            .or(customer.name.middleName.startsWithIgnoreCase(term));

        nameSearch = addBrandFilter(nameSearch).and(customer.deleted.isFalse());

        return super.pageInfo(nameSearch, page);
    }

    @Override
    public CustomerInfo saveInfo(CustomerInfo dto) {
        if (null == dto.getCode()) {
            //CREATE operation, Generate random candidate brand codes until we find a unique one
            Customer existing = null;
            String candidateCode;
            do {
                candidateCode = RandomStringUtils.randomAlphanumeric(5).toLowerCase();
                existing = repo.findByCode(candidateCode);
            } while (null != existing);
    
            //Use the generated code as default customer code
            dto.setCode(candidateCode);

            Customer saved = saveInfoAndGetEntity(dto);

            //Assign the customer to the set brand
            Brand brand = brandService.findByCode(dto.getBrandCode());
            Preconditions.checkNotNull(brand);
            saved.setBrand(brand);

            return toDto(saved);
        } else {
            //UPDATE operation
            Customer existing = repo.findByCode(dto.getCode());
            Preconditions.checkNotNull(existing);
            mapper.map(dto, existing);

            //Assign the customer to the set brand
            Brand brand = brandService.findByCode(dto.getBrandCode());
            Preconditions.checkNotNull(brand);
            existing.setBrand(brand);

            return toDto(existing);
        }
    }

    @Override
    public CustomerInfo findInfoByCode(String customerCode) {
        return toDto(repo.findByCode(customerCode));
    }
}
