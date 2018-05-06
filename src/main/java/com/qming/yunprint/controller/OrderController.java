package com.qming.yunprint.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qming.yunprint.model.Order;
import com.qming.yunprint.model.UserHolder;
import com.qming.yunprint.service.FileService;
import com.qming.yunprint.service.OrderService;
import com.qming.yunprint.service.UserService;
import com.qming.yunprint.util.PricingUtil;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @yunprint
 * @Description:
 * @Author: qming_c
 * @Date: 2018-03-09-23:44
 */
@Controller
public class OrderController {
    @Autowired
    FileService fileService;
    @Autowired
    UserService userService;
    @Autowired
    UserHolder holder;
    @Autowired
    OrderService orderService;
    @RequestMapping(path = "/order/{uuid}", method = RequestMethod.GET)
    public String toOrderPage(@PathVariable String uuid, HttpServletRequest request, Model model){
        model.addAttribute("orderUuid", uuid);
        if (orderService.isExist(uuid)){
            return "redirect:/file/printSelect";
        }
        Map<String,String> map = (Map<String, String>)request.getSession().getAttribute("files");
        if (map == null){
            map = new HashMap<>();
            request.getSession().setAttribute("files", map);
        }
        model.addAttribute("files", fileService.getFileList(map.keySet()));

        return "/order";
    }
    @ResponseBody
    @RequestMapping(path = "/order/commit", method = RequestMethod.POST)
    public String commit(@RequestBody String ordersJson, HttpServletRequest request){
        List<Order> orders = JSONArray.parseArray(ordersJson, Order.class);
        for (Order order : orders ) {
            order.setOwnerId(holder.get().getId());
            order.setStatus(OrderService.getUNPAID());
            order.setCreateTime(new Date());
        }
        request.getSession().setAttribute("files", new HashMap<>());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", orderService.addOrder(orders));
        return jsonObject.toJSONString();
    }
    @RequestMapping(path = "/{orderUuid}/pay", method = RequestMethod.GET)
    public String pay(@PathParam("orderUuid")String uuid){
        int status = orderService.getStatus(uuid);
        if (status == 1){

        }
        return uuid;
    }
    @RequestMapping(path = "/document",method = RequestMethod.GET)
    public String readerDocument(@RequestParam("document_id") String documentId, Model model) {
        model.addAttribute("documentId", documentId);
        System.out.println(documentId);
        return "document";
    }
    @ResponseBody
    @RequestMapping(path = "/caculatePrice")
    public double getPrice(@RequestParam("duplex")boolean duplex, @RequestParam("color") boolean color, @RequestParam("page") Integer page, @RequestParam("copy")Integer copy) {
        return PricingUtil.calculatePrice(page, color, duplex, copy);
    }
    @RequestMapping("/toOrderSuccess")
    public String toSuccessOrderPage(Model model, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String uuid = null;
        for (Cookie cookie : cookies){
            if ("orderUuid".equals(cookie.getName())){
                uuid = cookie.getValue();
                cookie.setMaxAge(0);
                cookie.setPath("/");
            }
        }
        model.addAttribute("price", orderService.getPrice(uuid));
        return "commited_order";
    }
    @GetMapping(path = "/getOrderList")
    public String getOrderList(Model model){
        model.addAttribute("orders",orderService.getOrders(holder.get().getId()));
        return "own_orders";
    }
}
