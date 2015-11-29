package com.tyrael.laundry.core.api.service.custom.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.google.common.collect.Lists;
import com.tyrael.laundry.commons.service.TyraelJpaServiceCustomImpl;
import com.tyrael.laundry.core.api.dto.UserDto;
import com.tyrael.laundry.core.api.service.UserService;
import com.tyrael.laundry.core.api.service.custom.UserServiceCustom;
import com.tyrael.laundry.model.user.User;

/**
 * 
 * @author Mark Martinez, created Nov 29, 2015
 *
 */
public class UserServiceCustomImpl 
    extends TyraelJpaServiceCustomImpl<User, UserDto, UserService>
    implements UserServiceCustom {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceCustomImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOG.info("Authenticating user. username={}", username);

        User user = repo.findByName(username);
        if (null == user) {
            throw new UsernameNotFoundException("User " + username + " not found.");
        }

        List<SimpleGrantedAuthority> authorities = Lists.newArrayList();
        for(String authString : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(authString));
        }

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), authorities);

        return userDetails;
    }

}
