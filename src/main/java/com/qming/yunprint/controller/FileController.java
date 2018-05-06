package com.qming.yunprint.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qming.yunprint.model.UserHolder;
import com.qming.yunprint.service.FileService;
import com.qming.yunprint.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @yunprint
 * @Description:
 * @Author: qming_c
 * @Date: 2018-03-08-22:06
 */
@RequestMapping("/file")
@Controller
public class FileController {
    @Autowired
    FileService fileService;
    @Autowired
    UserHolder userHolder;

    @ResponseBody
    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam(value = "file",required = true) MultipartFile file, HttpServletRequest request, HttpServletResponse response){
        //文件判空
        if (file == null){
            return JsonUtil.getStatusJson(JsonUtil.GLOBAL_FAIL);
        }
        String orderUUID = getOrderUuid(request.getCookies());
        HttpSession session = request.getSession();
        Object o = session.getAttribute("files");
        Map<String,String> files = (o == null ? null : (Map<String, String>) (o));
        if (files != null && files.size() >= 3) {
            return JsonUtil.getStatusJson(JsonUtil.GLOBAL_FAIL);
        }
        String fileUuid = fileService.upload(userHolder.get().getId(), file);
        if (fileUuid != null){
            if(files == null) {
                files = new HashMap<>();
            }
            files.put(fileUuid, file.getOriginalFilename());
            session.setAttribute("files", files);
            return JsonUtil.getStatusJson(JsonUtil.GLOBAL_SUCCESS);
        }else {
            return JsonUtil.getStatusJson(JsonUtil.GLOBAL_FAIL);
        }

    }

    @ResponseBody
    @RequestMapping(value = "/fileList", method = RequestMethod.GET)
    public String getFileList(HttpServletRequest request) {
        Map<String,String> fileNames = (Map<String, String>) request.getSession().getAttribute("files");
        JSONArray jsonArray = new JSONArray();
        if (fileNames == null){
            return jsonArray.toJSONString();
        }
        for (Map.Entry<String,String> file : fileNames.entrySet()){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("uuid", file.getKey());
            jsonObject.put("name", file.getValue());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/getFileCount", method = RequestMethod.GET)
    public String getFileNum(HttpServletRequest request) {
        JSONObject result = new JSONObject();
        Map<String,String> map = (Map<String, String>)request.getSession().getAttribute("files");
        result.put("count", map == null ? 0 : map.size());
        return result.toJSONString();
    }
    @ResponseBody
    @RequestMapping(value = "/deleteFile",method = RequestMethod.POST)
    public String deleteFile(@RequestBody String uuid, HttpServletRequest request){
        Map<String,String> map = (Map<String, String>)request.getSession().getAttribute("files");
        map.remove(uuid);
        return JsonUtil.getStatusJson(1);
    }
    private String getOrderUuid(Cookie[] cookies){
        for (Cookie cookie : cookies){
            if ("orderUuid".equals(cookie.getName())){
                return cookie.getValue();
            }
        }
        return null;
    }

}
