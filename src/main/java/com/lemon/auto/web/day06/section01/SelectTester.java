package com.lemon.auto.web.day06.section01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.ui.Select;

public class SelectTester {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
				"src/main/resources/driver/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://39.108.136.60:8085/lmcanon_web_auto/mng/login.html");
		driver.findElement(By.id("mobilephone")).sendKeys("13825161923");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.id("login")).click();
		
		Thread.sleep(1000);
		driver.findElement(By.id("menu-teacher")).click();
		Thread.sleep(1000);
		driver.findElement(By.partialLinkText("老师信息")).click();
		Thread.sleep(1000);
		//得到iframe web元素
		WebElement iframe = driver.findElement(By.cssSelector("iframe[src='teacher-list.html']"));
		driver.switchTo().frame(iframe);
		
//		driver.findElement(By.cssSelector("option[value='3']")).click();
		
		WebElement selectEle = driver.findElement(By.name("type"));
		Select select = new Select(selectEle);
		//根据index： 索引从0开始的
		select.selectByIndex(2);
		
		Thread.sleep(3000);
		select.selectByValue("5");
		
		Thread.sleep(3000);
		select.selectByVisibleText("销售人员");
		
		
		Thread.sleep(5000);

		driver.quit();
	}

}
