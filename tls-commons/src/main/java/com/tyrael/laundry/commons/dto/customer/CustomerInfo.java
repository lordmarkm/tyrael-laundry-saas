package com.tyrael.laundry.commons.dto.customer;

import java.math.BigDecimal;

import org.springframework.core.style.ToStringCreator;

import com.tyrael.laundry.commons.dto.BaseDto;
import com.tyrael.laundry.commons.util.SlugUtil;

/**
 * @author mbmartinez
 */
public class CustomerInfo extends BaseDto {

    private NameInfo name;
    private AddressInfo address;
    private ContactDetailsInfo contactDetails;
    private String code;
    private BigDecimal balance;

    /**
     * The code and name of the brand the customer is assigned to
     */
    private String brandCode;
    private String brandName;

    @Override
    protected ToStringCreator toStringCreator() {
        return super.toStringCreator()
            .append("name", name)
            .append("balance", balance)
            .append("address", address)
            .append("contact", contactDetails)
            .append("code", code)
            .append("brandCode", brandCode)
            .append("brandName", brandName);
    }

    public String getFormattedAddress() {
        if (null == address) {
            return "Address not specified";
        }

        StringBuilder sb = new StringBuilder();
        if (null != address.getAddressLine1()) {
            sb.append(address.getAddressLine1());
        }
        if (null != address.getAddressLine2()) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(address.getAddressLine2());
        }
        return sb.toString();
    }

    public String getFormattedName() {
        if (null == name) {
            return "Name not specified";
        }

        StringBuilder sb = new StringBuilder();
        if (null != name.getSurname()) {
            sb.append(name.getSurname());
        }
        if (null != name.getGivenName()) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(name.getGivenName());
        }
        if (null != name.getMiddleName()) {
            if (null == name.getGivenName() && sb.length() > 0) {
                sb.append(", ");
            } else if (sb.length() > 0){
                sb.append(" ");
            }
            sb.append(name.getMiddleName());
        }
        return sb.toString();
    }

    public String getSlug() {
        return SlugUtil.toSlug(getFormattedName());
    }

    public NameInfo getName() {
        return name;
    }

    public void setName(NameInfo name) {
        this.name = name;
    }

    public AddressInfo getAddress() {
        return address;
    }

    public void setAddress(AddressInfo address) {
        this.address = address;
    }

    public ContactDetailsInfo getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetailsInfo contactDetails) {
        this.contactDetails = contactDetails;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

}
