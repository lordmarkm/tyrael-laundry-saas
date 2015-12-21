package com.tyrael.laundry.commons.dto;

import java.util.Set;

import com.tyrael.laundry.commons.dto.BaseNamedDto;

/**
 * 
 * @author Mark Martinez, created Nov 29, 2015
 *
 */
public class UserDto extends BaseNamedDto {

    private String password;
    private String code;
    private Set<String> roles;

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Set<String> getRoles() {
        return roles;
    }
    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

}
