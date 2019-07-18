package com.lemon.web.auto.testCase.register;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.lemon.web.auto.base.BaseTester;
import com.lemon.web.auto.util.ExcelUtils;

/**
 * 反向测试用例
 * @author happy
 * @date 2019年1月3日
 * @desc 
 * @email
 */
public class Register_Test_Case extends BaseTester {

	private static final Logger logger = Logger.getLogger(Register_Test_Case.class);

	@DataProvider
	public Object[][] getData() {
		/*Object[][] data = { { "", "", "", "用户名不能为空" },
				{ "lemon", "", "", "非法的手机号" },
				{ "13888876666", "", "", "密码不能为空" }, 
				{ "13888876666", "12345", "", "密码长度至少为6位" },
				{ "13888876666", "123456", "", "重复密码不能为空" }, 
				{ "13888876666", "123456", "1234567", "密码不一致" },
				{ "13888876666", "中文的的密码", "中文的的密码", "密码不一致" }};*/
		return ExcelUtils.readExcel("/testCase/register/register.xlsx", 1);
	}

	@DataProvider
	public Object[][] getSuccussData() {
		Object[][] data = { { "13566666677", "123456", "123456" }, 
				{ "13566666678", "123456", "123456" },
				{ "13566666679", "abcefg", "abcefg" } };
		return data;
	}

	/**
	 * 正向测试用例
	 * 痛点：元素定位的方式、定位的值可能会发生变化
	 * @param mobilePhone
	 * @param password
	 * @param pwdConfirm
	 * @param expected
	 * @throws InterruptedException 
	 */
	@Test(dataProvider = "getSuccussData", enabled = false)
	public void test_case_1(String mobilePhone, String password, String pwdConfirm) throws InterruptedException {
		//窗口最大化
		maximize();
		//打开注册页面
		to("register_url");
		//往手机号输入框输入手机号
		type("手机号输入框", mobilePhone);
		//往密码输入框输入密码
		type("密码输入框", password);
		//往重复密码输入框输入密码
		type("重复密码输入框", pwdConfirm);
		//输入万能验证码
		type("验证码输入框", "LM19");
		//点击注册
		click("注册按钮");
		//注册成功--》调整登录页面
		//判断是否到了登录页面：1:获得此时的url  2：判断页面出现了登录按钮
		//		Assert.assertNotNull(getElement("登录按钮")); // 在注册页面找登录按钮是找不到的
		Thread.sleep(2000);
//		Assert.assertNotNull(getElementInPage(Login_Test_Case.class, "登录按钮"));
	}

	/**
	 * 反向测试用例
	 * 痛点：元素定位的方式、定位的值可能会发生变化
	 * @param mobilePhone
	 * @param password
	 * @param pwdConfirm
	 * @param expected
	 * @throws InterruptedException 
	 */
	@Test(dataProvider = "getData")
	public void test_case_2(String mobilePhone, String password, String pwdConfirm, String expected)
			throws InterruptedException {
		//窗口最大化
		maximize();
		//打开注册页面
		to("register_url");
		//往手机号输入框输入手机号
		type("手机号输入框", mobilePhone);
		//往密码输入框输入密码
		type("密码输入框", password);
		//往重复密码输入框输入密码
		type("重复密码输入框", pwdConfirm);
		//输入万能验证码
		type("验证码输入框", "LM19");
		//点击注册
		click("注册按钮");
		//断言提示信息元素文本值为期望的文本
		assertTextPresent("提示信息元素", expected);
	}

}
