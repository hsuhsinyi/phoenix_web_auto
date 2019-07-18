package com.lemon.phoenix.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.StaticApplicationContext;
import org.testng.TestNG;

/**
 * 对Appium Server进行管理的工具类
 * 
 * @author hhy
 *
 */
public class AppiumServerUtil {

	public static final String appiumLogPath = "D:\\test9\\";
	// appium启动脚本main.js绝对路径
	public static final String appiumJsPath = "C:\\Users\\Administrator\\AppData\\Local\\Programs\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js";

	//声明udidList
	public static List<String> udidList;
	public static List<String> appiumPortList;
	public static List<String> bootstrapPortList;
	public static List<String> uiautomator2PortList;
	
	public static void startAppiumServer() {
		// 1、得到设备名（udid）集合
		udidList = CmdUtil.getUdidList();
		// 2、初始化Appium的监听端口号集合4723 4725 4727...
		appiumPortList = CmdUtil.getPortList(4723, udidList.size());
		// 3、初始化bootstrap的端口号集合4724 4726 4728...
		bootstrapPortList = CmdUtil.getPortList(4724, udidList.size());
		//4、初始化uiautomator2端口集合8200 8202 8204...
		uiautomator2PortList=CmdUtil.getPortList(8200, udidList.size());
		// 4、得到Appium启动命令集合
		List<String> commandList = new ArrayList<String>();
		for (int i = 0; i < udidList.size(); i++) {
			int index=i+1;
			// Appium启动命令参数进行拼接
			String cmd = "node " + appiumJsPath + " -U " + udidList.get(i) + " -a 127.0.0.1 " + "-p "
					+ appiumPortList.get(i) + " -bp " + bootstrapPortList.get(i) + " --session-override " + " --log "
					+ appiumLogPath + "appium" + index + ".log";
			commandList.add(cmd);
			System.out.println(cmd);

		}
		//for循环去启动Appium服务，因为第一个Appium服务起来之后一直在后台运行，第二个启动Appium服务命令实际上是执行
		//进程阻塞
		for (final String cmd : commandList) {
			//通过已有的参数命令组合去启动Appium服务
			//解决方案：多线程：线程与线程之间是相互独立运行，并且线程是运行在进程的内部，
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					//写上你要在线程里面需要做的事情
					CmdUtil.execCmd(cmd);
				}
			}).start();
			
		}
		
	}

	public static void main(String[] args) {
		//1、启动多个Appium服务
		startAppiumServer();
		//2、创建testng文件
		XmlUtil.createTestngXml();
		//3、通过代码的方式去执行testng.xml文件
		//初始化testng对象
		TestNG testNG = new TestNG();
		//初始化一个List集合，用于保存testng.xml的路径
		List<String> testFilesPath = new ArrayList<String>();
		testFilesPath.add(System.getProperty("user.dir")+"\\testng_create.xml");
		//把想要执行的testng.xml文件路径添加到testng配置中
		testNG.setTestSuites(testFilesPath);
		testNG.run();
	}
}
