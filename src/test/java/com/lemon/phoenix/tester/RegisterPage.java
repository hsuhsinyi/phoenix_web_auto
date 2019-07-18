package com.lemon.phoenix.tester;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.lemon.phoenix.base.BaseTester;
import com.lemon.phoenix.util.AssertUtil;
import com.lemon.phoenix.util.ExcelUtil;

/**
 * 
 * @author tommy
 * @Date 2018年3月13日
 * @Desc 注册模块反向测试用例执行
 * @Email 1754739303@qq.com
 */
public class RegisterPage extends BaseTester {
	//日志：记录此刻干了什么事
	private static Logger logger = Logger.getLogger(RegisterPage.class);

	// 一个测试用例的执行
	@Test(dataProvider = "getSuccessDatas", enabled=false)
	public void registerSuccessTest001(String mobilePhone, String password, String pwdConfirm, String expectedTips)
			throws InterruptedException {
		// 打开页面
		to("registerURL");
		// 输入手机号
		sendkeys("账户输入框", mobilePhone);
		// 输入密码
		sendkeys("密码输入框", password);
		// 输入重复密码
		sendkeys("重复密码输入框", pwdConfirm);
		// 点击注册
		click("注册按钮");
		//验证某个页面存在某个元素
		assertElementExistsInPage("登录按钮", LoginPage.class);
		
	}

	@Test(dataProvider = "getFailureDatas")
	public void registerFailureTest001(String mobilePhone, String password, String pwdConfirm, String expectedTips)
			throws InterruptedException {
		// 打开页面
		to("registerURL");
		// 输入手机号
		sendkeys("账户输入框", mobilePhone);
		// 输入密码
		sendkeys("密码输入框", password);
		// 输入重复密码
		sendkeys("重复密码输入框", pwdConfirm);
		// 点击注册
		click("注册按钮");
		
		
		//验证某个元素是否包含某个文本
		AssertUtil.assertTextPresent(getElement("提示内容元素"), expectedTips);
		//自己尝试
//		AssertUtil.assertTextPresent("提示内容元素", this.getClass(), expectedTips);
	}

	@DataProvider
	public Object[][] getFailureDatas() {
		Object[][] datas = ExcelUtil.readExcel("/register.xlsx", 2, 7, 1, 4);
		return datas;
	}

	@DataProvider
	public Object[][] getSuccessDatas() {
		Object[][] datas = ExcelUtil.readExcel("/register.xlsx", 8, 8, 1, 4);
		return datas;
	}

}
