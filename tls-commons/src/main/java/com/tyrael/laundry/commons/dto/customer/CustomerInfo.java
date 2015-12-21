package com.tyrael.laundry.commons.dto.customer;

import org.springframework.core.style.ToStringCreator;

import com.tyrael.laundry.commons.dto.BaseDto;

/**
 * @author mbmartinez
 */
public class CustomerInfo extends BaseDto {

    private NameInfo name;
    private AddressInfo address;
    private ContactDetailsInfo contactDetails;

    @Override
    protected ToStringCreator toStringCreator() {
        return super.toStringCreator()
            .append("name", name)
            .append("address", address)
            .append("contact", contactDetails);
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

}
