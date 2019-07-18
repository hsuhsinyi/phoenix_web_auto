package com.lemon.web.auto.listener;

import java.io.File;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import com.lemon.web.auto.util.PropertiesUtils;
import com.lemon.web.auto.util.SeleniumUtils;

/**
 * 这个报告是reportng生成--》jar包？？
 * 
 * @author happy
 * @date 2019年1月14日
 * @desc 
 * @email
 */
public class PhoenixListener extends TestListenerAdapter {
	
	@Override 
	public void onTestFailure(ITestResult tr) {
		super.onTestFailure(tr);
		//0:保存到任意不合理，现在设计保存到target/surefile-report/screenshot这个目录
		//1:期望的目录
		//E:\workspace\java2_workspace\phoenix_web_auto_8_2\target\surefire-reports\screenshot\register
		
		//2:获得套件的测试报告输出目录
		//E:\workspace\java2_workspace\phoenix_web_auto_8_2\target\surefire-reports\Suite
		String outputDirectory = tr.getTestContext().getOutputDirectory();
		
		//3:截取surefire-reports目录
		//E:\workspace\java2_workspace\phoenix_web_auto_8_2\target\surefire-reports
		String surefireDir = outputDirectory.substring(0, outputDirectory.lastIndexOf("\\"));
		
		//4:获得test的名称
		//register
		String testName = tr.getTestContext().getCurrentXmlTest().getName();
		
		//5:完整的保存截图的目录路径
		String screenshotPath = surefireDir + File.separator + "screenshot" + File.separator + testName;
		System.out.println(screenshotPath);
		//6：截图
		File file = SeleniumUtils.takeScreenshot(screenshotPath);
		//7:得到这个文件的信息：图片的路径、名称
		String absolutePath = file.getAbsolutePath();
		//E:\workspace\java2_workspace\phoenix_web_auto_8\target\surefire-reports\screenshot\login_test\1547445609965.jpg
		//把图片的web url写入到测试日志
		//http://localhost/screenshot/login_test/1547445609965.jpg
		//获得baseURL ： http://localhost/
		String baseUrl = PropertiesUtils.getConfig("baseURL");//TODO 请配置到属性文件中
		//把E:\workspace\java2_workspace\phoenix_web_auto_8\target\surefire-reports\	替换为 http://localhost/
		String oldStr = absolutePath.substring(0, absolutePath.indexOf("screenshot"));
		//替换
		String imageURL = absolutePath.replace(oldStr, baseUrl).replace("\\", "/");
		System.out.println(imageURL);
		
		//添加该测试用例的日志信息
		Reporter.log("<img src='"+imageURL+"' hight='100' width='100'><a href='"+imageURL+"' target='_blank'>点击查看大图</a></img>");

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
