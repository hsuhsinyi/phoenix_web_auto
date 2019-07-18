package com.lemon.auto.web.day06.section01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

public class LoginTester {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
				"src/main/resources/driver/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://39.108.136.60:8085/lmcanon_web_auto/mng/login.html");
		driver.findElement(By.id("mobilephone")).sendKeys("13825161923");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.id("login")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.id("menu-teacher")).click();
		Thread.sleep(2000);
		driver.findElement(By.partialLinkText("老师信息")).click();
		//nickname对应的输入框在一个iframe中间
		//首先应该切换到iframe
//		driver.switchTo().frame(index) //(不推荐)
//		driver.switchTo().frame(nameOrId) //根据name、id，推荐
//		driver.switchTo().frame(frameElement) //先找到iframe元素，推荐
		
		Thread.sleep(2000);
		//得到iframe web元素
		WebElement iframe = driver.findElement(By.cssSelector("iframe[src='teacher-list.html']"));
		driver.switchTo().frame(iframe);
		
		driver.findElement(By.name("nickname")).sendKeys("韬哥");
		
		
		//回到主页面操作
		//需要切换为主页面
		driver.switchTo().defaultContent();
		driver.findElement(By.id("menu-product")).click();
		
		
		Thread.sleep(5000);

		driver.quit();
	}

}
