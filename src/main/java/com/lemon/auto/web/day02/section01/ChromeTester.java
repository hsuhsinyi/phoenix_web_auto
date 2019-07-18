package com.lemon.auto.web.day02.section01;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

public class ChromeTester {

	public static void main(String[] args) throws InterruptedException {
		//https://chromedriver.storage.googleapis.com/index.html
		
		System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, "src/main/resources/driver/chromedriver.exe");
		//错误1：The path to the driver executable must be set by the webdriver.chrome.driver system property;
		ChromeDriver driver = new ChromeDriver();

		Thread.sleep(2000);
		
		driver.quit();
	}

}
