package com.lemon.auto.web.day01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class IETester {
	
	//selenium 2.53.1
	public static void main(String[] args) throws InterruptedException {
		//人工--》人（眼睛、大脑、手）
		//程序--》Driver（ie驱动）-->打开浏览器
		System.setProperty("webdriver.ie.driver", "src/main/resources/driver/IEDriverServer.exe");
//		1：打开浏览器
		InternetExplorerDriver driver = new InternetExplorerDriver();
		//错误1：The path to the driver executable must be set by the webdriver.ie.driver system property;
		//错误2：Unexpected error launching Internet Explorer. Protected Mode settings are not the same for all zones. Enable Protected Mode must be set to the same value (enabled or disabled) for all zones
		//错误3：Unexpected error launching Internet Explorer. Browser zoom level was set to 144%. It should be set to 100%
		
//		2：输入url
		driver.get("http://www.baidu.com");
		
//		3:找到百度输入框，输入要查找的内容
		driver.findElement(By.id("kw")).sendKeys("柠檬班");
		
//		4:找到"百度一下"按钮，点击
		WebElement btn = driver.findElement(By.id("su"));
		btn.click();
		
//		5:检查查询的内容是否符合要求
		//TODO
		
//		6:关闭浏览器
		Thread.sleep(2000);
		
		driver.quit();

	}

}
