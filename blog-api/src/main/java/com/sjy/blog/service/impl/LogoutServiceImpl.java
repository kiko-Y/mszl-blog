package com.sjy.blog.service.impl;

import com.sjy.blog.service.LogoutService;
import com.sjy.blog.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author: Kiko
 * @date: 2023/2/4 2:00
 */
@Service
public class LogoutServiceImpl implements LogoutService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public R logout(String token) {
        redisTemplate.delete("TOKEN_" + token);
        return R.success(null);
    }
}
