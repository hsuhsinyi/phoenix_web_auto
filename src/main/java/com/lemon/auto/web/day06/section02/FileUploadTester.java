package com.lemon.auto.web.day06.section02;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
/**
 * 如果一个验证码你能够识别出来，那么此时你可以去提改进的bug？？
 * 1:自动识别（不要去做）
 * 2:屏蔽验证码--》适合在测试网站上进行操作（生产、预生产）
 * 3:万能验证码--》后门（前程贷的万能验证码：hapi）
 * @author happy
 * @date 2018年12月28日
 * @desc 
 * @email
 */
public class FileUploadTester {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
				"src/main/resources/driver/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.get("file:///E:/svn-lemon/%E6%95%99%E5%AD%A6ppt/happy/java%E8%87%AA%E5%8A%A8%E5%8C%96/04-web%E8%87%AA%E5%8A%A8%E5%8C%96/%E7%89%B9%E6%AE%8A%E5%85%83%E7%B4%A0%E5%AE%9A%E4%BD%8D%E5%92%8C%E6%93%8D%E4%BD%9C%E9%9D%99%E6%80%81%E9%A1%B5%E9%9D%A2/%E6%96%87%E4%BB%B6%E4%B8%8A%E4%BC%A0/fileupload.html");
		
		///‪C:\Users\happy\Desktop\]}}D{$D(YY]F9SSB~PO9P_Q.png
		driver.findElement(By.id("fu")).sendKeys("C:\\Users\\happy\\Desktop\\]}}D{$D(YY]F9SSB~PO9P_Q.png");
		//前程贷的万能验证码：hapi
		Thread.sleep(5000);
		driver.quit();
	}

}
