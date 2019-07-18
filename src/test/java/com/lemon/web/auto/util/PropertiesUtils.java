package com.lemon.web.auto.util;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils {
	
	private static Properties urlProperties;
	public static Properties configProperties;
	
	static {
		if (urlProperties == null) {
			urlProperties = new Properties();
		}
		if (configProperties == null) {
			configProperties = new Properties();
		}
		try {
			urlProperties.load(PropertiesUtils.class.getResourceAsStream("/url.properties"));
			configProperties.load(PropertiesUtils.class.getResourceAsStream("/config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获得完整的网址：baseURL + 资源路径
	 * @param urlKey
	 * @return
	 */
	public static String getUrl(String urlKey){
		return urlProperties.getProperty("base_url") + urlProperties.getProperty(urlKey);
	}
	
	public static String getConfig(String configKey){
		return configProperties.getProperty(configKey);
	}

	public static void main(String[] args) {
//		System.out.println(urlProperties.getProperty("base_url"));
//		System.out.println(urlProperties.getProperty("login_url"));
//		System.out.println(getUrl("login_url"));
		
	}

}
