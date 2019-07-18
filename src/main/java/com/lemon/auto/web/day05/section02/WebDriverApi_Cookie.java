package com.lemon.auto.web.day05.section02;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

public class WebDriverApi_Cookie {

	public static void main(String[] args) throws InterruptedException {
		//1：绕过验证码
		//2: 提高脚本的效率
		System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
				"src/main/resources/driver/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		
		driver.get("http://120.78.128.25:8765");
		driver.manage().deleteAllCookies();
		//fengwoo  ln8ghj7h5hemt0getjsfm8n3c2
		//remember_me 13825161923
		Options options = driver.manage();
		options.addCookie(new Cookie("fengwoo", "c2cr85ebtai6min2a79lt5ad77"));
		options.addCookie(new Cookie("remember_me", "13825161923"));
		
		driver.get("http://120.78.128.25:8765");
		
		//
		
		Thread.sleep(5000);

		driver.quit();
	}

}
