package com.lemon.phoenix.listener;

import java.io.File;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import com.lemon.phoenix.util.DateUtil;
import com.lemon.phoenix.util.ScreenshotUtil;

/**
 * 用例执行失败的监听类
 * @author tommy
 * @Date 2018年3月31日
 * @Desc
 * @Email 1754739303@qq.com
 */
public class TestFailureListener extends TestListenerAdapter {

	@Override
	public void onTestFailure(ITestResult tr) {
		super.onTestFailure(tr);
		// 思路:当测试用例执行失败的时候，就使用driver的api进行截图
		// 保存到surefire-reports/screeshot下
		//
		// 1：通过test的上下文得到surefire-reports这个根目录
		// E:\java2_workspace\phoenix_web_auto\target\surefire-reports\Suite
		// output的目录可能有多个（surefire-reports\套件名称）
		String outputDir = tr.getTestContext().getOutputDirectory();

		// 2:截取出surefire-reports根目录：E:\java2_workspace\phoenix_web_auto\target\surefire-reports
		String surefireDir = outputDir.substring(0, outputDir.lastIndexOf("\\"));

		// 3:一个套件是有很多test，我们希望不同的test失败的截图保存到相应目录下
		// 从上下文拿到test的name
		String testName = tr.getTestContext().getCurrentXmlTest().getName();
		//年月日
		String dateStr = DateUtil.getTodayYMDString();
		// 4:截图保存的目录的组装：E:\java2_workspace\phoenix_web_auto\target\surefire-reports\screenshot\register
		String screenshotDir = surefireDir + File.separator + "screenshot" + File.separator + testName + File.separator
				+ dateStr;

		// 5：截图的功能--》可能有一大堆代码
		File screenshotFile = ScreenshotUtil.takeScreenshot(screenshotDir);
		String absolutePath = screenshotFile.getAbsolutePath();
		
		//文件系统中间的路径
		//E:\java2_workspace\phoenix_web_auto\target\surefire-reports\screenshot\regsiterTester\20180329\1522325969185.jpg
		//截取和拼接的思路：从screenshot
		//url：
		// http://localhost:8887/screenshot/regsiterTester/20180329/1522325969185.jpg
		// 文件的路径：
		// <img>
		String oldStr = absolutePath.substring(0, absolutePath.indexOf("screenshot"));
		String newStr = "http://localhost:8887/";//硬编码
		String urlTemp = absolutePath.replace(oldStr, newStr);
		//http://localhost:8887\screenshot\regsiterTester\20180329\1522325969185.jpg
		String imgUrl = urlTemp.replace("\\", "/");
		//http://localhost:8887/screenshot/regsiterT ester/20180329/1522326881985.jpg
		//图片的url已经有了--》现在要嵌入到reportNG的报表
		
		//ReportNG的监听器监听到testng的一个测试结果，拿到结果中间的各种信息
		//通过一定的手段把信息放到自己的日志中间--》取出日志渲染报表
		Reporter.log("<img src='"+imgUrl+"' hight='100' width='100'><a href='"+imgUrl+"' target='_blank'>查看大图</a></img>");
	}
}
