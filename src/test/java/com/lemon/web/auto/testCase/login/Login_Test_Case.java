package com.lemon.web.auto.testCase.login;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.lemon.web.auto.base.BaseTester;
import com.lemon.web.auto.util.SeleniumUtils;

/**
 * Login_Success_Test_Case
 * Login_Failure_Test_Case
 * 测试类命名是有规则的
 * 反向测试用例
 * @author happy
 * @date 2019年1月3日
 * @desc 
 * @email
 */
public class Login_Test_Case extends BaseTester {

	public static void main(String[] args) {
		System.out.println(Login_Test_Case.class.getSimpleName());
	}
	
	@DataProvider
	public Object[][] getData(){
		Object[][] data = {
				{"","","用户名不能为空"},
				{"lemon","","非法的手机号"},
				{"13888876666","","密码不能为空"},
				{"13888876666","12345","账号信息错误1"},
				{"13888876666","123456","账号信息错误1"}
		};
		return data;
	}
	
	@Test(dataProvider="getData")
	public void test_case_1(String mobilePhone,String password,String expected){
		to("login_url");
		type("手机号输入框", mobilePhone);
		type("密码输入框", password);
		click("登录按钮");
//		String actual = getText("提示信息元素");
//		Assert.assertEquals(actual, expected);
		assertTextPresent("提示信息元素", expected);
	}
}
