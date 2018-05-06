package com.qming.yunprint.model;

import org.springframework.stereotype.Component;

/**
 * @yunprint
 * @Description:
 * @Author: qming_c
 * @Date: 2018-03-07-22:53
 */
@Component
public class UserHolder {
    private static ThreadLocal<User> userThreadLocal = new ThreadLocal<User>();
    public  void set(User user){
        userThreadLocal.set(user);
    }
    public  User get(){
        return userThreadLocal.get();
    }
    public  void remove(){
        userThreadLocal.remove();
    }
    public  boolean hasUser(){
        return userThreadLocal.get() != null;
    }

}
