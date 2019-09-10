package com.github.taven.service

import org.springframework.dao.IncorrectResultSizeDataAccessException
import org.springframework.lang.Nullable
import org.springframework.util.CollectionUtils

class Sql {
    static Object[] newParams(Object ... objects) {
        return objects
    }

    static <T> T singleResult(@Nullable Collection<T> results) throws IncorrectResultSizeDataAccessException {
        if (CollectionUtils.isEmpty(results)) {
            return null
        }
        if (results.size() > 1) {
            throw new IncorrectResultSizeDataAccessException(1, results.size())
        }
        return results.iterator().next()
    }

    public static String loadUserByUsername = '''
    select 
        id, username, `password`, user_type 
    from sys_user 
        where username = ?
    and is_lock = '0'
    and is_delete = '0'
'''

    public static String selectRolesByUserId = '''
    select
        r.id, role_code
    from sys_role r
    left join sys_user_role_ref ur on r.id = ur.role_id
    where r.is_delete = 0
    and ur.user_id = ?
'''

    public static String getPermByResource = '''
    SELECT permission_code FROM `sys_permission`
    where url = ?
'''

    public static String getPermByUsername = '''
    select 
        permission_code
    from sys_user u
    INNER JOIN sys_user_role_ref ur on u.id = ur.user_id
    INNER JOIN sys_role r on r.id = ur.role_id
    INNER JOIN sys_role_permission_ref rp on rp.role_id = r.id
    INNER JOIN sys_permission p on p.id = rp.permission_id
    where u.username = ?
'''
}