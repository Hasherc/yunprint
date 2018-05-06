package com.qming.yunprint.service;

import com.qming.yunprint.dao.FileDao;
import com.qming.yunprint.dao.OrderDao;
import com.qming.yunprint.dao.UserDao;
import com.qming.yunprint.model.FileEntity;
import com.qming.yunprint.model.FileVo;
import com.qming.yunprint.model.Order;
import com.qming.yunprint.model.OrderVo;
import com.qming.yunprint.util.JsonUtil;
import com.qming.yunprint.util.PricingUtil;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @yunprint
 * @Description:
 * @Author: qming_c
 * @Date: 2018-03-10-15:46
 */
@Service
public class OrderService  {
    @Autowired
    OrderDao orderDao;
    @Autowired
    UserDao userDao;
    @Autowired
    FileDao fileDao;
    private static final int UNPAID = 0;
    private static final int PAID = 1;
    private static final int UN_PRINT = 2;
    private static final int DONE = 3;
    // 黑白单面打印单价
    private static final double SINGLE_BLACK_PRINT_ONE_PAGE_PRICE = 0.1;
    // 彩色单面打印单价
    private static final double SINGLE_COLOR_PRINT_ONE_PAGE_PRICE = 1.0;
    //黑白双面打印单价
    private static final double DUPLEX_BLACK_PRINT_ONE_PAGE_PRICE = 0.2;

    public static int getUNPAID() {
        return UNPAID;
    }

    public static int getPAID() {
        return PAID;
    }

    public static int getUnPrint() {
        return UN_PRINT;
    }

    public static int getDONE() {
        return DONE;
    }



    public Integer addOrder(List<Order> orders){
        double cost = 0.0;
        for (Order order : orders){
            System.out.println(order);
            double fileCost = PricingUtil.calculatePrice(order.getPage(),order.isColorful(), order.isDuplex(), order.getCopies());
            cost += fileCost;
            order.setPrice(fileCost);
        }
        double balance = userDao.getBalance(orders.get(0).getOwnerId());
        if (balance < cost){
            return JsonUtil.GLOBAL_FAIL;
        }
        userDao.updateBalance(orders.get(0).getOwnerId(), balance - cost);
        for (Order order : orders){
            order.setStatus(PricingUtil.PAID);
            System.out.println(order);
            orderDao.insertOrder(order);
        }
        return JsonUtil.GLOBAL_SUCCESS;
    }
    public boolean isExist(String uuid){
        return orderDao.isExist(uuid) > 0;
    }
    public int getStatus(String uuid){
        return orderDao.selectStatus(uuid);
    }
    public List<Order> getOrder(String uuid){
        return orderDao.getOrdersByUuid(uuid);
    }
    public double getPrice(String uuid) {
        double price = orderDao.getPrice(uuid);
        return price;
    }
    public List<OrderVo> getOrders(int userId){
        Map<String, List<Order>> map = new HashMap<>();
        List<Order> orders = orderDao.getOrdersByUserId(userId);
        for (Order order : orders){
            if (map.containsKey(order.getUuid())){
                map.get(order.getUuid()).add(order);
            }else {
                List<Order> list = new ArrayList<>();
                list.add(order);
                map.put(order.getUuid(), list);
            }
        }
        List<OrderVo> orderVos = new ArrayList<>();
        for (List<Order> list : map.values()){
            OrderVo orderVo = new OrderVo();
            List<FileVo> fileVos = new ArrayList<>();
            for (Order order : list){
                FileVo fileVo = new FileVo();
                FileEntity fileEntity = fileDao.getFileByUuid(order.getFileUuid());
                fileVo.setColor(order.isColorful() ? "彩色" : "黑白");
                fileVo.setCopy(order.getCopies());
                fileVo.setDuplex(order.isDuplex() ? "双面" : "单面");
                fileVo.setName(fileEntity.getName());
                fileVo.setPage(order.getPage());
                fileVo.setPrice(order.getPrice());
                fileVo.setCover(fileEntity.getCoverUrl());
                fileVo.setDocumentId(fileEntity.getDocumentId());
                fileVos.add(fileVo);
                orderVo.setCount(list.size());
                orderVo.setAddress(order.getAddress());
                orderVo.setUuid(order.getUuid());
                orderVo.setPhone(order.getPhoneNum());
                orderVo.setPrice(order.getPrice() + orderVo.getPrice());
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                orderVo.setTime(format.format(order.getCreateTime()));
            }
            orderVo.setFiles(fileVos);
            orderVos.add(orderVo);
        }

        return orderVos;
    }
}
