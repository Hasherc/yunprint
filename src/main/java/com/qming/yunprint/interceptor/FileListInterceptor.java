package com.qming.yunprint.interceptor;

import com.qming.yunprint.service.OrderService;
import com.qming.yunprint.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @yunprint
 * @Description:
 * @Author: qming_c
 * @Date: 2018-03-09-14:08
 */
@Component
public class FileListInterceptor implements HandlerInterceptor {
    @Autowired
    OrderService orderService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String orderUuid = null;
        Cookie[] cookies = request.getCookies();

        for (Cookie cookie : cookies != null ? cookies : new Cookie[0]){
            if ("orderUuid".equals(cookie.getName())){
                orderUuid = cookie.getValue();
            }
        }

        if (orderUuid == null ) {
            orderUuid = UUIDUtil.getUUID();
            Cookie orderCookie = new Cookie("orderUuid", orderUuid);
            orderCookie.setPath("/");
            response.addCookie(orderCookie);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

