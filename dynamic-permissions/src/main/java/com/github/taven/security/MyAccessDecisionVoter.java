package com.github.taven.security;

import com.github.taven.service.SecurityService;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

public class MyAccessDecisionVoter implements AccessDecisionVoter<Object> {
    private SecurityService securityService;

    public MyAccessDecisionVoter(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
        String uri = ((FilterInvocation) object).getHttpRequest().getRequestURI();
        Object principal = authentication.getPrincipal();
        if ("anonymousUser".equals(principal) && !CollectionUtils.isEmpty(attributes)) {
            return ACCESS_DENIED;
        }

        return 0;
    }
}
