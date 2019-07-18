package com.lemon.auto.web.day04.section02;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginTester2 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
				"src/main/resources/driver/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();

		driver.get("http://39.108.136.60:8085/lmcanon_web_auto/mng/login.html");
		driver.findElement(By.id("mobilephone")).sendKeys("13825161923");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.id("login")).click();
		
		waitElementDisplayed(driver, 3, By.id("menu-teacher"));
		driver.findElement(By.id("menu-teacher")).click();

		waitElementDisplayed(driver, 3, By.cssSelector("a[data-href='teacher-list.html']"));
		driver.findElement(By.cssSelector("a[data-href='teacher-list.html']")).click();

		Thread.sleep(5000);
		
		driver.quit();
	}
	
	public static boolean waitElementDisplayed(WebDriver driver,long timeoutInSeconds,By by){
		WebDriverWait wait = new WebDriverWait(driver,3);
		//until直到xxx才
		return wait.until(new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver arg0) {
				System.out.println("---------------------------------");
				return driver.findElement(by).isDisplayed();
			}
			
		
		});
		
	}
	
	/**
	 * 智能查找元素
	 * @param driver
	 * @param timeOutInSeconds
	 * @param by
	 * @return
	 */
	public static WebElement getElement(WebDriver driver,long timeOutInSeconds,By by){
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		WebElement element = wait.until(new ExpectedCondition<WebElement>() {

			@Override
			public WebElement apply(WebDriver input) {
				return driver.findElement(by);
			}
		});
		return element;
	}

}
