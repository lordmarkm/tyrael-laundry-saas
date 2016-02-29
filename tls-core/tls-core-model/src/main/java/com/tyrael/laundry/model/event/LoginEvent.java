package com.tyrael.laundry.model.event;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "login_event")
@DiscriminatorValue("LOGIN")
public class LoginEvent extends TlsEvent {

}
