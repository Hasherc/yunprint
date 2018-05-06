package com.qming.yunprint;

import com.qming.yunprint.interceptor.FileListInterceptor;
import com.qming.yunprint.interceptor.LoginRequireInterceptor;
import com.qming.yunprint.interceptor.PassInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @yunprint
 * @Description:
 * @Author: qming_c
 * @Date: 2018-03-08-21:41
 */
@Component
public class YunPrintWebConfiguration implements WebMvcConfigurer {
    @Autowired
    PassInterceptor passInterceptor;
    @Autowired
    LoginRequireInterceptor loginRequireInterceptor;
    @Autowired
    FileListInterceptor fileListInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(passInterceptor);
        registry.addInterceptor(loginRequireInterceptor).addPathPatterns("/file/*","/order/*");
        registry.addInterceptor(fileListInterceptor).addPathPatterns("/*");

    }
}
