package com.lemon.auto.web.day03;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

public class LocateElement {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, "src/main/resources/driver/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		
		driver.get("http://www.baidu.com");
		//定位元素、查找元素
		//查找单个元素
//		driver.findElement(by)
		//查找多个元素
//		driver.findElements(by)
		//通过静态方法来封装一个定位元素的方式（途径）
		//1:id -->identity-->唯一标识符 (类似于通过身份证号码)
//		WebElement input = driver.findElement(By.id("happy"));
		
		//2:name
//		WebElement input =driver.findElement(By.name("wd11"));
		
		//3:tagName -->标签名（用的频率非常低）
//		WebElement input =driver.findElement(By.tagName("input"));
//		String value = input.getAttribute("maxlength");
//		System.out.println(value);
		//如果以标签名，一般都是查找列表--》爬虫
//		List<WebElement> inputs =driver.findElements(By.tagName("input"));
//		System.out.println(inputs.size());
		
		//4:linkText-->超链接完整文本
//		WebElement element1 = driver.findElement(By.linkText("hao123"));
//		element1.click();//单击元素--》点击了鼠标的左键
		
		//完整文本定位可能存在的坑：
//		driver.get("http://120.78.128.25:8765/Index/login.html");
//		driver.findElement(By.linkText("免费注册")).click();
//		driver.findElement(By.partialLinkText("免费注册")).click();
		
		//5:partialLinkText -》超链接部分文本
//		WebElement element2 = driver.findElement(By.partialLinkText("hao123"));
//		WebElement element2 = driver.findElement(By.partialLinkText("o123"));
//		element2.click();
		
		
		//6：className --》元素class属性中间其中一个样式
//		WebElement input = driver.findElement(By.className("s_ipt"));
//		input.sendKeys("柠檬班");
		//Compound class names not permitted: 不能使用符合的类名
//		driver.findElement(By.className("bg s_btn btnhover")).click();
//		driver.findElement(By.className("s_btn")).click();
		
		//7:cssSelector (重要！！！必须掌握)-->标签名[属性名=属性值][属性2名=属性2值][xxx=xxx]
		//A  地区=湖南   籍贯=衡阳
		//B  地区=湖南   籍贯=长沙
		//<input id="kw" name="wd" class="s_ipt" value="" maxlength="255" autocomplete="off">
//		driver.findElement(By.cssSelector("input[maxlength=\"255\"]")).sendKeys("柠檬班软件测试");
//		driver.findElement(By.cssSelector("input[maxlength='255']")).sendKeys("柠檬班软件测试");
		driver.findElement(By.cssSelector("input[maxlength='255'][ autocomplete='off']")).sendKeys("柠檬班软件测试");
		
		
		Thread.sleep(2000);
		driver.quit();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
