package com.tyrael.laundry.model.branch;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.tyrael.laundry.commons.model.BaseNamedEntity;
import com.tyrael.laundry.model.user.User;

/**
 * 
 * @author Mark Martinez, created Nov 28, 2015
 *
 */
@Entity(name = "brand")
public class Brand extends BaseNamedEntity {

    @Column(name = "brand_code", unique = true, nullable = false)
    private String code;

    @OneToMany(mappedBy = "brand")
    private List<Branch> branches;

    @ManyToMany
    @JoinTable(name = "brand_users")
    private List<User> users;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
