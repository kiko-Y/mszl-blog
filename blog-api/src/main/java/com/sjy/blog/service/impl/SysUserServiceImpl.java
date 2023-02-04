package com.sjy.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sjy.blog.dao.mapper.SysUserMapper;
import com.sjy.blog.dao.pojo.SysUser;
import com.sjy.blog.service.SysUserService;
import com.sjy.blog.service.TokenService;
import com.sjy.blog.utils.JWTUtils;
import com.sjy.blog.vo.ErrorCode;
import com.sjy.blog.vo.LoginUserVo;
import com.sjy.blog.vo.R;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author: Kiko
 * @date: 2023/2/3 15:35
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;


    @Autowired
    private TokenService tokenService;

    @Override
    public SysUser findSysUserById(Long id) {
        return sysUserMapper.selectById(id);
    }

    @Override
    public SysUser findUserByAccountAndPassword(String account, String password) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getAccount, account)
                .eq(SysUser::getPassword, password).last("limit 1");
        return sysUserMapper.selectOne(queryWrapper);
    }

    @Override
    public R findUserByToken(String token) {
        SysUser user = tokenService.checkToken(token);
        if(user == null) {
            return R.fail(ErrorCode.TOKEN_ERROR.getCode(), ErrorCode.TOKEN_ERROR.getMsg());
        }
        LoginUserVo loginUserVo = new LoginUserVo();
        BeanUtils.copyProperties(user, loginUserVo);
        return R.success(loginUserVo);
    }

    @Override
    public void save(SysUser user) {
        sysUserMapper.insert(user);
    }

    @Override
    public SysUser findUserByAccount(String account) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getAccount, account).last("limit 1");
        return sysUserMapper.selectOne(queryWrapper);
    }

}
