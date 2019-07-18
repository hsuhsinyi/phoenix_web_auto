package com.lemon.auto.web.day04.section01;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import bsh.commands.dir;

public class ElementOptTester {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, "src/main/resources/driver/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
//		driver.get("http://www.baidu.com");

		//点击
		/*driver.findElement(By.linkText("hao123")).click();
		
		Thread.sleep(2000);
		
		driver.get("http://www.baidu.com");
		//输入
		driver.findElement(By.id("kw")).sendKeys("柠檬班");
		
		Thread.sleep(2000);
		
		//清空
		driver.findElement(By.id("kw")).clear();
		
		Thread.sleep(2000);*/
		
//		WebElement input = driver.findElement(By.id("kw"));
//		input.sendKeys("柠檬班");
		
//		input.sendKeys(Keys.CONTROL,"c");
//		Thread.sleep(1000);
//		input.sendKeys(Keys.CONTROL,"v");//
//		Thread.sleep(1000);
//		input.sendKeys(Keys.CONTROL,"a");
//		input.sendKeys(Keys.CONTROL,"x");
//		Thread.sleep(1000);
//		input.sendKeys(Keys.CONTROL,"v");
		
/*		String tagName = driver.findElement(By.id("su")).getTagName();
		String attrValue = driver.findElement(By.id("su")).getAttribute("value");
		System.out.println(tagName);
		System.out.println(attrValue);*/
		
		/*String text = driver.findElement(By.id("cp")).getText();
		System.out.println(text);
		
		Thread.sleep(2000);*/
		
//		WebElement input = driver.findElement(By.id("kw"));
//		System.out.println(input.isDisplayed());//是否显示出来
//		System.out.println(input.isEnabled());//是否有效（编辑）
//		System.out.println(input.isSelected());// checkbox、radio、select中option
		
		driver.get("http://120.78.128.25:8765/Index/login.html");
		
		driver.findElement(By.name("phone")).sendKeys("13825161923");
		driver.findElement(By.name("password")).sendKeys("lemon123");
		//找到表单元素
		driver.findElement(By.name("login-form")).submit();
		
//		driver.findElement(By.className("btn-special")).click();
		
//		driver.findElement(By.className("btn-special")).submit();
		
		Thread.sleep(5000);
		
		driver.quit();
	}

}
