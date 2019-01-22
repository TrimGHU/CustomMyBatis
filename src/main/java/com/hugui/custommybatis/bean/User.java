package com.hugui.custommybatis.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright © 2018 hugui. All rights reserved.
 * 
 * @Title: User.java
 * @Prject: custom-mybatis
 * @Package: com.hugui.custommybatis.bean
 * @Description:
 * @author: HuGui
 * @date: 2019年1月21日 下午3:09:34
 * @version: V1.0
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class User {
	private String id;
	private String username;
	private String password;
}
