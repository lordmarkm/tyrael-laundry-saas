package com.tyrael.laundry.core.api.dto;

import java.util.List;

/**
 * 
 * @author Mark Martinez, created Dec 10, 2015
 *
 */
public class CreateUserRequest {

    private UserDto user;
    private List<String> brandCodes;
    private boolean resetPassword;

    public UserDto getUser() {
        return user;
    }
    public void setUser(UserDto user) {
        this.user = user;
    }
    public List<String> getBrandCodes() {
        return brandCodes;
    }
    public void setBrandCodes(List<String> brandCodes) {
        this.brandCodes = brandCodes;
    }
    public boolean isResetPassword() {
        return resetPassword;
    }
    public void setResetPassword(boolean resetPassword) {
        this.resetPassword = resetPassword;
    }

}
