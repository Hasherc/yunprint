package com.qming.yunprint.controller;

import com.alibaba.fastjson.JSONObject;
import com.qming.yunprint.model.User;
import com.qming.yunprint.service.OrderService;
import com.qming.yunprint.service.TicketService;
import com.qming.yunprint.service.UserService;
import com.qming.yunprint.util.JsonUtil;
import com.qming.yunprint.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @yunprint
 * @Description:
 * @Author: qming_c
 * @Date: 2018-03-08-13:00
 */
@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @Autowired
    TicketService ticketService;

    @Autowired
    OrderService orderService;

    @ResponseBody
    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String register(@RequestBody String json, HttpServletResponse response){
        JSONObject jsonObject = JSONObject.parseObject(json);

        String nickName = jsonObject.getString("nickName");
        String phoneNum = jsonObject.getString("phoneNum");
        String password = jsonObject.getString("password");

        if (userService.existUserByPhoneNum(phoneNum)){
            //用户已存在
            return JsonUtil.getStatusJson(JsonUtil.GLOBAL_FAIL);
        }
        User user = userService.registerByPhoneNum(nickName, phoneNum, password);

        return JsonUtil.getStatusJson(JsonUtil.GLOBAL_SUCCESS);
    }

    @ResponseBody
    @RequestMapping(path = "/loginByPhoneNum", method = RequestMethod.POST)
    public String login(@RequestBody String json, HttpServletResponse response){
        JSONObject jsonObject = JSONObject.parseObject(json);
        System.out.println(json);
        String phoneNum = jsonObject.getString("phoneNum");
        String password = jsonObject.getString("password");
        boolean remember = jsonObject.getBoolean("remember");

        User user = userService.loginByPhoneNum(phoneNum, password);
        if (user == null){
            return JsonUtil.getStatusJson(JsonUtil.GLOBAL_FAIL);
        }
        Cookie cookie = new Cookie("ticket", ticketService.newTicket(user.getId()));
        cookie.setPath("/");
        if (remember){
            cookie.setMaxAge(30 * 3600 * 24);
        }
        response.addCookie(cookie);

        return JsonUtil.getStatusJson(JsonUtil.GLOBAL_SUCCESS);
    }

    @RequestMapping(path = "/file/printSelect", method = RequestMethod.GET)
    public String printSelect(Model model,@CookieValue("orderUuid") String orderUuid, HttpServletResponse response, HttpServletRequest request) {
        if (orderService.isExist(orderUuid)){
            orderUuid = UUIDUtil.getUUID();
            Cookie orderCookie = new Cookie("orderUuid", orderUuid);
            orderCookie.setPath("/");
            response.addCookie(orderCookie);
            request.getSession().setAttribute("files", new HashMap<>());
        }
        model.addAttribute("orderUuid", orderUuid);
        return "print";
    }

    @RequestMapping(path = "/toLogin",method = RequestMethod.GET)
    public String toLogin() {
        return "login";
    }

    @RequestMapping(path = "/toRegister",method = RequestMethod.GET)
    public String toRegister() {
        return "register";
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String loginRegister(HttpSession session,Model model) {
        return "main";
    }

    @RequestMapping(path = "/logout")
    public String logout(@CookieValue("ticket") String ticket){

        if (ticket != null){
            ticketService.setTicketExpired(ticket);
        }
        return "main";
    }

}
