package com.sjy.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: Kiko
 * @date: 2023/2/3 14:35
 */
@Data
@AllArgsConstructor
public class R {
    private boolean success;
    private int code;
    private String msg;
    private Object data;

    public static R success(Object data) {
        return new R(true, 200, "success", data);
    }

    public static R fail(int code, String msg) {
        return new R(false, code, msg, null);
    }
}
