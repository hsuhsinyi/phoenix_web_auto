package com.lemon.auto.web.day02.section02;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

public class BaseTester {

	private static WebDriver driver;
	
	public static WebDriver getDriver() {
		return driver;
	}

	@BeforeSuite
	@Parameters(value = { "broswerType", "seleniumVersion", "driverExePath" })
	public void beforeSuite(String broswerType, String seleniumVersion, String driverExePath) {
		driver = SeleniumUtils.getWebDriver(broswerType, seleniumVersion, driverExePath);
	}

	@AfterSuite
	public void afterSuite() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
	}
	
	public void to(String url){
		driver.get(url);
	}

}
