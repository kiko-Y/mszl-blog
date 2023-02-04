package com.sjy.blog.handler;

import com.alibaba.fastjson.JSON;
import com.sjy.blog.dao.pojo.SysUser;
import com.sjy.blog.service.TokenService;
import com.sjy.blog.utils.UserThreadLocal;
import com.sjy.blog.vo.ErrorCode;
import com.sjy.blog.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 * @author: Kiko
 * @date: 2023/2/4 11:59
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;

    /**
     * 1. 判断请求路径是否为controller方法
     * 2. 判断token是否为空，未登录
     * 3. 如果token不为空，登录验证
     * 4. 认证成功放行即可
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!(handler instanceof HandlerMethod)) {
            // handler 可能是 RequestResourceHandler 访问资源
            return true;
        }

        String token = request.getHeader("Authorization");
        log.info("=================request start===========================");
        String requestURI = request.getRequestURI();
        log.info("request uri:{}",requestURI);
        log.info("request method:{}",request.getMethod());
        log.info("token:{}", token);
        log.info("=================request end===========================");
        if(StringUtils.isBlank(token)) {
            R r = R.fail(ErrorCode.NO_LOGIN.getCode(), ErrorCode.NO_LOGIN.getMsg());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(r));
            return false;
        }
        SysUser user = tokenService.checkToken(token);
        if(user == null) {
            R r = R.fail(ErrorCode.NO_LOGIN.getCode(), ErrorCode.NO_LOGIN.getMsg());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(r));
            return false;
        }
        UserThreadLocal.put(user);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 如果不删除ThreadLocal用完的信息 会有内存泄漏的风险
        UserThreadLocal.remove();
    }
}
