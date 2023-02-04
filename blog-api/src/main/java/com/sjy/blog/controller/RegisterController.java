package com.sjy.blog.controller;

import com.sjy.blog.service.RegisterService;
import com.sjy.blog.vo.R;
import com.sjy.blog.vo.params.RegisterParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Kiko
 * @date: 2023/2/4 10:52
 */
@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping
    public R register(@RequestBody RegisterParam registerParam) {
        return registerService.register(registerParam);
    }
}
