package com.sjy.blog.vo;

import lombok.Data;

/**
 * @author: Kiko
 * @date: 2023/2/4 1:07
 */
@Data
public class LoginUserVo {
    
    private Long id;

    private String account;

    private String nickname;

    private String avatar;
}
