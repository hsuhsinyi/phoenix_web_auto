package com.lemon.phoenix.util;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

	// 属性文件在一个项目中可能有多个
	public static Properties urlProperties;

	/**
	 * static块只会运行一次
	 */
	static {
		loadURLProperties();
	}

	/**
	 * 加载url属性文件：保存的是所有的
	 */
	public static void loadURLProperties() {
		if (urlProperties == null) {
			urlProperties = new Properties();
		}
		try {
			urlProperties.load(PropertiesUtil.class.getResourceAsStream("/url.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Example
	public static void main(String[] args) {
		String registerURL = PropertiesUtil.urlProperties.getProperty("loginURL");
		System.out.println(registerURL);
	}
	
	

}
