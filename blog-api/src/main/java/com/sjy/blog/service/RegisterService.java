package com.sjy.blog.service;

import com.sjy.blog.vo.R;
import com.sjy.blog.vo.params.RegisterParam;

/**
 * @author: Kiko
 * @date: 2023/2/4 10:53
 */
public interface RegisterService {
    R register(RegisterParam registerParam);
}
