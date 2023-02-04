package com.sjy.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.sjy.blog.dao.pojo.SysUser;
import com.sjy.blog.service.LoginService;
import com.sjy.blog.service.SysUserService;
import com.sjy.blog.utils.JWTUtils;
import com.sjy.blog.vo.ErrorCode;
import com.sjy.blog.vo.R;
import com.sjy.blog.vo.params.LoginParam;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author: Kiko
 * @date: 2023/2/3 23:56
 */
@Service
public class LoginServiceImpl implements LoginService {

    private static final String SALT = "mszlu!@#";

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 检查登录是否合法
     * 1. 检查参数是否合法
     * 2. 根据用户名和密码去sysUser表中查询
     * 3. 如果不存在 登录失败
     * 4. 如果存在，使用jwt生成token
     * 5. 把token放入redis中，映射到user，设置过期时间，减少数据库查询次数
     * @param loginParam
     * @return
     */
    @Override
    public R login(LoginParam loginParam) {
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        // 账号密码不合法
        if(StringUtils.isBlank(account) || StringUtils.isBlank(password)) {
            return R.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }

        // 使用加密盐保证一定的安全性
        // 关于为什么要加盐:
        // https://cloud.tencent.com/developer/article/1757935
        password = DigestUtils.md5Hex(password + SALT);

        SysUser user = sysUserService.findUserByAccountAndPassword(account, password);
        // 用户名或密码错误
        if(Objects.isNull(user)) {
            return R.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(), ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
        long expireTime = 2 * 60 * 60 * 1000L;
        String token = JWTUtils.createToken(user.getId(), expireTime);
        redisTemplate.opsForValue().set("TOKEN_" + token, JSON.toJSONString(user), expireTime, TimeUnit.MILLISECONDS);
        return R.success(token);
    }
}
