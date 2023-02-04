package com.sjy.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.sjy.blog.dao.pojo.SysUser;
import com.sjy.blog.service.TokenService;
import com.sjy.blog.utils.JWTUtils;
import com.sjy.blog.vo.ErrorCode;
import com.sjy.blog.vo.LoginUserVo;
import com.sjy.blog.vo.R;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author: Kiko
 * @date: 2023/2/4 10:45
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public SysUser checkToken(String token) {
        if(StringUtils.isBlank(token)) {
            return null;
        }
        Map<String, Object> map = JWTUtils.checkToken(token);
        // 已经超时或者token不合法
        if(map == null) {
            return null;
        }
        // 从缓存中找
        String jsonUser = redisTemplate.opsForValue().get("TOKEN_" + token);
        if(jsonUser == null) {
            return null;
        }
        SysUser user = JSON.parseObject(jsonUser, SysUser.class);
        return user;
    }
}
