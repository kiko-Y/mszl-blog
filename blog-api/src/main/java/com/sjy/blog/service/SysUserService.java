package com.sjy.blog.service;

import com.sjy.blog.dao.pojo.SysUser;
import com.sjy.blog.vo.R;

/**
 * @author: Kiko
 * @date: 2023/2/3 15:35
 */
public interface SysUserService {
    SysUser findSysUserById(Long id);

    SysUser findUserByAccountAndPassword(String account, String password);


    R findUserByToken(String token);
}
