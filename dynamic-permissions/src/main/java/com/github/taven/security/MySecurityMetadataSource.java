package com.github.taven.security;

import com.github.taven.service.SecurityService;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import java.util.Collection;

public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    private SecurityService securityService;

    public MySecurityMetadataSource(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
