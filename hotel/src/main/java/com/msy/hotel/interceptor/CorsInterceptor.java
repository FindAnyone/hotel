package com.msy.hotel.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: wudagai
 * @date: 2021/11/1 10:31
 * @description: 自定义的一个拦截器
 */
public class CorsInterceptor implements HandlerInterceptor {
    /**
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     * 该方法在 controller 执行前执行，可以实现对数据的预处理。
     * 如果方法返回 true ，则继续调用下一个资源。否则不在继续调用。
     * 使用场景：登录权限校验，敏感字符的过滤，用户登录检验
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("指定contoller处理器方法之前");
        return true;
    }

    /**
     * 该方法在处理器执行后，生成视图前执行。这里有机会修改视图层数据。
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     * 在执行了处理器方法所有业务逻辑之后并且在return之前
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("执行了处理器方法并且是在执行return之前");
    }

    /**
     * 最后执行，通常用于记录日志，释放资源，处理异常。
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion---最后执行");
    }
}
