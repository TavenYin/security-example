package com.github.taven.security;

import com.github.taven.service.SecurityService;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.FilterInvocation;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
        Object principal = authentication.getPrincipal();

        if ("anonymousUser".equals(principal)) {
            // 当前用户未登录，如果不要求权限->允许访问，否则拒绝访问
            return CollectionUtils.isEmpty(attributes) ? ACCESS_GRANTED : ACCESS_DENIED;

        } else {
            // 这里我的逻辑是，当前资源的要求权限，用户必须全部满足时才可以访问
            User user = (User) principal;
            List<String> permitList = securityService.getPermByUsername(user.getUsername());
            List<String> stringAttributes = attributes.stream().map(ConfigAttribute::getAttribute).collect(Collectors.toList());
            return permitList.containsAll(stringAttributes) ? ACCESS_GRANTED : ACCESS_DENIED;
        }
    }
}
