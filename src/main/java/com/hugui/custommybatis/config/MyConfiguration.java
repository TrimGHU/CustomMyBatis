package com.hugui.custommybatis.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * Copyright © 2018 hugui. All rights reserved.
 * 
 * @Title: MyConfiguration.java
 * @Prject: custom-mybatis
 * @Package: com.hugui.custommybatis.config
 * @Description: 读取config配置文件，构造实体
 * @author: HuGui
 * @date: 2019年1月21日 下午3:38:09
 * @version: V1.0
 */

public class MyConfiguration {

	private static ClassLoader loader = ClassLoader.getSystemClassLoader();

	public Connection build(String resource) {
		InputStream resourceAsStream = loader.getResourceAsStream(resource);
		SAXReader reader = new SAXReader();
		try {
			Document read = reader.read(resourceAsStream);
			return buildElement(read.getRootElement());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * <?xml version="1.0" encoding="UTF-8"?> <database>
	 * <property name="driverClassName">com.mysql.jdbc.Driver</property>
	 * <property name=
	 * "url">jdbc:mysql://localhost:3306/test?useUnicode=true&amp;characterEncoding=utf8</property>
	 * <property name="username">root</property>
	 * <property name="password">1234</property> </database>
	 * 
	 * @param rootElement
	 * @return
	 * @throws ClassNotFoundException
	 */
	private Connection buildElement(Element rootElement) throws ClassNotFoundException {
		if (!rootElement.getName().equals("database")) {
			throw new RuntimeException("root should be <database>");
		}

		String driverClassName = null;
		String url = null;
		String username = null;
		String password = null;

		@SuppressWarnings("unchecked")
		List<Element> elements = (List<Element>) rootElement.elements("property");
		for (Element element : elements) {
			String value = getValue(element);
			String name = element.attributeValue("name");
			if (name == null || value == null) {
				throw new RuntimeException("[database]: <property> should contain name and value");
			}

			switch (name) {
			case "url":
				url = value;
				break;
			case "username":
				username = value;
				break;
			case "password":
				password = value;
				break;
			case "driverClassName":
				driverClassName = value;
				break;
			default:
				throw new RuntimeException("[database]: <property> unknown name");
			}

		}

		Class.forName(driverClassName);

		try {
			// 此处需要注意，如果mysql是高版本，但是驱动是低版本，会出现连接时报时区或者编码的错误。
			return DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	private String getValue(Element node) {
		return node.hasContent() ? node.getText() : node.attributeValue("value");
	}

	public MapperBean readerMapper(String path) {
		MapperBean mapper = new MapperBean();
		try {
			InputStream resourceAsStream = loader.getResourceAsStream(path);
			SAXReader reader = new SAXReader();
			Document read = reader.read(resourceAsStream);
			Element rootElement = read.getRootElement();
			mapper.setInterfaceName(rootElement.attributeValue("nameSpace").trim());
			List<MyFunction> list = new ArrayList<>();

			for (@SuppressWarnings("unchecked")
			Iterator<Element> rootIter = rootElement.elementIterator(); rootIter.hasNext();) {
				MyFunction fun = new MyFunction();
				Element e = rootIter.next();
				String sqltype = e.getName().trim();
				String funcName = e.attributeValue("id").trim();
				String sql = e.getText().trim();
				String resultType = e.attributeValue("resultType").trim();
				fun.setSqltype(sqltype);
				fun.setFuncName(funcName);
				Object newInstance = null;
				try {
					// 类似此处mybatis做了很多基本类型的mapping
					// int -> java.lang.Integer
					// long -> java.lang.Long
					// string -> java.lang.String 等等
					newInstance = Class.forName(resultType).newInstance();
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				fun.setResultType(newInstance);
				fun.setSql(sql);
				list.add(fun);
			}
			mapper.setList(list);

		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return mapper;
	}
}
