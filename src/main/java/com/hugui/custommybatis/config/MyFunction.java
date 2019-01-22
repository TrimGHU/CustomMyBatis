package com.hugui.custommybatis.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright © 2018 hugui. All rights reserved.
 * 
 * @Title: MyFunction.java
 * @Prject: custom-mybatis
 * @Package: com.hugui.custommybatis.config
 * @Description:
 * @author: HuGui
 * @date: 2019年1月21日 下午4:16:59
 * @version: V1.0
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyFunction {
	private String sqltype;
	private String funcName;
	private String sql;
	private Object resultType;
	private String parameterType;
}
