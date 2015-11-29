package com.tyrael.laundry.core.api.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyrael.laundry.core.api.dto.UserDto;
import com.tyrael.laundry.core.api.service.UserService;
import com.tyrael.laundry.model.user.User;

/**
 * 
 * @author Mark Martinez, created Nov 29, 2015
 *
 */
@RestController
@RequestMapping("/user")
public class UserResource extends BaseResource<User, UserDto, UserService> {

}
