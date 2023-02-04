package com.sjy.blog.service;

import com.sjy.blog.vo.R;
import com.sjy.blog.vo.params.LoginParam;

/**
 * @author: Kiko
 * @date: 2023/2/3 23:54
 */

public interface LoginService {
    R login(LoginParam loginParam);
}
