package com.lemon.auto.web.day05.section02;

import java.util.concurrent.TimeUnit;

import org.omg.PortableServer.THREAD_POLICY_ID;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

public class AlertTester {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
				"src/main/resources/driver/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		
//		原生alert警告框
	/*	driver.get("file:///E:/svn-lemon/%E6%95%99%E5%AD%A6ppt/happy/java%E8%87%AA%E5%8A%A8%E5%8C%96/04-web%E8%87%AA%E5%8A%A8%E5%8C%96/%E7%89%B9%E6%AE%8A%E5%85%83%E7%B4%A0%E5%AE%9A%E4%BD%8D%E5%92%8C%E6%93%8D%E4%BD%9C%E9%9D%99%E6%80%81%E9%A1%B5%E9%9D%A2/%E6%A8%A1%E6%80%81%E6%A1%86/alert.html");
		
		driver.findElement(By.id("abtn")).click();
		
		Alert alert = driver.switchTo().alert();
		String text = alert.getText();
		System.out.println(text);
		
		Thread.sleep(3000);
		alert.dismiss();*/
		
		//原生confirm确定框
		//
//		driver.switchTo().alert()
//		driver.switchTo().frame(index)
//		driver.switchTo().window(nameOrHandle)
		
		driver.get("file:///E:/svn-lemon/%E6%95%99%E5%AD%A6ppt/happy/java%E8%87%AA%E5%8A%A8%E5%8C%96/04-web%E8%87%AA%E5%8A%A8%E5%8C%96/%E7%89%B9%E6%AE%8A%E5%85%83%E7%B4%A0%E5%AE%9A%E4%BD%8D%E5%92%8C%E6%93%8D%E4%BD%9C%E9%9D%99%E6%80%81%E9%A1%B5%E9%9D%A2/%E6%A8%A1%E6%80%81%E6%A1%86/confirm.html");
		driver.findElement(By.id("abtn")).click();
		
		Alert alert = driver.switchTo().alert();
		System.out.println(alert.getText());
		
		Thread.sleep(3000);
//		alert.dismiss();
		alert.accept();

		Thread.sleep(5000);

		driver.quit();
	}

}
