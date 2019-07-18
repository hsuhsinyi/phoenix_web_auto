package com.lemon.phoenix.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.context.support.StaticApplicationContext;

import com.lemon.phoenix.pojo.Config;
import com.lemon.phoenix.pojo.Locator;
import com.lemon.phoenix.pojo.Page;

public class XmlUtil {
	
	//我们要将解析UI库得到的信息存储的位置
	public static List<Page> listPages = new ArrayList<Page>();
	
	//我们将解析ConfigInfo文件得到信息存储在这里
	public static Config config;
	
	//静态代码块：在程序刚开始运行的时候就进行解析，将其放到内存中，以供我们的测试用例使用
	static{
		readUIlibrary();
		readConfigInfos();
	}
	
	//应用到dom4j的技术解析xml文件
	/**
	 * 解析UI库得到元素/页面信息保存
	 */
	public static void readUIlibrary(){
		//1、初始化saxReader对象
		SAXReader saxReader = new SAXReader();
		try {
			//2、获取得到xml文档的document对象
			Document document = saxReader.read(XmlUtil.class.getResourceAsStream("/UIlibrary.xml"));
			//3、获取到document的根节点
			Element rootElement = document.getRootElement();
			//4、在根节点的基础上获取到所有的子节点
			List<Element> pageElements = rootElement.elements("page");
			for (Element pageElement : pageElements) {
				//5、得到page节点的属性activityName
				String activityName = pageElement.attributeValue("activityName");
				//6、得到page节点的属性pageDesc
				String pageDesc = pageElement.attributeValue("pageDesc");
				//7、得到page节点下的子节点locator
				List<Element> locatorElements = pageElement.elements("locator");
				
				//初始化locator list集合，用于保存解析得到的locator信息
				List<Locator> listLocator= new ArrayList<Locator>(); 
				
				for (Element locatorElement : locatorElements) {
					//8、得到locator节点的属性by
					String locatorBy = locatorElement.attributeValue("by");
					//9、得到locator节点的属性value
					String locatorValue = locatorElement.attributeValue("value");
					//10、得到locator节点的属性desc
					String locatorDesc = locatorElement.attributeValue("desc");
					//初始化Locator对象
					Locator locator = new Locator(locatorBy, locatorValue, locatorDesc);
					//把所有的locator保存到listLocator集合中
					listLocator.add(locator);
				}
				//初始化page对象，将activityName、pageDesc、listLocator保存在里面
				Page page = new Page(activityName, pageDesc, listLocator);
				//将所有的page放到listPages中
				listPages.add(page);
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 解析ConfigInfos文件得到相对应的配置
	 */
	public static void readConfigInfos(){
		SAXReader saxReader = new SAXReader();
		try {
			Document document =saxReader.read(XmlUtil.class.getResourceAsStream("/configInfos.xml"));
			Element rootElement = document.getRootElement();
			
			//得到platformName标签的value值
			Element platformNameElement = rootElement.element("platformName");
			String platformName = platformNameElement.attributeValue("value");
			
			//得到appPackage标签的value值
			Element appPackageElement = rootElement.element("appPackage");
			String appPackage = appPackageElement.attributeValue("value");
			
			//得到appActivity标签的value值
			Element appActivityElement = rootElement.element("appActivity");
			String appActivity = appActivityElement.attributeValue("value");
			
			//得到classes标签
			Element classesElement = rootElement.element("classes");
			List<Element> classElements = classesElement.elements("class");
			List<String> classNameList = new ArrayList<String>();
			for (Element element : classElements) {
				String className = element.attributeValue("name");
				classNameList.add(className);
			}
			config = new Config(platformName, appPackage, appActivity, classNameList);
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 创建testng文件
	 */
	public static void createTestngXml(){
		//plaformName、appPackage、appActivity、测试类、。。。都有了
		//1、创建document对象
		Document document = DocumentHelper.createDocument();
		//2、创建根标签
		Element rootElement = DocumentHelper.createElement("suite");
		//3、让根标签生效
		document.setRootElement(rootElement);
		
		//name="Suite" parallel="tests" thread-count="2"
		rootElement.addAttribute("name", "Suite");
		rootElement.addAttribute("parallel", "tests");
		rootElement.addAttribute("thread-count", AppiumServerUtil.udidList.size()+"");
		
		//4、for循环（根据设备的数量来创建test标签）
		for(int i=0; i<AppiumServerUtil.udidList.size();i++){
			//创建test标签
			Element testElement =rootElement.addElement("test");
			int index =i + 1;
			testElement.addAttribute("name", "device_"+index);
			
			//添加platformName parameter <parameter name="platformName" value="Android">
			Element platformNameElement = testElement.addElement("parameter");
			platformNameElement.addAttribute("name", "platformName");
			platformNameElement.addAttribute("value", config.getPlatformName());
			
			//添加udid parameter
			Element udidElement = testElement.addElement("parameter");
			udidElement.addAttribute("name", "udid");
			udidElement.addAttribute("value", AppiumServerUtil.udidList.get(i));
			
			//添加appPackage parameter
			Element appPackageElement = testElement.addElement("parameter");
			appPackageElement.addAttribute("name", "appPackage");
			appPackageElement.addAttribute("value", config.getAppPackage());
			
			//添加appActivity parameter
			Element appActivityElement = testElement.addElement("parameter");
			appActivityElement.addAttribute("name", "appActivity");
			appActivityElement.addAttribute("value", config.getAppActivity());
			
			//添加appiumPort parameter
			Element appiumPortElement = testElement.addElement("parameter");
			appiumPortElement.addAttribute("name", "appiumPort");
			appiumPortElement.addAttribute("value", AppiumServerUtil.appiumPortList.get(i));
			
			//添加uiautomator2Port parameter
			Element uiautomator2PortElement = testElement.addElement("parameter");
			uiautomator2PortElement.addAttribute("name", "uiautomator2Port");
			uiautomator2PortElement.addAttribute("value", AppiumServerUtil.uiautomator2PortList.get(i));
			
			//创建classes标签
			Element classesElement = testElement.addElement("classes");
			List<String> caseList = config.getClassNames();
			for (String testcase : caseList) {
				Element classElment = classesElement.addElement("class");
				classElment.addAttribute("name", testcase);
			}
			
		}
		//document对象填充完毕 ，写入到xml文件中
		//指定输出到文件的格式
		OutputFormat outputFormat = OutputFormat.createPrettyPrint();
		
		try {
			//初始化输出流对象
			FileOutputStream fileOutputStream = new FileOutputStream(System.getProperty("user.dir")+"\\testng_create.xml");
			//xml文档写出对象
			XMLWriter xmlWriter = new XMLWriter(fileOutputStream, outputFormat);
			xmlWriter.write(document);
			System.out.println("testng_create.xml文件创建成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) {
		//解析的结果是否正确
		readConfigInfos();
		System.out.println(config.getPlatformName());
		System.out.println(config.getAppPackage());
		System.out.println(config.getAppActivity());
		System.out.println(config.getClassNames());
	}

}
