package com.qming.yunprint.util;

import com.alibaba.fastjson.JSONObject;

/**
 * @yunprint
 * @Description:
 * @Author: qming_c
 * @Date: 2018-03-07-22:35
 */
public class JsonUtil {
    public static final int GLOBAL_SUCCESS = 1;
    public static final int GLOBAL_FAIL = 0;
    public static String getStatusJson(int status){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status",status);
        return jsonObject.toString();
    }

    public static void main(String[] args) {
        JsonUtil a = new JsonUtil();
        int b;
        Integer c;

    }
}
