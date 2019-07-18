package com.lemon.auto.web.day02.section01;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriver.SystemProperty;

public class FirefoxTester {

	//2.x版本推荐：selenium 2.53.1 + firefox 46.0 --》selenium 2.xx 自带驱动
	//selenium 3.x --》去官网下载对应驱动
	
	//3.x版本推荐：selenium 3.5.1+firefox 56
	
	public static void main(String[] args) throws InterruptedException {
		//3.x的需要下载驱动文件，设置系统路径
		//System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe")

		//错误1：Cannot find firefox binary in PATH. Make sure firefox is installed.
		System.setProperty(SystemProperty.BROWSER_BINARY, "D:\\Program Files\\Mozilla Firefox\\firefox.exe");
		//打开火狐的浏览器
		FirefoxDriver driver = new FirefoxDriver();
		driver.get("http://www.baidu.com");
		Thread.sleep(2000);
		
		driver.quit();
	}

}
