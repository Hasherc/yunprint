package com.qming.yunprint.service;

import com.qming.yunprint.dao.UserDao;
import com.qming.yunprint.model.User;
import com.qming.yunprint.model.UserHolder;
import com.qming.yunprint.util.SHAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

/**
 * @yunprint
 * @Description:
 * @Author: qming_c
 * @Date: 2018-03-07-22:21
 */
@Service
public class UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    UserHolder holder;
    public User registerByPhoneNum(String nickName, String phoneNum, String password){
        nickName = HtmlUtils.htmlEscape(nickName);
        phoneNum = HtmlUtils.htmlEscape(phoneNum);
        password = SHAUtil.encryptSHA(HtmlUtils.htmlEscape(password));
        userDao.insertUser(getNewUser(nickName, phoneNum, password));
        return userDao.getUser(phoneNum,null,password);
    }

    public User loginByPhoneNum(String phoneNum, String password){
        return userDao.getUser(phoneNum,null, SHAUtil.encryptSHA(password));
    }
    public User loginByEmail(String email, String password){
        return userDao.getUser(null,email, SHAUtil.encryptSHA(password));
    }
    public boolean existUserByPhoneNum(String phoneNum){
        return userDao.existUser(phoneNum, null) > 0;
    }
    public boolean existUserByEmail(String email){
        return userDao.existUser(null, email) > 0;
    }
    private User getNewUser(String nickName, String phoneNum, String password){
        User user = new User();
        user.setNickName(nickName);
        user.setPhoneNum(phoneNum);
        user.setPassword(password);
        user.setBalance(0);
        user.setStatus(0);
        return user;
    }
    public double addBalance(int id, double balance){
        User user = userDao.getUserById(id);
        user.setBalance(user.getBalance() + balance);
        userDao.updateBalance(id, user.getBalance());
        return user.getBalance();
    }

}
