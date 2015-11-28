package com.tyrael.laundry.core.api.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author Mark Martinez, created Nov 29, 2015
 *
 */
@RestController
@RequestMapping("/user")
public class UserResource extends BaseResource<UserDto> {

}
