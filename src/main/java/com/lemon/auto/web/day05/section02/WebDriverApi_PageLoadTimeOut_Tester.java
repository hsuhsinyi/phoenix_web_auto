package com.lemon.auto.web.day05.section02;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

public class WebDriverApi_PageLoadTimeOut_Tester {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
				"src/main/resources/driver/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		
		//页面加载超时
		//需求：如果一个页面加载的时间超过了10秒(100ms)--》bug
		//
		try {
			driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.MILLISECONDS);
			driver.get("http://120.78.128.25:8765/");
		} catch (TimeoutException e) {
			//提bug--》项目管理平台（禅道）
			System.out.println("捕获到了页面超时的异常，该提bug了");
		}
		
		Thread.sleep(5000);

		driver.quit();
	}

}
