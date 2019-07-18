package com.lemon.auto.web.day04.section01;

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

		//1：正确的手机号、错误的密码--？
		driver.findElement(By.id("mobilephone")).sendKeys("13825161923");
		driver.findElement(By.id("password")).sendKeys("lemon123");
		
		
		//2:不输入手机号、不输入密码
		driver.findElement(By.id("login")).click();
		
		//延时等待
		//1：线程等待、硬性等待、强制等待、线程休眠
		//线程休眠2秒
//		Thread.sleep(2000);
		
		String tips = driver.findElement(By.className("tips")).getText();
		/**
		 * 1：正确的手机号、错误的密码 ,得不到提示信息 --》访问到后端、数据库验证--》有时间
		 * 2：不输入手机号、不输入密码,能得到提示信息--》前端校验不通过
		 */
//		WebElement tipElement = driver.findElement(By.className("tips"));
//		String tips = tipElement.getText();
//		System.out.println(tipElement);
		
		System.out.println(tips);
		


		Thread.sleep(5000);

		driver.quit();
	}

}
