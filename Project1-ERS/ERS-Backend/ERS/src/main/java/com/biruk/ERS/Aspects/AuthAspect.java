package com.biruk.ERS.Aspects;

import jakarta.servlet.http.HttpSession;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class AuthAspect {

    @Before("within(com.biruk.ERS.Controllers.*)" + "&& !within(com.biruk.ERS.Controllers.AuthController)")
    public void checkLoggedIn () {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attributes.getRequest().getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            throw new IllegalArgumentException("User must be logged in to do this!");
        }
    }
}
