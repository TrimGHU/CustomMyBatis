package com.hugui.custommybatis.config;

import java.lang.reflect.Proxy;

/**
 * Copyright © 2018 hugui. All rights reserved.
 * 
 * @Title: MySqlSession.java
 * @Prject: custom-mybatis
 * @Package: com.hugui.custommybatis.config
 * @Description:
 * @author: HuGui
 * @date: 2019年1月21日 下午5:53:08
 * @version: V1.0
 */

public class MySqlSession {

	private Executor executor = new MyExecutor();

	private MyConfiguration config = new MyConfiguration();

	public <T> T selectOne(String statement, Object parameter) {
		return executor.query(statement, parameter);
	}

	@SuppressWarnings("unchecked")
	public <T> T getMapper(Class<T> clazz) {
		return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[] { clazz }, new MyMapperProxy(config, this));
	}
}
