package com.lemon.auto.web.day05.section02;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

public class WebDriverApiTester3 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
				"src/main/resources/driver/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		
		Options options = driver.manage();
		
//		driver.manage().timeouts().implicitlyWait(time, unit)
//		driver.manage().timeouts().pageLoadTimeout(time, unit)
		
		Window window = driver.manage().window();
//		window.maximize();//最大化
//		driver.manage().window().maximize();
//		获取window对象
//		Window window = driver.manage().window();
//		窗口最大化
//		maximize()
		
//		窗口位置
//		getPosition()
//		Point point = window.getPosition();
//		System.out.println(point.x);
//		System.out.println(point.y);
		
//		窗口大小
//		Dimension dimension = window.getSize();
//		System.out.println(dimension.getHeight());
//		System.out.println(dimension.getWidth());
//		getSize()
//		设置位置
//		setPosition(targetPosition)
		Point point = new Point(100, 100);
		window.setPosition(point);
		
		Thread.sleep(3000);
//		设置窗口大小
//		setSize(targetSize)
		Dimension dimension = new Dimension(500, 500);
		window.setSize(dimension);

		

		Thread.sleep(5000);

		driver.quit();
	}

}
