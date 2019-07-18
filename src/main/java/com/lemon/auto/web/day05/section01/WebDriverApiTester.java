package com.lemon.auto.web.day05.section01;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

public class WebDriverApiTester {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
				"src/main/resources/driver/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		
//		driver.get(String url)：访问指定url页面
		driver.get("http://www.baidu.com"); //阻塞（同步、异步全部加载完了后才会执行后续的代码）
//		driver.findElement(By.id(""));
		
//		driver.getCurrentUrl():获取当前页面的url地址
//		System.out.println(driver.getCurrentUrl()); //断言
		
//		driver.getTitle():获取当前页面的标题
//		System.out.println(driver.getTitle()); //断言
//		driver.getPageSource()：获取当前页面源代码
//		System.out.println(driver.getPageSource()); //断言
		
//		driver.quit()：关闭驱动对象以及所有相关的窗口
		
//		driver.close()：关闭当前窗口
//		driver.findElement(By.id("jgwab")).click();
//		Thread.sleep(2000);
//		driver.close();
		
//		driver.findElement(by)：根据by对象获取单个元素
//		driver.findElements(by)：根据by对象获取元素集合
		
//		driver.getWindowHandle()：返回当前页面句柄
		String fisrtWindowHandle = driver.getWindowHandle();//获取当前窗口句柄：窗口的一个编号，唯一标识符
		System.out.println(fisrtWindowHandle);//-->点击会打开另外一个窗口
		
		driver.findElement(By.id("jgwab")).click();//
		String handle = driver.getWindowHandle();//获取当前窗口句柄：窗口的一个编号，唯一标识符
		System.out.println(handle);
		
//		driver.getWindowHandles()：返回所有由驱动对象打开页面的句柄，页面不同，句柄不一样
		System.out.println("-------------------------------------------------");
		Set<String> handles = driver.getWindowHandles();
		for (String item : handles) {
			System.out.println(item);
		}
		

		

		Thread.sleep(5000);

		driver.quit();
	}

}
