package com.tyrael.laundry.core.service.custom.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mysema.query.types.Predicate;
import com.tyrael.laundry.commons.dto.PageInfo;
import com.tyrael.laundry.commons.dto.UserDto;
import com.tyrael.laundry.commons.service.TyraelJpaServiceCustomImpl;
import com.tyrael.laundry.commons.util.AuthenticationUtil;
import com.tyrael.laundry.core.service.BrandService;
import com.tyrael.laundry.core.service.UserService;
import com.tyrael.laundry.core.service.custom.UserServiceCustom;
import com.tyrael.laundry.core.service.dto.CreateUserRequest;
import com.tyrael.laundry.model.branch.Brand;
import com.tyrael.laundry.model.user.QUser;
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
    public UserDto updateUser(CreateUserRequest createUserRequest) {
        //Make sure brands exists first
        List<Brand> brands = Lists.newArrayList();
        for (String brandCode : createUserRequest.getBrandCodes()) {
            brands.add(brandService.findByCode(brandCode));
        }
        Preconditions.checkArgument(!brands.isEmpty(), "User must be assigned to at least 1 brand");

        //Save the user
        UserDto userDto = createUserRequest.getUser();
        User user = null;
        if (StringUtils.isEmpty(userDto.getCode())) {
            //New user (create operation)
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user = repo.saveInfoAndGetEntity(createUserRequest.getUser());

            //Give user a code
            if (StringUtils.isEmpty(user.getCode())) {
                User existing;
                String candidateCode;
                do {
                    candidateCode = RandomStringUtils.randomAlphanumeric(5).toLowerCase();
                    existing = repo.findByCode(candidateCode);
                } while (null != existing);
                user.setCode(candidateCode);
            }
        } else {
            //Existing user (update operation)
            user = repo.findByCode(userDto.getCode());
            user.setName(userDto.getName());
            user.setRoles(Sets.newHashSet(userDto.getRoles()));
            if (createUserRequest.isResetPassword()) {
                user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            }
        }

        //Remove the user from any existing brands he already has first
        for (Brand brand : brandService.findByUser(user)) {
            brand.getUsers().remove(user);
        }

        //Add the user to the brands' users
        for (Brand brand : brands) {
            brand.getUsers().add(user);
        }

        return toDto(user);
    }

    @Override
    public UserDto findInfoByCode(String userCode) {
        return toDto(repo.findByCode(userCode));
    }

    @Override
    public PageInfo<UserDto> pageInfo(Pageable page) {
        if (AuthenticationUtil.isAuthorized(AuthenticationUtil.ROLE_ADMIN)) {
            return super.pageInfo(page);
        } else {
            User user = repo.findByName(AuthenticationUtil.getLoggedInUsername());
            List<Brand> brands = brandService.findByUser(user);

            //This is a truly, truly awful implementation
            List<User> users = Lists.newArrayList();
            for (Brand brand : brands) {
                users.addAll(brand.getUsers());
            }
            Predicate filter = QUser.user.in(users);
            Page<User> results = repo.findAll(filter, page);
            return toPageInfo(results);
        }
    }
}
