package com.hugui.custommybatis.config;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Copyright © 2018 hugui. All rights reserved.
 * 
 * @Title: MyMapperProxy.java
 * @Prject: custom-mybatis
 * @Package: com.hugui.custommybatis.config
 * @Description:
 * @author: HuGui
 * @date: 2019年1月22日 上午11:00:37
 * @version: V1.0
 */

public class MyMapperProxy implements InvocationHandler {

	private MySqlSession sqlSession;
	private MyConfiguration config;

	public MyMapperProxy(MyConfiguration config, MySqlSession sqlSession) {
		this.config = config;
		this.sqlSession = sqlSession;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		MapperBean readerMapper = config.readerMapper("UserMapper.xml");

		if (!method.getDeclaringClass().getName().equals(readerMapper.getInterfaceName())) {
			return null;
		}

		List<MyFunction> list = readerMapper.getList();
		for (MyFunction myFunction : list) {
			if (method.getName().equals(myFunction.getFuncName())) {
				return sqlSession.selectOne(myFunction.getSql(), args[0]);
			}
		}

		return null;
	}

}
