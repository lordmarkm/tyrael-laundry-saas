package com.tyrael.laundry.core.api.service.custom;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.tyrael.laundry.commons.service.TyraelJpaServiceCustom;
import com.tyrael.laundry.core.api.dto.UserDto;
import com.tyrael.laundry.model.user.User;

/**
 * 
 * @author Mark Martinez, created Nov 29, 2015
 *
 */
public interface UserServiceCustom extends TyraelJpaServiceCustom<User, UserDto>,
    UserDetailsService {

}
