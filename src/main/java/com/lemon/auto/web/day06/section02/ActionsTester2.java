package com.lemon.auto.web.day06.section02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class ActionsTester2 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
				"src/main/resources/driver/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		
		driver.get("http://www.treejs.cn/v3/demo/cn/exedit/drag.html");
		
		WebElement ele1 = driver.findElement(By.id("treeDemo_4_span"));
		WebElement ele2 = driver.findElement(By.id("treeDemo_8_span"));
		
		Actions actions = new Actions(driver);
		actions.clickAndHold(ele1).moveToElement(ele2).release().perform();
		
//		Action action = actions.clickAndHold(ele1).moveToElement(ele2).release().build();
//		action.perform();
		

		Thread.sleep(5000);
		driver.quit();
	}
}
