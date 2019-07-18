package com.lemon.auto.web.day03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

public class LocateElementByXPath {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
				"src/main/resources/driver/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://120.78.128.25:8765/Index/login.html");
		
		//button的路径
		
		// 1：绝对路径
		// /html/body/div[2]/div/form/div[5]/button
//		driver.findElement(By.xpath("html/body/div[2]/div/form/div[5]/button")).click();
//		driver.findElement(By.xpath("/html/body/div[2]/div/form/div[5]/button")).click();
//		driver.findElement(By.xpath("./html/body/div[2]/div/form/div[5]/button")).click();
		
		// //*[@id="kw"]
		
		//采用绝对路径的xpath缺点：
		//1）:网页结构（dom）是经常会变动
		//2）：如果网页结构复杂，xpath很非常长，不好维护
		
		//  //*[@class='form-group mt-20']
//		WebElement element = driver.findElement(By.xpath("//*[@class='form-group mt-20']/button"));
//		element.click();
		
//		driver.findElement(By.xpath("//*[contains(@name,'one')]")).sendKeys("13555555555");

//		driver.findElement(By.xpath("//*[text()='免费注册']")).click();
		
//		driver.findElement(By.xpath("//a[contains(text(),'免费注册')]")).click();
		
		//2：相对路径
		//工具去完成
		//1）chrome: copy xpath: 
		//2） firefox： 安装firebug、firepath 插件

		Thread.sleep(5000);
		driver.quit();
	}

}
