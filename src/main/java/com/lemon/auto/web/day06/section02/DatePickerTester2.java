package com.lemon.auto.web.day06.section02;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

public class DatePickerTester2 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
				"src/main/resources/driver/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.12306.cn/index/");
		//先执行JavaScript把该元素的只读属性移除掉
		String jsStr = "document.getElementById('train_date').removeAttribute('readonly')";
		
//		WebDriver driver = new ChromeDriver();
//		JavascriptExecutor executor = (JavascriptExecutor) driver;
//		executor.executeScript(jsStr);//同步执行脚本
		driver.executeScript(jsStr);//同步执行脚本
		
		//移除完了，此时就可以去操作这个元素了
		//清空输入框的默认内容
		Thread.sleep(1000);
		driver.findElement(By.id("train_date")).clear();
		Thread.sleep(1000);
		driver.findElement(By.id("train_date")).sendKeys("2018-12-29");
		Thread.sleep(5000);

		driver.quit();
	}

}
