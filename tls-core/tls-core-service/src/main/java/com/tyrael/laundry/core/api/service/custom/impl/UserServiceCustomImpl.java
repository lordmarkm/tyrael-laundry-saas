package com.tyrael.laundry.core.api.service.custom.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.tyrael.laundry.commons.service.TyraelJpaServiceCustomImpl;
import com.tyrael.laundry.core.api.dto.CreateUserRequest;
import com.tyrael.laundry.core.api.dto.UserDto;
import com.tyrael.laundry.core.api.service.BrandService;
import com.tyrael.laundry.core.api.service.UserService;
import com.tyrael.laundry.core.api.service.custom.UserServiceCustom;
import com.tyrael.laundry.model.branch.Brand;
import com.tyrael.laundry.model.user.User;

/**
 * 
 * @author Mark Martinez, created Nov 29, 2015
 *
 */
@Transactional
public class UserServiceCustomImpl 
    extends TyraelJpaServiceCustomImpl<User, UserDto, UserService>
    implements UserServiceCustom {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceCustomImpl.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BrandService brandService;

    @PostConstruct
    public void createDefaultAdmin() {
        if (repo.count() == 0) {
            User user = new User();
            user.setCreatedBy("SYSTEM");
            user.setDescription("Default user");
            user.setName("markm");
            user.setPassword(passwordEncoder.encode("123qwe"));
            user.setRoles(Sets.newHashSet("ROLE_ADMIN"));
            user.setUpdatedBy("SYSTEM");
            repo.save(user);
        }
    }

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

    @Override
    public UserDto createUser(CreateUserRequest createUserRequest) {
        //Make sure brand exists first
        Brand brand = brandService.findByCode(createUserRequest.getBrand().getCode());
        Preconditions.checkNotNull(brand);

        //Save the user
        UserDto userDto = createUserRequest.getUser();
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = repo.saveInfoAndGetEntity(createUserRequest.getUser());

        //Give user a code if necessary
        if (StringUtils.isEmpty(user.getCode())) {
            User existing;
            String candidateCode;
            do {
                candidateCode = RandomStringUtils.randomAlphanumeric(5).toLowerCase();
                existing = repo.findByCode(candidateCode);
            } while (null != existing);
            user.setCode(candidateCode);
        }

        //Add the user to the brand users
        brand.getUsers().add(user);

        return toDto(user);
    }

    @Override
    public UserDto findInfoByCode(String userCode) {
        return toDto(repo.findByCode(userCode));
    }

}
