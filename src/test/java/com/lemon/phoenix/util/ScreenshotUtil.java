package com.lemon.phoenix.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.lemon.phoenix.base.BaseTester;

/**
 * 截屏的工具类
 * 
 * @author tommy
 * @Date 2018年3月27日
 * @Desc
 * @Email 1754739303@qq.com
 */
public class ScreenshotUtil {

	private static Logger logger = Logger.getLogger(ScreenshotUtil.class);
	/**
	 * 截屏的方法
	 * 
	 * @param screenshotDir
	 *            截图要保存的位置
	 */
	public static File takeScreenshot(String screenshotDir) {
		//每个测试用例执行失败的的话，保存的截屏文件名应该是唯一的？？
		//1522159546442.jpg
		Date now = new Date();
		String fileName = now.getTime() + ".jpg";
		File destFile = new File(screenshotDir + File.separator + fileName);
		File sourceFile = null;
		// 截图需要webdriver的子类
		WebDriver driver = BaseTester.driver;
		// WebDriver是没有这个方法
		// 我怎么知道现在这个driver是哪个子类：IE 、Chrome、Firefox
		// 如果driver是IEDriver类型-->driver是InternetExplorerDriver的一个实例
		if (driver instanceof InternetExplorerDriver) {
			InternetExplorerDriver ieDirver = (InternetExplorerDriver) driver;
			logger.info("IE开始进行截图");
			sourceFile = ieDirver.getScreenshotAs(OutputType.FILE);
		} else if (driver instanceof FirefoxDriver) {
			FirefoxDriver firefoxDriver = (FirefoxDriver) driver;
			logger.info("Firefox开始进行截图");
			sourceFile = firefoxDriver.getScreenshotAs(OutputType.FILE);
		} else if (driver instanceof ChromeDriver) {
			ChromeDriver chromeDriver = (ChromeDriver) driver;
			logger.info("Chrome开始进行截图");
			sourceFile = chromeDriver.getScreenshotAs(OutputType.FILE);
		}
		
		try {
			// 拷贝到screenshotDir
			// 图片--》jpg--》给一个目标文件的对象
			logger.info("拷贝截图文件到surefire目录下");
			FileUtils.copyFile(sourceFile, destFile);
		} catch (IOException e) {
			logger.error("截图失败");
			e.printStackTrace();
		}
		
		return destFile;
	}
	
	public static void main(String[] args) {
		Date now = new Date();
		System.out.println(now.getTime());//1522159532177
		//1522159538925
		//1522159546442
	}

}
