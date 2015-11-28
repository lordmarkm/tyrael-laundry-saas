package com.tyrael.laundry.model.user;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

import com.tyrael.laundry.commons.model.BaseNamedEntity;

/**
 * 
 * @author Mark Martinez, created Nov 29, 2015
 *
 */
@Entity(name = "user")
public class User extends BaseNamedEntity {

    @Column(name = "pw")
    private String password;

    @ElementCollection
    @CollectionTable(name = "user_roles", joinColumns = {
        @JoinColumn(name = "user_id")
    })
    private String[] roles;

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
