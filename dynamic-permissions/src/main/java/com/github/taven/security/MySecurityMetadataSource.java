package com.github.taven.security;

import com.github.taven.service.SecurityService;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import java.util.Collection;
import java.util.List;

public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    private SecurityService securityService;

    public MySecurityMetadataSource(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String uri = ((FilterInvocation) object).getHttpRequest().getRequestURI();
        List<String> list = securityService.getPermByResource(uri);
        if (list != null && list.size() != 0) {
            return SecurityConfig.createList(list.toArray(new String[0]));
        }

        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
