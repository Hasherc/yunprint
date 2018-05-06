package com.qming.yunprint.interceptor;

import com.qming.yunprint.dao.TicketDao;
import com.qming.yunprint.dao.UserDao;
import com.qming.yunprint.model.Ticket;
import com.qming.yunprint.model.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @yunprint
 * @Description:
 * @Author: qming_c
 * @Date: 2018-03-08-21:04
 */
@Component
public class PassInterceptor implements HandlerInterceptor {
    @Autowired
    UserHolder holder;
    @Autowired
    TicketDao ticketDao;
    @Autowired
    UserDao userDao;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ticket = null;
        if (request.getCookies() != null){
            for (Cookie cookie : request.getCookies()){
                if ("ticket".equals(cookie.getName())){
                    ticket = cookie.getValue();
                    break;
                }
            }
        }

        if (ticket != null){
            Ticket loginTicket = ticketDao.getTicket(ticket);
            if (loginTicket == null || loginTicket.getExpired().before(new Date()) || loginTicket.getStatus() == 1){
                return true;
            }
            holder.set(userDao.getUserById(loginTicket.getOwnerId()));
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null && holder.get() != null) {
            modelAndView.addObject("user", holder.get());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        holder.remove();
    }
}
