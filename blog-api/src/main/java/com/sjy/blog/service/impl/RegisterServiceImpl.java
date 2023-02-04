package com.sjy.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.sjy.blog.dao.pojo.SysUser;
import com.sjy.blog.service.RegisterService;
import com.sjy.blog.service.SysUserService;
import com.sjy.blog.utils.JWTUtils;
import com.sjy.blog.vo.ErrorCode;
import com.sjy.blog.vo.R;
import com.sjy.blog.vo.params.RegisterParam;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * @author: Kiko
 * @date: 2023/2/4 10:53
 */

@Service
@Transactional
public class RegisterServiceImpl implements RegisterService {

    private static final String SALT = "mszlu!@#";

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 注册用户成功则返回用户
     * @param registerParam
     * @return
     */
    @Override
    public R register(RegisterParam registerParam) {
        String account = registerParam.getAccount();
        String nickname = registerParam.getNickname();
        String password = registerParam.getPassword();
        // 参数不合法
        if(StringUtils.isBlank(account) ||
            StringUtils.isBlank(nickname) ||
            StringUtils.isBlank(password)) {
            return R.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }

        SysUser user = sysUserService.findUserByAccount(account);
        if(user != null) {
            return R.fail(ErrorCode.ACCOUNT_EXIST.getCode(), ErrorCode.ACCOUNT_EXIST.getMsg());
        }
        user = new SysUser();
        // 通过雪花算发生成
        user.setId(null);
        user.setAccount(account);
        user.setAdmin(1);
        user.setAvatar("/static/img/logo.b3a48c0.png");
        user.setCreateDate(System.currentTimeMillis());
        user.setDeleted(0);
        user.setEmail("");
        user.setLastLogin(System.currentTimeMillis());
        user.setMobilePhoneNumber("");
        user.setNickname(nickname);
        user.setPassword(DigestUtils.md5Hex(password + SALT));
        user.setSalt("mszlu!@#");
        user.setStatus("");
        // 保存user
        sysUserService.save(user);
        long expireTime = 10 * 60 * 1000L;
        String token = JWTUtils.createToken(user.getId(), expireTime);
        redisTemplate.opsForValue().set("TOKEN_" + token, JSON.toJSONString(user),
                expireTime, TimeUnit.MILLISECONDS);
        return R.success(token);
    }
}
