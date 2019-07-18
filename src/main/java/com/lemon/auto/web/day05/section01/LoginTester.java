package com.lemon.auto.web.day05.section01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginTester {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
				"src/main/resources/driver/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();

		driver.get("http://47.107.168.87:8087/lmcanon_web_auto/mng/register.html");
		//		driver.findElement(By.id("mobilephone")).sendKeys("13825161923");
		//		driver.findElement(By.id("password")).sendKeys("123456");
		//		driver.findElement(By.id("login")).click();
		getElement(driver, 5, By.id("mobilephone")).sendKeys("13333333334");
		
		getElement(driver, 5, By.id("password")).sendKeys("123456");
		getElement(driver, 5, By.id("pwdconfirm")).sendKeys("123456");
		getElement(driver, 5, By.id("verifycode")).sendKeys("LM19");
		
		getElement(driver, 5, By.id("signup-button")).click();
		
//		ExpectedConditions.textToBePresentInElement(By.className("tips"), "用户名不能为空");
		ExpectedConditions.textToBePresentInElementLocated(By.className("tips"), "用户名不能为空");
		String string   = getElement2(driver, 5, By.className("tips"));
		System.out.println(string);

		Thread.sleep(5000);
		driver.quit();
	}

	/**
	 * 智能查找元素
	 * @param driver
	 * @param timeOutInSeconds
	 * @param by
	 * @return
	 */
	public static WebElement getElement(WebDriver driver, long timeOutInSeconds, By by) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		WebElement element = wait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver input) {
				return driver.findElement(by);
			}
		});
		return element;
	}
	
	public static String getElement2(WebDriver driver, long timeOutInSeconds, By by) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		String String = wait.until(new ExpectedCondition<String>() {
			@Override
			public String apply(WebDriver input) {
				return driver.findElement(by).getText();
			}
		});
		return String;
	}

}
