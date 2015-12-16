package com.tyrael.laundry.model.customer;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Name {

    @Column(name = "given_name")
    private String givenName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "surname")
    private String surname;

    public String toString() {
        StringBuilder string = new StringBuilder();
        if (null != surname) {
            string.append(surname).append(", ");
        }
        if (null != givenName) {
            string.append(givenName).append(" ");
        }
        if (null != middleName) {
            string.append(middleName);
        }
        return string.toString();
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
