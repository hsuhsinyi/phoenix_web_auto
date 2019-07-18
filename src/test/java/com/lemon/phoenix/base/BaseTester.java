package com.lemon.phoenix.base;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.lemon.phoenix.pojo.Locator;
import com.lemon.phoenix.util.PropertiesUtil;
import com.lemon.phoenix.util.UILibraryUtil;

/**
 * 测试父类   
 * 
 * 
 * 
 * @author tommy
 * @Date 2018年3月1日
 * @Desc
 * @Email 1754739303@qq.com
 */
public class BaseTester {

	private static Logger logger = Logger.getLogger(BaseTester.class);

	// 全部变量，对象的属性
	public static WebDriver driver = null;

	/**
	 * 如果ie浏览器测试--》 InternetExplorerDriver 如果chrome浏览器测试--》 ChromeDriver
	 * 如果firefox浏览器测试--》 FireFoxDriver 如果是2.x 的selenium版本,不用设置驱动文件 如果是3.x
	 * 的selenium版本，设置驱动文件
	 */
	@BeforeSuite
	@Parameters(value = { "browserType", "driverPath", "seleniumVersion" })
	public void beforeSuite(String browserType, String driverPath, String seleniumVersion) {
		// 局部变量
		// 将browserType字符串转换成小写：如 IE-->ie
		String browserTypeLowerCase = browserType.toLowerCase();

		logger.info("*****************************开始测试***************************");
		logger.info("初始化驱动，配置的浏览器类型:" + browserType + "，驱动路径:" + driverPath + "，selenium版本：" + seleniumVersion);

		// 如果是ie浏览器
		if (("ie").equals(browserTypeLowerCase)) {
			// 1:驱动文件找不到的异常
			System.setProperty("webdriver.ie.driver", driverPath);
			// 创建一个设置对象，用来设置创建ie驱动时的各种设置
			DesiredCapabilities capabilities = new DesiredCapabilities();
			// 2:取消IE安全设置（忽略IE的Protected Mode的设置），
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			// 3:忽略浏览器的页面缩放设置
			capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			// 4:设置一个初始化页面，防止window对象丢失
			capabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "http://www.baidu.com");
			// 创建一个ie驱动
			driver = new InternetExplorerDriver(capabilities);
			
			logger.info("创建IE驱动完成");
		}
		// 如果是要做谷歌浏览器的测试
		else if (("chrome").equals(browserTypeLowerCase)) {
			// 设置驱动
			System.setProperty("webdriver.chrome.driver", driverPath);
			// 创建一个谷歌的webdriver驱动对象
			driver = new ChromeDriver();
			logger.info("创建Chrome驱动完成");
		} else if (("firefox").equals(browserTypeLowerCase)) {
			// 火狐浏览器可执行文件的设置，因为不是安装在默认的路径下
			System.setProperty("webdriver.firefox.bin", "C:\\Program Files\\Mozilla Firefox\\firefox.exe");
			// 如果Selenium的版本，需要进行驱动文件设置
			if ("3.x".equals(seleniumVersion)) {
				System.setProperty("webdriver.gecko.driver", driverPath);
			}
			// 创建一个火狐的driver
			driver = new FirefoxDriver();
			logger.info("创建火狐驱动完成");
		}
	}

	@AfterSuite
	public void afterSuite() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
		logger.info("*********************************完成测试，关闭浏览器************************");
	}

	/**
	 * 定位单个元素
	 * 
	 * @param keyword
	 *            对元素的描述(关键字)
	 * @return
	 */
	public  WebElement getElement(String keyword) {
		return getElement(keyword, this.getClass());
	}
	
	/**
	 * 定位指定页面的某元素
	 * @param keyword
	 * @param className
	 * @return
	 */
	public WebElement getElement(String keyword,Class<?> clazz) {
		// 所有页面的页面描述
		HashMap<String, Locator> page = UILibraryUtil.pagesMap.get(clazz.getName());
		// 当前要找的元素的定位信息
		final Locator locator = page.get(keyword);
		final String byStr = locator.getBy();// id 、name、className...
		final String value = locator.getValue();// mobilephone、password...

		String tips = "开始获取元素【" + locator.getDesc() + "】：{'by','" + locator.getBy() + "'},{'value','"
				+ locator.getValue() + "'}";
		logger.info(tips);

		WebDriverWait wait = new WebDriverWait(driver, 30);
		

		WebElement element = wait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				// 类的细节：有什么方法、变量...
				Class<By> clazz = By.class;
				try {
					Method method = clazz.getMethod(byStr, String.class);
					By by = (By) method.invoke(null, value);
					return driver.findElement(by);
				} catch (NoSuchMethodException e) {
					logger.error("找不到对应方法，请检查你的xml配置");
				} catch (Exception e) {
					logger.error("获取元素" + locator.getDesc() + "【失败】");
				}
				return null;
			}
		});
		logger.info("获取元素" + locator.getDesc() + "【成功】");
		return element;
	}

	/**
	 * 找一个元素列表
	 * 
	 * @param keyword
	 * @return
	 */
	public List<WebElement> getElements(String keyword) {
		// 所有页面的页面描述
		HashMap<String, Locator> page = UILibraryUtil.pagesMap.get(this.getClass().getName());
	
		// 当前要找的元素的定位信息
		final Locator locator = page.get(keyword);
		final String byStr = locator.getBy();// id 、name、className...
		final String value = locator.getValue();// mobilephone、password...

		String tips = "开始获取元素列表【" + locator.getDesc() + "】：{'by','" + locator.getBy() + "'},{'value','"
				+ locator.getValue() + "'}";
		logger.info(tips);

		WebDriverWait wait = new WebDriverWait(driver, 30);

		List<WebElement> elements = wait.until(new ExpectedCondition<List<WebElement>>() {
			
			@Override
			public List<WebElement> apply(WebDriver driver) {
				// 类的细节：有什么方法、变量...
				Class<By> clazz = By.class;
				try {
					Method method = clazz.getMethod(byStr, String.class);
					By by = (By) method.invoke(null, value);
					return driver.findElements(by);
				} catch (NoSuchMethodException e) {
					logger.error("找不到对应方法，请检查你的xml配置");
				} catch (Exception e) {
					logger.error("获取元素" + locator.getDesc() + "【失败】");
				}
				return null;
			}
		});

		logger.info("获取元素" + locator.getDesc() + "【成功】");
		return elements;
	}

	/**
	 * //一个页面元素可以点击的必要条件<br>
	 * 1：页面上要有这个元素--》dom树存在该标签<br>
	 * 2: 元素一定要显示出来的<br>
	 * 3: 元素一定要是激活状态 ：disable<br>
	 * 
	 * @param timeOutInSeconds
	 * @param by
	 * @return
	 */
	public WebElement getElement2(long timeOutInSeconds, By by) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		return wait.until(ExpectedConditions.elementToBeClickable(by));

	}

	/**
	 * 到某个页面去
	 * 
	 * @param url
	 */
	public void to(String url) {
		String urlStr = PropertiesUtil.urlProperties.getProperty(url);
		// driver.get(url);
		logger.info("打开页面地址：" + urlStr);
		driver.navigate().to(urlStr);
	}

	/**
	 * 浏览器最大化
	 */
	public void maximize() {
		driver.manage().window().maximize();
	}

	/**
	 * 前进
	 */
	public void forward() {
		driver.navigate().forward();
	}

	/**
	 * 后退
	 */
	public void back() {
		driver.navigate().back();
	}

	/**
	 * 刷新
	 */
	public void refresh() {
		driver.navigate().refresh();
	}

	/**
	 * 点击
	 * 
	 * @param element
	 *            要点击元素
	 */
	public void click(WebElement element) {
		logger.info("点击元素");
		element.click();
	}

	public void click(String keyword) {
		logger.info("点击元素");
		getElement(keyword).click();
	}

	/**
	 * 输入内容
	 * 
	 * @param element
	 *            要输入内容的元素
	 * @param value
	 *            输入的内容
	 */
	public void sendkeys(WebElement element, String value) {
		element.sendKeys(value);
	}

	/**
	 * 
	 * @param keyword
	 * @param value
	 */
	public void sendkeys(String keyword, String value) {
		getElement(keyword).sendKeys(value);
		logger.info("往页面元素【" + keyword + "】" + "输入数据【" + value + "】");
	}

	public String getText(String keyword) {
		return getElement(keyword).getText();
	}
	
	/**
	 * 验证当前页面是否存在某元素
	 * 
	 * @param keyword
	 */
	public void assertElementExists(String keyword) {
		assertElementExistsInPage(keyword, this.getClass());
	}

	/**
	 * 验证某个页面是否存在某个元素
	 * 
	 * @param keyword
	 * @param clazz
	 */
	public void assertElementExistsInPage(String keyword, Class<?> clazz) {
		Assert.assertNotNull(getElement(keyword, clazz));
	}

}
