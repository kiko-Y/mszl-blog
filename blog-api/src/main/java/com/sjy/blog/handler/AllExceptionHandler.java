package com.sjy.blog.handler;

import com.sjy.blog.vo.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: Kiko
 * @date: 2023/2/3 16:34
 */
@ControllerAdvice
public class AllExceptionHandler {


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R doException(Exception ex) {
        ex.printStackTrace();
        return R.fail(-999, "系统异常");
    }
}
