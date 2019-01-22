package com.hugui.custommybatis.Test;

import com.hugui.custommybatis.bean.User;
import com.hugui.custommybatis.config.MySqlSession;
import com.hugui.custommybatis.mapper.UserMapper;

/**
 * Copyright © 2018 hugui. All rights reserved.
 * 
 * @Title: MainTest.java
 * @Prject: custom-mybatis
 * @Package: com.hugui.custommybatis.Test
 * @Description: 
 * @author: HuGui
 * @date: 2019年1月22日 上午11:12:01
 * @version: V1.0
 */

public class MainTest {

	public static void main(String[] args) {
		MySqlSession sqlSession = new MySqlSession();
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		User userById = mapper.getUserById("1");
		System.out.println(userById);
	}
	
}
