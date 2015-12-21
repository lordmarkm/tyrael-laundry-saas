package com.tyrael.laundry.commons.dto.customer;

import org.springframework.core.style.ToStringCreator;

/**
 * @author mbmartinez
 */
public class AddressInfo {

    private String addressLine1;
    private String addressLine2;
    private String city;
    private String province;
    private String zip;

    public String toString() {
        return new ToStringCreator(this)
            .append("line1", addressLine1)
            .append("line2", addressLine2)
            .append("city", city)
            .append("prov", province)
            .append("zip", zip)
            .toString();
    }
    public String getFormattedAddress() {
        StringBuilder sb = new StringBuilder();
        if (null != addressLine1) {
            sb.append(addressLine1);
        }
        if (null != addressLine2) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(addressLine2);
        }
        return sb.toString();
    }
    public String getAddressLine1() {
        return addressLine1;
    }
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }
    public String getAddressLine2() {
        return addressLine2;
    }
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getZip() {
        return zip;
    }
    public void setZip(String zip) {
        this.zip = zip;
    }

}
