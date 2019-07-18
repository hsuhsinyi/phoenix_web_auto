package com.lemon.auto.web.day06.section02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.interactions.Actions;

public class ActionsTester {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
				"src/main/resources/driver/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		
		driver.get("http://www.baidu.com");
		WebElement input = driver.findElement(By.id("kw"));
		WebElement button = driver.findElement(By.id("su"));

		Actions actions = new Actions(driver);//创建Action对象
		//输入关键字的动作
		actions.sendKeys(input, "您好").perform();//键盘输入
		//移动的动作
		actions.moveToElement(button).perform();//移动鼠标并点击
		//点击搜索的动作
		actions.click().perform();//点击
		

		Thread.sleep(5000);
		driver.quit();
	}
}
