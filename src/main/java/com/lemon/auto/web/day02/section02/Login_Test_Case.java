package com.lemon.auto.web.day02.section02;

import org.testng.annotations.Test;

public class Login_Test_Case extends BaseTester {

	@Test
	public void test_case_1() throws InterruptedException {
//		getDriver().get("http://www.baidu.com");
		to("http://www.baidu.com");
	}

	@Test
	public void test_case_2() throws InterruptedException {
//		getDriver().get("http://www.lemfix.com");
		to("http://www.lemfix.com");
	}

	@Test
	public void test_case_3() throws InterruptedException {
//		getDriver().get("http://www.lemonban.com");
		to("http://www.lemonban.com");
	}

}
