package com.lemon.auto.web.day02.section01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class IETester {
	
	//selenium 2.53.1
	public static void main(String[] args) throws InterruptedException {
		System.setProperty(InternetExplorerDriverService.IE_DRIVER_EXE_PROPERTY, "src/main/resources/driver/IEDriverServer.exe");
		//通用性的设置
		//在创建驱动的时候，增加一些能力
		DesiredCapabilities capabilities = new DesiredCapabilities();
		//忽略安全域的设置
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		//忽略浏览器缩放
		capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		
		InternetExplorerDriver driver = new InternetExplorerDriver(capabilities);
		
		Thread.sleep(2000);
		driver.quit();

	}

}
