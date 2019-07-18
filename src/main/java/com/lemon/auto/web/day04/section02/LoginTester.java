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

public class LoginTester {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
				"src/main/resources/driver/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();

		driver.get("http://39.108.136.60:8085/lmcanon_web_auto/mng/login.html");
		driver.findElement(By.id("mobilephone")).sendKeys("13825161923");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.id("login")).click();
		//延时等待
		//1：线程等待、硬性等待、强制等待、线程休眠
		//线程休眠2秒
		//Thread.sleep(2000);
		//缺点：不能确定应该等待多长的时间，容易造成时间的浪费，影响自动化脚本的执行效率

		//2：隐式等待（关注的是找到元素，不管元素状态）
		//定位元素过程时，会有3秒的等待时间(0.1秒)
		//如果定位到了，继续执行后续代码，如果3秒后没有定位到，就会抛出超时的异常
		//全局性能
		//隐式等待缺点：
		//1):全局性的设置，任何一个元素都会使用这个规则
		//2):不太智能，因为有时候需要操作元素（点击、输入）这个元素至少要可见(isDispaly=true)、可操作（isEnabled=true）
		//		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		//3:智能等待
		//轮询的时间是0.5秒，500ms
		WebDriverWait wait = new WebDriverWait(driver,3);
		//until直到xxx才
		wait.until(new ExpectedCondition() {
			//ExpectedCondition:期望的条件
			public Object apply(Object arg0) {
				System.out.println("----------------------");
				return driver.findElement(By.id("menu-teacher")).isDisplayed();
			}
		});

		driver.findElement(By.id("menu-teacher")).click();
		//		driver.findElement(By.cssSelector("a[data-href='teacher-list.html']")).click();

		//3:智能等待
		WebDriverWait wait2 = new WebDriverWait(driver, 5);
		//until直到xxx才
		wait2.until(new ExpectedCondition() {
			//ExpectedCondition:期望的条件
			public Object apply(Object arg0) {
				System.out.println("***************************");
				return driver.findElement(By.cssSelector("a[data-href='teacher-list.html']")).isDisplayed();
			}
		});

		driver.findElement(By.cssSelector("a[data-href='teacher-list.html']")).click();

		//直到期望的条件满足的时候，采取执行后续的代码-->智能
		//直到元素可见的时候，再进行后续操作
		//直到元素可点击的时候，再进行后续操作

		//NoSuchElementException:no such element: Unable to locate element: {"method":"id","selector":"menu-teacher"}
		//因为还在登录页面
		//ElementNotVisable-->不可见，能够找到这个元素，因为在dom结构中是存在
		
		//优点：
		//1：可以进行等待的封装--》代码段完成的等待
		//2：不会造成时间的浪费

		Thread.sleep(5000);
		driver.quit();
	}
	
	public static boolean waitElementDisplayed(WebDriver driver,long timeoutInSeconds,By by){
		WebDriverWait wait = new WebDriverWait(driver,3);
		//until直到xxx才
		return wait.until(new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver arg0) {
				return driver.findElement(by).isDisplayed();
			}
			
		});
		
	}

}
