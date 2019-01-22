package com.hugui.custommybatis.config;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright © 2018 hugui. All rights reserved.
 * 
 * @Title: MapperBean.java
 * @Prject: custom-mybatis
 * @Package: com.hugui.custommybatis.config
 * @Description:
 * @author: HuGui
 * @date: 2019年1月21日 下午4:16:21
 * @version: V1.0
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MapperBean {

	private String interfaceName; // 接口名
	private List<MyFunction> list; // 接口下所有方法

}
