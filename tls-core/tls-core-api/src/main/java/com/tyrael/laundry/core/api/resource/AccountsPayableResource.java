package com.tyrael.laundry.core.api.resource;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tyrael.laundry.commons.dto.acctspayable.AccountsPayableInfo;
import com.tyrael.laundry.commons.resource.BaseResource;
import com.tyrael.laundry.core.service.AccountsPayableService;
import com.tyrael.laundry.model.acctspayable.AccountsPayable;

/**
 * 
 * @author Mark Martinez, created Feb 21, 2016
 *
 */
@RestController
@RequestMapping("/accounts-payable")
public class AccountsPayableResource  extends BaseResource<AccountsPayable, AccountsPayableInfo, AccountsPayableService> {

    @RequestMapping(method = GET, params = "apCode")
    public ResponseEntity<AccountsPayableInfo> findByCode(@RequestParam String apCode) {
        return new ResponseEntity<>(service.findInfoByCode(apCode), OK);
    }

}
