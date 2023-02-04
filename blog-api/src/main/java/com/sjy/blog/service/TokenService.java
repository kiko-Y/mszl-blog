package com.sjy.blog.service;

import com.sjy.blog.dao.pojo.SysUser;

/**
 * @author: Kiko
 * @date: 2023/2/4 10:45
 */
public interface TokenService {
    SysUser checkToken(String token);
}
