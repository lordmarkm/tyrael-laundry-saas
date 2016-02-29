package com.tyrael.laundry.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.tyrael.laundry.core.service.EventService;
import com.tyrael.laundry.model.event.LoginEvent;

/**
 * 
 * @author Mark Martinez, create Dec 8, 2015
 *
 */
@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private EventService eventService;

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication auth) throws IOException, ServletException {

        eventService.save(new LoginEvent());

        response.getWriter().print("{\"responseCode\":\"SUCCESS\"}");
        response.getWriter().flush();
    }

}
