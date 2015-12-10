package com.tyrael.laundry.core.api.dto;

/**
 * 
 * @author Mark Martinez, created Dec 10, 2015
 *
 */
public class CreateUserRequest {

    private BrandDto brand;
    private UserDto user;

    public BrandDto getBrand() {
        return brand;
    }
    public void setBrand(BrandDto brand) {
        this.brand = brand;
    }
    public UserDto getUser() {
        return user;
    }
    public void setUser(UserDto user) {
        this.user = user;
    }

}
