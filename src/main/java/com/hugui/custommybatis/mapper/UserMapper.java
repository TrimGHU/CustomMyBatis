package com.hugui.custommybatis.mapper;

import com.hugui.custommybatis.bean.User;

/**
 * Copyright © 2018 hugui. All rights reserved.
 * 
 * @Title: UserMapper.java
 * @Prject: custom-mybatis
 * @Package: com.hugui.custommybatis.mapper
 * @Description:
 * @author: HuGui
 * @date: 2019年1月21日 下午3:12:44
 * @version: V1.0
 */

public interface UserMapper {

	User getUserById(String id);

}
