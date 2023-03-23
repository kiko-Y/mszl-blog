package com.sjy.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sjy.blog.dao.mapper.CommentMapper;
import com.sjy.blog.dao.mapper.SysUserMapper;
import com.sjy.blog.dao.pojo.SysUser;
import com.sjy.blog.utils.UserThreadLocal;
import com.sjy.blog.vo.R;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tests")
//@ConfigurationProperties(prefix = "qiniu")
@Data
public class TestController {

//    private String accessKey;
//
//    private String secretKey;

    @Autowired
    SysUserMapper sysUserMapper;

    @GetMapping
    public R test(){
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getId, 10086L);
        List<SysUser> sysUsers = sysUserMapper.selectList(queryWrapper);
        System.out.println(sysUsers);
        return R.success(1);
    }
}
