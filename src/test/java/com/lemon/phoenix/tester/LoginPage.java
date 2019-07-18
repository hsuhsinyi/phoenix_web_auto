package com.lemon.phoenix.tester;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.lemon.phoenix.base.BaseTester;
import com.lemon.phoenix.util.ExcelUtil;
import com.lemon.phoenix.util.PropertiesUtil;

/**
 * 登录
 * @author tommy
 * @Date 2018年3月13日
 * @Desc 注册模块反向测试用例执行
 * @Email 1754739303@qq.com
 */
public class LoginPage extends BaseTester {
	//日志：记录此刻干了什么事
	private static Logger logger = Logger.getLogger(LoginPage.class);

	// 一个测试用例的执行
	@Test(dataProvider = "getSuccessDatas")
	public void registerSuccessTest001(String mobilePhone, String password, String pwdConfirm, String expectedTips)
			throws InterruptedException {
		// 打开页面
		to(PropertiesUtil.urlProperties.getProperty("registerURL"));
		// 输入手机号
		sendkeys("账户输入框", mobilePhone);
		// 输入密码
		sendkeys("密码输入框", password);
		// 输入重复密码
		sendkeys("重复密码输入框", pwdConfirm);
		// 点击注册
		click("注册按钮");
		
		getElement("登录按钮");

		/*// 真实的url地址
		String actulUrl = driver.getCurrentUrl();
		// 期望的url地址
		String expectedUrl = "http://39.108.136.60:8082/lmcanon_web_auto/mng/login.html";
		// 断言
		Assert.assertEquals(actulUrl, expectedUrl);*/
		
	}

	@Test(dataProvider = "getFailureDatas")
	public void registerFailureTest001(String mobilePhone, String password, String pwdConfirm, String expectedTips)
			throws InterruptedException {
		// 打开页面
		to(PropertiesUtil.urlProperties.getProperty("registerURL"));
		// 输入手机号
		sendkeys("账户输入框", mobilePhone);
		// 输入密码
		sendkeys("密码输入框", password);
		// 输入重复密码
		sendkeys("重复密码输入框", pwdConfirm);
		// 点击注册
		click("注册按钮");
		
		// 真实的得到提示内容
		String actualTips = getText("提示内容元素");
		// 断言
		Assert.assertEquals(actualTips, expectedTips);
	}

	@DataProvider
	public Object[][] getFailureDatas() {
		Object[][] datas = ExcelUtil.readExcel("/register.xlsx", 2, 2, 1, 4);
		return datas;
	}

	@DataProvider
	public Object[][] getSuccessDatas() {
		Object[][] datas = ExcelUtil.readExcel("/register.xlsx", 8, 8, 1, 4);
		return datas;
	}

}
