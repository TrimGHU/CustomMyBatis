package com.hugui.custommybatis.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hugui.custommybatis.bean.User;

/**
 * Copyright © 2018 hugui. All rights reserved.
 * 
 * @Title: MyExcutor.java
 * @Prject: custom-mybatis
 * @Package: com.hugui.custommybatis.config
 * @Description:
 * @author: HuGui
 * @date: 2019年1月21日 下午5:56:20
 * @version: V1.0
 */

public class MyExecutor implements Executor {

	private MyConfiguration config = new MyConfiguration();

	private Connection connection;

	/**
	 * 控制访问数据库
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T query(String statement, Object parameter) {
		Connection myconnection = getConnection();
		ResultSet rs = null;

		try (PreparedStatement ps = myconnection.prepareStatement(statement)) {
			ps.setString(1, parameter.toString());
			rs = ps.executeQuery();

			User u = User.builder().build();
			while (rs.next()) {
				u.setId(rs.getString(1));
				u.setUsername(rs.getString(2));
				u.setPassword(rs.getString(3));
			}

			return (T) u;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				myconnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	private synchronized Connection getConnection() {
		if (connection != null) {
			return connection;
		}

		connection = config.build("config.xml");
		return connection;
	}

}
