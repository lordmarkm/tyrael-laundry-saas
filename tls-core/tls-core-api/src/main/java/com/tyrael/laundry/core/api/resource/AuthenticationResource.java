package com.tyrael.laundry.core.api.resource;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationResource {

    private static Logger LOG = LoggerFactory.getLogger(AuthenticationResource.class);

    @RequestMapping(method = GET)
    public ResponseEntity<Principal> getAuth(Principal principal) {
        LOG.debug("Authentication check. user={}", principal);
        return new ResponseEntity<>(principal, OK);
    }

}
