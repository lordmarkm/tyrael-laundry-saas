package com.tyrael.laundry.commons.dto;

/**
 *
 * @author Mark Baldwin B. Martinez on Feb 29, 2016
 *
 */
public class DashboardDto {

    private int newJoborders;
    private int salesToday;
    private int accountsDue;

    public int getNewJoborders() {
        return newJoborders;
    }

    public void setNewJoborders(int newJoborders) {
        this.newJoborders = newJoborders;
    }

    public int getSalesToday() {
        return salesToday;
    }

    public void setSalesToday(int salesToday) {
        this.salesToday = salesToday;
    }

    public int getAccountsDue() {
        return accountsDue;
    }

    public void setAccountsDue(int accountsDue) {
        this.accountsDue = accountsDue;
    }

}
