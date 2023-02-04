package com.sjy.blog.service;

import com.sjy.blog.vo.R;

/**
 * @author: Kiko
 * @date: 2023/2/4 1:59
 */
public interface LogoutService {
    R logout(String token);
}
