package com.qming.yunprint;

import com.qming.yunprint.dao.FileDao;
import com.qming.yunprint.dao.OrderDao;
import com.qming.yunprint.dao.UserDao;
import com.qming.yunprint.model.FileEntity;
import com.qming.yunprint.model.Order;
import com.qming.yunprint.model.User;
import com.qming.yunprint.service.UserService;
import com.qming.yunprint.util.SHAUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YunprintApplicationTests {
	@Autowired
	FileDao fileDao;
	@Autowired
	UserDao userDao;
	@Autowired
	OrderDao orderDao;
	@Autowired
	UserService userService;

	@Test
	public void contextLoads() {

	}

}