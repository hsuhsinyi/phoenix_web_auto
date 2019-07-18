package com.lemon.auto.web.day02.section02;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriver.SystemProperty;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

public class SeleniumUtils {

	private static final String IE_BROWSER_NAME = "ie";

	private static final String FIREFOX_BROWSER_NAME = "firefox";

	private static final String CHROME_BROWSER_NAME = "chrome";

	private static final String EDGE_BROWSER_NAME = "edge";

	private static final String SAFARI_BROWSER_NAME = "safari";

	private static final String SELENIUM_VERSION_3_X = "3.x";

	private static final String SELENIUM_VERSION_2_X = "2.x";

	/**
	 * 根据浏览器类型，获得对应驱动对象，默认是采用2.x的selenium
	 * @param broswerType
	 * @param driverExePath
	 * @return
	 */
	public static WebDriver getWebDriver(String broswerType, String driverExePath) {
		return getWebDriver(broswerType, SELENIUM_VERSION_2_X, driverExePath);
	}

	/**
	 * 根据浏览器类型，获得对应驱动对象
	 * @param broswerType
	 * @return
	 */
	public static WebDriver getWebDriver(String broswerType, String seleniumVersion, String driverExePath) {
		if (IE_BROWSER_NAME.equalsIgnoreCase(broswerType)) {
			return getIEDriver(driverExePath);
		} else if (FIREFOX_BROWSER_NAME.equalsIgnoreCase(broswerType)) {
			return getFireFoxDriver(seleniumVersion, driverExePath);
		} else if (CHROME_BROWSER_NAME.equalsIgnoreCase(broswerType)) {
			return getChromeDriver(driverExePath);
		} else if (EDGE_BROWSER_NAME.equalsIgnoreCase(broswerType)) {
			return getEdgeDriver(driverExePath);
		} else if (SAFARI_BROWSER_NAME.equalsIgnoreCase(broswerType)) {
			return getSafariDriver();
		} else {
			//抛异常，没有符合的驱动
			return null;
		}
	}

	private static WebDriver getSafariDriver() {
		//TODO
		return new SafariDriver();
	}

	/**
	 * 获得edge的驱动对象
	 * @param driverExePath
	 * @return
	 */
	private static WebDriver getEdgeDriver(String driverExePath) {
		System.setProperty(EdgeDriverService.EDGE_DRIVER_EXE_PROPERTY, driverExePath);
		return new EdgeDriver();
	}

	/**
	 * 获得Chrome的驱动对象
	 * @param driverExePath
	 * @return
	 */
	private static WebDriver getChromeDriver(String driverExePath) {
		System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, driverExePath);
		return new ChromeDriver();
	}

	/**
	 * 获得firefox的驱动对象
	 * @param seleniumVersion
	 * @param driverExePath
	 * @return
	 */
	private static WebDriver getFireFoxDriver(String seleniumVersion, String driverExePath) {
		//3.x的版本需要设置驱动路径
		if (SELENIUM_VERSION_3_X.equals(seleniumVersion)) {
			System.setProperty(GeckoDriverService.GECKO_DRIVER_EXE_PROPERTY, driverExePath);
		}
		System.setProperty(SystemProperty.BROWSER_BINARY, "D:\\Program Files\\Mozilla Firefox\\firefox.exe");
		return new FirefoxDriver();
	}

	/**
	 * 获得ie的驱动对象
	 * @param driverExePath
	 * @return
	 */
	private static WebDriver getIEDriver(String driverExePath) {
		System.setProperty(InternetExplorerDriverService.IE_DRIVER_EXE_PROPERTY, driverExePath);
		//在创建驱动的时候，增加一些能力
		DesiredCapabilities capabilities = new DesiredCapabilities();
		//忽略安全域的设置
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		//忽略浏览器缩放
		capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		return new InternetExplorerDriver(capabilities);
	}

}
