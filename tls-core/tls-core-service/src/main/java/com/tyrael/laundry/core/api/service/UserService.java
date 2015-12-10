package com.tyrael.laundry.core.api.service;

import com.tyrael.laundry.commons.service.TyraelJpaService;
import com.tyrael.laundry.core.api.service.custom.UserServiceCustom;
import com.tyrael.laundry.model.user.User;

/**
 * 
 * @author Mark Martinez, created Nov 29, 2015
 *
 */
public interface UserService extends UserServiceCustom, TyraelJpaService<User> {

    User findByName(String username);
    User findByCode(String candidateCode);

}
