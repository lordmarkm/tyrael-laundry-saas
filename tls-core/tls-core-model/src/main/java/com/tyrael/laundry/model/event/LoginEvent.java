package com.tyrael.laundry.model.event;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "event_login")
@DiscriminatorValue("LOGIN")
public class LoginEvent extends TlsEvent {

}
