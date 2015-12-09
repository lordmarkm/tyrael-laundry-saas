package com.tyrael.laundry.commons.util;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 
 * @author Mark Martinez, create Dec 9, 2015
 *
 */
public class AuthenticationUtil {

    public static String getLoggedInUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
