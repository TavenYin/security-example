package com.github.taven.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 加载资源要求的权限
     *
     * @param resource
     * @return
     */
    public List<String> getPermByResource(String resource) {
        return jdbcTemplate.queryForList(Sql.getPermByResource, String.class, resource);
    }

    /**
     * 当前用户的权限
     *
     * @param username
     * @return
     */
    public List<String> getPermByUsername(String username) {
        return jdbcTemplate.queryForList(Sql.getPermByUsername, String.class, username);
    }

}
