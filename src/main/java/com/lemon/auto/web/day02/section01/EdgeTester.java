package com.lemon.auto.web.day02.section01;

import org.openqa.selenium.edge.EdgeDriver;

public class EdgeTester {

	public static void main(String[] args) throws InterruptedException {
		//The path to the driver executable must be set by the webdriver.edge.driver system property;
		System.setProperty("webdriver.edge.driver", "src/main/resources/driver/MicrosoftWebDriver.exe");
		EdgeDriver driver = new EdgeDriver();
		driver.get("http:www.baidu.com");
		Thread.sleep(2000);
		driver.quit();
	}

}
