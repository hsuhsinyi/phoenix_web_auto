package com.lemon.auto.web.day06.section02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

public class DatePickerTester {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
				"src/main/resources/driver/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://39.108.136.60:8085/lmcanon_web_auto/mng/login.html");
		driver.findElement(By.id("mobilephone")).sendKeys("13825161923");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.id("login")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.id("menu-product")).click();
		Thread.sleep(2000);
		driver.findElement(By.partialLinkText("一周排课")).click();
		Thread.sleep(2000);
		WebElement iframe = driver.findElement(By.cssSelector("iframe[src='class-plan-list.html']"));
		driver.switchTo().frame(iframe);
		
		driver.findElement(By.id("datemin")).sendKeys("2018-12-26 21:37:40");
		
		driver.findElement(By.id("searchButton")).click();
		
		Thread.sleep(5000);

		driver.quit();
	}

}
