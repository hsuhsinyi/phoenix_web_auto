package com.lemon.phoenix.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.lemon.phoenix.pojo.Locator;

public class UILibraryUtil {

	//所有页面的描述
	public static HashMap<String, HashMap<String, Locator>> pagesMap = new HashMap<String, HashMap<String, Locator>>();

	static {
		readUILibrary();
	}

	// 数据载体xml--》java中对象
	private static HashMap<String, HashMap<String, Locator>> readUILibrary() {
		SAXReader reader = new SAXReader();
		//包含所有的页面的描述
		try {
			// 获得xml文档
			Document document = reader.read(UILibraryUtil.class.getResourceAsStream("/UILibrary.xml"));
			// 获得根元素
			Element rootElement = document.getRootElement();
			// 获得所有子元素
			List<Element> pageElements = rootElement.elements("page");
			// 遍历每一个页面
			for (Element page : pageElements) {
				// 页面的名称--》类名
				String pageName = page.attributeValue("name");
				// 页面的描述
				String pageDesc = page.attributeValue("desc");
				//获得每个页面下的所有元素信息
				List<Element> locatorElements = page.elements("locator");
				
				//保存每个页面的locator的map
				HashMap<String, Locator> lacatorMap = new HashMap<String, Locator>();
				
				//遍历每一个元素
				for (Element locatorElement : locatorElements) {
					//定位的方式
					String by = locatorElement.attributeValue("by");
					//定位的值
					String value = locatorElement.attributeValue("value");
					//元素的描述
					String desc = locatorElement.attributeValue("desc");
					//创建locator
					Locator locator = new Locator(by, value, desc);
					lacatorMap.put(desc, locator);
				}
				pagesMap.put(pageName, lacatorMap);
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pagesMap;
	}

	// example
	public static void main(String[] args) {
/*//		Class<UILibraryUtil> class1 = UILibraryUtil.class;
		HashMap<String, HashMap<String, Locator>> map = UILibraryUtil.readUILibrary();
		//我要获得注册页面的账户输入框元素怎么去定位信息
		//1：从pagesMap中间找到注册页面
		HashMap<String, Locator> page = map.get("com.lemon.phoenix.tester.RegisterPage");
		//2:在注册页面找到账户输入框
		Locator locator = page.get("账户输入框");
		System.out.println(locator);*/
	}
}
