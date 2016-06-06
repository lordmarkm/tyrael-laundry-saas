package com.tyrael.laundry.commons.util;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 
 * @author Mark Martinez, create Dec 9, 2015
 *
 */
public class AuthenticationUtil {

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_BRAND_MANAGER = "ROLE_BRAND_MANAGER";
    public static final String ROLE_POS = "ROLE_POS";

    public static boolean isAuthenticated() {
        return null != SecurityContextHolder.getContext().getAuthentication()
                && !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken);
    }

    public static String getLoggedInUsername() {
        if (null == SecurityContextHolder.getContext().getAuthentication()) {
            return "SYSTEM";
        } else {
            return SecurityContextHolder.getContext().getAuthentication().getName();
        }
    }

    public static boolean isAuthorized(String role) {
        for(GrantedAuthority auth : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
            if (auth.getAuthority().equals(role)) {
                return true;
            }
        }
        return false;
    }
}
