package com.lemon.phoenix.tester;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.ClickAction;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lemon.phoenix.base.BaseOperation;
import com.lemon.phoenix.util.ExcelUtil;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.functions.ExpectedCondition;
import io.appium.java_client.touch.offset.PointOption;

public class LoginTest extends BaseOperation{
	
	/**
	 * 测试主页的流程
	 * @throws InterruptedException 
	 */
	@Test(priority=1)
	public void testMain() throws InterruptedException{
		// 点击我的柠檬
		click("主页页面","我的柠檬");
		// 点击头像登录
		click("主页页面","头像登录");
	}

	/**
	 * 测试登录页面的流程
	 * @param mobilephone
	 * @param pwd
	 * @param toastTips
	 * @throws InterruptedException
	 */
	@Test(dataProvider="getLoginDatas",priority=2)
	public void testLogin(String mobilephone, String pwd, String toastTips) throws InterruptedException {
		// 输入手机号码
		sendKeys("登录页面","手机号码输入框",mobilephone);
		// 输入密码
		sendKeys("登录页面","密码输入框",pwd);
		// 登录按钮 com.lemon.lemonban:id/btn_login
		click("登录页面","登录按钮");
		//解决第二条测试用例找不到我的柠檬元素问题
		//在每一条测试用例运行完毕之后就跳转到主页
		//最优的解决方式：只需要进入到登录页面一次即可
//		Thread.sleep(2000);
//		Activity activity = new Activity("com.lemon.lemonban", ".activity.MainActivity");
//		androidDriver.startActivity(activity);
	}
	
	@DataProvider
	public Object[][] getLoginDatas(){
		Object[][] datas = ExcelUtil.readExcel("/lemon_testcase.xlsx", 2, 6, 1, 3);
		return datas;
	}

}
