package com.lemon.auto.web.day05.section02;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

public class DivAlertTester {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
				"src/main/resources/driver/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://120.78.128.25:8765");
		
		driver.findElement(By.partialLinkText("收益计算器")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div[data-type='5']")).click();
		
		Thread.sleep(5000);

		driver.quit();
	}

}
