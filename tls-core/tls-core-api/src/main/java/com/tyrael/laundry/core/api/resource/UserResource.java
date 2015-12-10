package com.tyrael.laundry.core.api.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tyrael.laundry.core.api.dto.CreateUserRequest;
import com.tyrael.laundry.core.api.dto.UserDto;
import com.tyrael.laundry.core.api.service.UserService;
import com.tyrael.laundry.model.user.User;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import static org.springframework.http.HttpStatus.*;

/**
 * 
 * @author Mark Martinez, created Nov 29, 2015
 *
 */
@RestController
@RequestMapping("/user")
public class UserResource extends BaseResource<User, UserDto, UserService> {

    @RequestMapping(method = GET, params = "userCode")
    public ResponseEntity<UserDto> findByCode(@RequestParam String userCode) {
        return new ResponseEntity<>(service.findInfoByCode(userCode), OK);
    }

    @RequestMapping(value = "/create", method = POST)
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequest createUserRequest) {
        return new ResponseEntity<>(service.createUser(createUserRequest), OK);
    }

}
