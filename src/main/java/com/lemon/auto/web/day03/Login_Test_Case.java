package com.lemon.auto.web.day03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.lemon.auto.web.day02.section02.BaseTester;

public class Login_Test_Case extends BaseTester {

	@Test
	public void f1() {
		//到登录页面
		to("http://120.78.128.25:8765/Index/login.html");
		//定位到登录按钮
		WebElement btn = getDriver().findElement(By.cssSelector("button[class='btn btn-special']"));
		//点击登录按钮
		btn.click();
		//找到提示错误信息的元素
		WebElement tipsElement = getDriver().findElement(By.xpath("//form[@name='login-form']/div/div"));
		//获得tipsElement的文本
		String tips = tipsElement.getText();
		System.out.println(tips);
		//期望出现的文本
		String expected = "请输入手机号";
		//断言
		Assert.assertEquals(tips, expected);
		
	}

}
