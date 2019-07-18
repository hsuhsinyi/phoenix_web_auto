package com.lemon.phoenix.base;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.ClickAction;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.lemon.phoenix.pojo.Locator;
import com.lemon.phoenix.pojo.Page;
import com.lemon.phoenix.util.XmlUtil;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.functions.ExpectedCondition;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.offset.PointOption;

public class BaseOperation {
	// 初始化日志对象
	private Logger logger = Logger.getLogger(BaseOperation.class);
	// 驱动对象
	public AndroidDriver<WebElement> androidDriver;
	
	@BeforeSuite
	public void waitForAppium() throws InterruptedException{
		Thread.sleep(30000);
	}

	@BeforeTest
	@Parameters({ "udid", "platformName", "appPackage", "appActivity", "appiumPort","uiautomator2Port" })
	public void setUp(String udid, String platformName, String appPackage, String appActivity, String appiumPort,String uiautomator2Port)
			throws MalformedURLException {
		logger.info("==========================测试开始=========================");
		// 1、创建一个配置对象DesiredCapabilities
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		//deviceName传给Appium，Appium不会通过deviceName去设置测试设备是一哪台。deviceName参数必须要有
		//大坑
		desiredCapabilities.setCapability("deviceName", udid);
		//udid就是你要测试对应的那台设备（真正Appium会进行设置）
		desiredCapabilities.setCapability("udid", udid);
		desiredCapabilities.setCapability("appPackage", appPackage);
		desiredCapabilities.setCapability("appActivity", appActivity);
		desiredCapabilities.setCapability("platformName", platformName);
		// 指定automationName为UIAutomator2支持toast获取
		desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
		//如果有用到uiautomator2配置，设置端口
		desiredCapabilities.setCapability("systemPort", Integer.parseInt(uiautomator2Port));
		// 初始化驱动对象:1、appium通讯地址 2、创建初始化的配置
		androidDriver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:"+appiumPort+"/wd/hub"), desiredCapabilities);
		logger.info("当前的配置为:" + androidDriver.getCapabilities());
		
	}

	/**
	 * 智能等待的二次封装
	 */
	public WebElement getElement(String pageKeyword, String locatorKeyWord) {
		String locatorBy = "";
		String locatorValue = "";
		for (Page page : XmlUtil.listPages) {
			String pageDesc = page.getPageDesc();
			if (pageDesc.equals(pageKeyword)) {
				List<Locator> listLocator = page.getLocators();
				for (Locator locator : listLocator) {
					String locatorDesc = locator.getLocatorDesc();
					if (locatorDesc.equals(locatorKeyWord)) {
						// 找到对应的元素之后才把by、value获取得到
						locatorBy = locator.getLocatorBy();
						locatorValue = locator.getLocatorValue();
					}
				}
			}
		}
		// 再重新声明final类型给他赋值
		final String by = locatorBy;
		final String value = locatorValue;

		// 1、初始化webDriverWait对象
		WebDriverWait webDriverWait = new WebDriverWait(androidDriver, 8);
		// 2、使用webDriverWait的util方法
		WebElement webElement = null;
		try {
			webElement = webDriverWait.until(new ExpectedCondition<WebElement>() {

				public WebElement apply(WebDriver input) {
					// TODO Auto-generated method stub
					try {
						// 需要通过by、value去初始化by对象,应用到反射技术
						// 1、得到类的字节码
						Class<By> clazz = By.class;
						// 2、根据字节码得到对应执行方法
						Method method = clazz.getMethod(by, String.class);
						// 3、执行method，返回对应的by对象
						By by = (By) method.invoke(null, value);
						return androidDriver.findElement(by);
					} catch (Exception e) {
						// logger.error("元素找不到！！！");
					}
					return null;
				}
			});
		} catch (Exception e) {
			logger.error("【" + value + "】元素找不到");
		}
		return webElement;
	}

	/**
	 * 点击的二次封装
	 * 
	 * @param pageKeyWord
	 * @param locatorKeyWord
	 */
	public void click(String pageKeyWord, String locatorKeyWord) {
		// 做日志
		logger.info("在【" + pageKeyWord + "】点击【" + locatorKeyWord + "】");
		// 调用智能等待
		getElement(pageKeyWord, locatorKeyWord).click();
	}

	public void sendKeys(String pageKeyword, String locatorKeyWord, String inputData) {
		// 做日志
		logger.info("在【" + pageKeyword + "】的元素【" + locatorKeyWord + "】输入数据【" + inputData + "】");
		getElement(pageKeyword, locatorKeyWord).sendKeys(inputData);
	}

	@AfterTest
	public void tearDown() {
		// 测试用例结束的时候退出驱动对象
		logger.info("==========================测试结束=====================");
		androidDriver.quit();
	}

	/**
	 * 自定义的向下滑动
	 */
	public void swipeDown() {
		// 坐标要通过代码的方式来得到，并且要通用
		// 1、得到屏幕分辨率，x轴最大长度,y轴的最大长度
		int xWitdth = androidDriver.manage().window().getSize().width;
		int yHeigth = androidDriver.manage().window().getSize().height;

		// 起始点坐标
		int startx = xWitdth / 2;
		int starty = yHeigth / 4;

		// 终止点坐标
		int endx = xWitdth / 2;
		int endy = yHeigth * 3 / 4;
		// 需要将int类型的时间转换成Duration类型
		Duration duration = Duration.ofMillis(300);
		// 初始化touchAction对象
		TouchAction touchAction = new TouchAction(androidDriver);
		// java-client 6.1.0的滑动API有变化，坐标的表示需要传入PointOption对象
		touchAction.longPress(PointOption.point(startx, starty)).moveTo(PointOption.point(endx, endy)).release();
		touchAction.perform();
	}
}
