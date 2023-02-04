package com.sjy.blog.service.impl;

import com.sjy.blog.service.RegisterService;
import com.sjy.blog.vo.R;
import com.sjy.blog.vo.params.RegisterParam;
import org.springframework.stereotype.Service;

/**
 * @author: Kiko
 * @date: 2023/2/4 10:53
 */

@Service
public class RegisterServiceImpl implements RegisterService {
    /**
     * 注册用户成功则返回用户
     * @param registerParam
     * @return
     */
    @Override
    public R register(RegisterParam registerParam) {
        return null;
    }
}
