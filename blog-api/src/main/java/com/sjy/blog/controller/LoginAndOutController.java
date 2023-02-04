package com.sjy.blog.controller;

import com.sjy.blog.service.LoginService;
import com.sjy.blog.service.LogoutService;
import com.sjy.blog.vo.R;
import com.sjy.blog.vo.params.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Kiko
 * @date: 2023/2/3 23:51
 */

@RestController
@RequestMapping
public class LoginAndOutController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private LogoutService logoutService;

    @PostMapping("/login")
    public R login(@RequestBody LoginParam loginParam) {
        return loginService.login(loginParam);
    }

    @GetMapping("/logout")
    public R logout(@RequestHeader("Authorization") String token) {
        return logoutService.logout(token);
    }
}
