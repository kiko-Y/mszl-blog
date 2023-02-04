package com.sjy.blog.utils;

import com.sjy.blog.dao.pojo.SysUser;

/**
 * @author: Kiko
 * @date: 2023/2/4 14:44
 */
public class UserThreadLocal {

    private UserThreadLocal() {

    }

    private static final ThreadLocal<SysUser> LOCAL = new ThreadLocal<>();


    public static void put(SysUser sysUser) {
        LOCAL.set(sysUser);
    }

    public static SysUser get() {
        return LOCAL.get();
    }

    public static void remove() {
        LOCAL.remove();
    }
}
