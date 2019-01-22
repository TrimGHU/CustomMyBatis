package com.hugui.custommybatis.config;

/**
 * Copyright © 2018 hugui. All rights reserved.
 * 
 * @Title: Excutor.java
 * @Prject: custom-mybatis
 * @Package: com.hugui.custommybatis.config
 * @Description:
 * @author: HuGui
 * @date: 2019年1月21日 下午5:55:06
 * @version: V1.0
 */

public interface Executor {

	public <T> T query(String statement, Object parameter);

}
