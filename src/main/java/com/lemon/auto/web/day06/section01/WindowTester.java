package com.lemon.auto.web.day06.section01;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

public class WindowTester {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
				"src/main/resources/driver/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://mail.qq.com/");
		
		//获取到第一个窗口句柄
		String firstHandle = driver.getWindowHandle();
		//点手机版后，会打开一个新的窗口
		driver.findElement(By.partialLinkText("手机版")).click();
		//获得所有的窗口句柄
		Set<String> allHandles = driver.getWindowHandles();
		//list（打开一个窗口--》打开一个--》关闭了一个--》打开了）
		//怎么拿到第二个窗口的句柄
		String secondHandle = null;
		for (String handle : allHandles) {
			//如果遍历的这个句柄和第一个窗口的句柄不相等的话，这就是第二个句柄
			if (!handle.equals(firstHandle)) {
				secondHandle = handle;
			}
		}
		
		driver.switchTo().window(secondHandle);
		
		
		//操作另外一个窗口的时候，要进行窗口的切换
//		driver.switchTo().window(nameOrHandle)
		//name：窗口的名称
		//Handle：句柄
		
		
		Thread.sleep(3000);
		driver.findElement(By.className("download_ios")).click();
		
		//切换到第一个窗口
		driver.switchTo().window(firstHandle);
		driver.findElement(By.partialLinkText("企业邮箱")).click();
		
		
		Thread.sleep(5000);

		driver.quit();
	}

}
