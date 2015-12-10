package com.tyrael.laundry.model.user;

import java.util.Set;

import javax.persistence.AttributeOverride;
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
@AttributeOverride(name = "name", column = @Column(name = "name", nullable = false, unique = true))
public class User extends BaseNamedEntity {

    @Column(name = "pw", nullable = false)
    private String password;

    @ElementCollection
    @CollectionTable(name = "user_roles", joinColumns = {
        @JoinColumn(name = "user_id")
    })
    @Column(name = "role")
    private Set<String> roles;

    @Column(name = "user_code")
    private String code;

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
