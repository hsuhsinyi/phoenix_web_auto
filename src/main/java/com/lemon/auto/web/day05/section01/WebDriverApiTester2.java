package com.lemon.auto.web.day05.section01;

import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

public class WebDriverApiTester2 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
				"src/main/resources/driver/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();

//		获取navigate对象
		Navigation navigation = driver.navigate();
//		访问指定的url地址
//		navigation.to(url)
		navigation.to("http://www.lemfix.com");
//		刷新当前页面
//		navigation.refresh();
		Thread.sleep(3000);
		navigation.refresh();
		
		Thread.sleep(3000);
		navigation.to("http://www.baidu.com");
		
//		浏览器回退操作
//		navigation.back();
		Thread.sleep(3000);
		navigation.back();
//		浏览器前进操作
//		navigation.forward();
		Thread.sleep(3000);
		navigation.forward();

		Thread.sleep(5000);

		driver.quit();
	}

}
