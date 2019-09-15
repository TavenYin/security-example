package com.github.taven.security;

import com.github.taven.service.MyUserDetailsService;
import com.github.taven.service.SecurityService;
import groovy.json.JsonOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecurityService securityService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/home", "/403").permitAll()
                .anyRequest().authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(
                            O fsi) {
                        fsi.setSecurityMetadataSource(new MySecurityMetadataSource(securityService));
                        fsi.setAccessDecisionManager(new AffirmativeBased(getDecisionVoters()));
                        return fsi;
                    }
                })
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll()
                .and()
            .exceptionHandling()
                // 访问拒绝时处理
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                            Map<String, Object> result = new HashMap<>();
                            result.put("code", 403);
                            result.put("mes", "access denied, u don't have permission");
                            PrintWriter out = response.getWriter();
                            out.append(JsonOutput.toJson(result));
                        })
//                .authenticationEntryPoint((request, response, authException) -> response.sendRedirect(request.getContextPath() + "/403")) // 认证时访问拒绝处理
        ;
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
    }

    private List<AccessDecisionVoter<? extends Object>> getDecisionVoters() {
        return Arrays.asList(
                new WebExpressionVoter(),
//                new RoleVoter(),
//                new AuthenticatedVoter(),
                new MyAccessDecisionVoter(securityService)
        );
    }

}