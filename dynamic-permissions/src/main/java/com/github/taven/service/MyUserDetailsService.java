package com.github.taven.service;

import com.github.taven.model.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsService implements UserDetailsService {
    private static final String ROLE_PREFIX = "ROLE_";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDO user = null;
        try {
            user = jdbcTemplate.queryForObject(Sql.loadUserByUsername, Sql.newParams(username), new BeanPropertyRowMapper<>(UserDO.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        if (user == null)
            throw new UsernameNotFoundException("用户不存在：" + username);

        return User.builder()
                .username(username)
                .password(user.getPassword())
                // 这里要求权限不为null，随便写了一个，可以在这里将用户的角色权限全查出来
                .authorities(ROLE_PREFIX+"USER")
                .build();
    }

}
