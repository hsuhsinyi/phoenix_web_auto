package com.lemon.web.auto.base;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.lemon.web.auto.pojo.Locator;
import com.lemon.web.auto.util.PagesUtils;
import com.lemon.web.auto.util.PropertiesUtils;
import com.lemon.web.auto.util.SeleniumUtils;

/**
 * 测试类的基类
 * 通用的方法--》模板设计模式
 * @author happy
 * @date 2019年1月9日
 * @desc 
 * @email
 */
public abstract class BaseTester {
	
	private static Logger logger = Logger.getLogger(BaseTester.class);

	private static WebDriver driver;
	
	public static WebDriver getDriver() {
		return driver;
	}

	@BeforeSuite
	@Parameters(value = { "broswerType", "seleniumVersion", "driverExePath" })
	public void beforeSuite(String broswerType, String seleniumVersion, String driverExePath) {
		/**
		 * 初始化的工作
		 */
		driver = SeleniumUtils.getWebDriver(broswerType, seleniumVersion, driverExePath);
	}

	@AfterSuite
	public void afterSuite() throws InterruptedException {
		/**
		 * 收尾的工作
		 */
		Thread.sleep(2000);
		driver.quit();
	}

	/**
	 * 打开某个网址
	 * @param url 网址
	 */
	public void to(String urlKey) {
		driver.get(PropertiesUtils.getUrl(urlKey));
//		driver.navigate().to(PropertiesUtils.getUrl(urlKey));
	}

	/**
	 * 刷新
	 */
	public void refresh(){
		driver.navigate().refresh();
	}
	
	/**
	 * 回退
	 */
	public void back(){
		driver.navigate().back();
	}
	
	/**
	 * 前进
	 */
	public void forward(){
		driver.navigate().forward();
	}
	
	/**
	 * 窗口最大化
	 */
	public void maximize(){
		logger.info("进行窗口最大化");
		driver.manage().window().maximize();
	}
	
	/**
	 * 查找元素的方法
	 * @param type : id name className xpath...
	 * @param value :具体的定位值：phone
	 * @return
	 * 定位当前页面的元素 （特殊的情况--》指定的页面是当前的页面）
	 * 定位指定页面的元素
	 */
	public WebElement getElement(String keyword) {
		//要知道一个页面某元素的定位信息，只要知道：1:页面的名称  2：元素的名称
		//获得子类的类名--》页面的名称--》通过页面名称可以找到对应页面的所有的定位信息
		//1：获得当前页面名称（指定的页面名称）
		return getElementInPage(this.getClass(), keyword);
	}
	
	/**
	 * 查找指定页面元素
	 * 	/*if ("id".equals(type)) {
			by = By.id(value);
		} else if ("name".equals(type)) {
			by = By.name(value);
		} else if ("className".equals(type)) {
			by = By.className(value);
		} else if ("cssSelector".equals(type)) {
			by = By.cssSelector(value);
		} else if ("linkText".equals(type)) {
			by = By.linkText(value);
		} else if ("partialLinkText".equals(type)) {
			by = By.partialLinkText(value);
		} else if ("tagName".equals(type)) {
			by = By.tagName(value);
		} else if ("xpath".equals(type)) {
			by = By.xpath(value);
		} else {
			//抛异常：不存在这样的定位方式
		}
	 * @param pageClazz 指定页面对应的类的字节码
	 * @param keyword 元素关键描述信息
	 * @return 定位到的元素
	 */
	public WebElement getElementInPage(Class pageClazz,String keyword) {
		return getElementInPage(pageClazz, keyword, 5);
	}
	
	public WebElement getElementInPage(Class pageClazz,String keyword, long timeInSecond) {
		//要知道一个页面某元素的定位信息，只要知道：1:页面的名称  2：元素的名称
				//获得子类的类名--》页面的名称--》通过页面名称可以找到对应页面的所有的定位信息
				//1：获得页面名称
				String pageName = pageClazz.getSimpleName();
				Locator locator = PagesUtils.getLocatorByPageNameAndElementName(pageName, keyword);
				//最后调用到这实例方法的是哪个类型的对象
				//定位的方式
				String type = locator.getType();
				//定位的值
				String value = locator.getValue();
				//是否要等待可点击
				String clickable = locator.getClickable();
				//是否要等待显示
				String displayed = locator.getDisplayed();
				
				logger.info("以["+type+"] 方式,值为["+ value+ "] 定位元素[" + keyword +" ]");
				//延时等待的处理
				//jdk 1.5 ：内部类访问外部类的局部变量，这个局部变量要是final修饰
				WebElement element = null;
				try {
					Class byClazz = By.class;
					Method byMethod = byClazz.getMethod(type, String.class);
					By by = (By) byMethod.invoke(null, value);
					if (displayed != null) {
						ExpectedConditions.visibilityOfElementLocated(by);
						element =  driver.findElement(by);
					}else if(clickable != null){
						ExpectedConditions.elementToBeClickable(by);
						element= driver.findElement(by);
					}else{
						//智能等待的方法
						WebDriverWait wait = new WebDriverWait(driver, timeInSecond);
						 element = wait.until(new ExpectedCondition<WebElement>() {
							@Override
							public WebElement apply(WebDriver input) {
								return driver.findElement(by);
							}
						});
					}
				} catch (Exception e) {
					//TODO 日志
					logger.info("定位信息出错：以["+type+"] 方式,值为["+ value+ "] 定位元素[" + keyword +" ]");
				}
				return element;
	}
	
	/**
	 * 定位元素列表
	 * 默认等待时间为5秒
	 * @param pageClazz 测试类名（页面的标识：登录页面）
	 * @param keyword
	 * @return
	 */
	public List<WebElement> getElementsInPage(Class pageClazz,String keyword) {
		return getElementsInPage(pageClazz, keyword, 5);
	}
	
	/**
	 * 定位元素列表
	 * @param pageClazz 测试类名（页面的标识：登录页面）
	 * @param keyword 关键字
	 * @param timeInSecond 超时的秒数
	 * @return
	 */
	public List<WebElement> getElementsInPage(Class pageClazz,String keyword,int timeInSecond) {
		//1：获得页面名称
		String pageName = pageClazz.getSimpleName();
		Locator locator = PagesUtils.getLocatorByPageNameAndElementName(pageName, keyword);
		//定位的方式
		String type = locator.getType();
		//定位的值
		String value = locator.getValue();
		//是否要等待可点击
		String clickable = locator.getClickable();
		//是否要等待显示
		String displayed = locator.getDisplayed();
		
		List<WebElement> elementList = null;
		//延时等待的查找
		try {
			Class byClazz = By.class;
			Method byMethod = byClazz.getMethod(type, String.class);
			By by = (By) byMethod.invoke(null, value);
			if (displayed != null) {
				ExpectedConditions.visibilityOfElementLocated(by);
				elementList =  driver.findElements(by);
			}else if(clickable != null){
				ExpectedConditions.elementToBeClickable(by);
				elementList= driver.findElements(by);
			}else{
				//智能等待的方法
				WebDriverWait wait = new WebDriverWait(driver, 5);
				elementList = wait.until(new ExpectedCondition<List<WebElement>>() {
					@Override
					public List<WebElement> apply(WebDriver input) {
						return driver.findElements(by);
					}
					
				});
			}
		} catch (Exception e) {
			//TODO 日志
		}
		return elementList;
	}
	
	/**
	 * 当前页面输入文本
	 * @param by 定位元素方式对象
	 * @param content 要输入的内容
	 */
	public void type(String keyword, String content) {
		typeInPage(this.getClass(), keyword, content);
	}
	
	/**
	 * 指定页面元素输入文本
	 * @param by 定位元素方式对象
	 * @param content 要输入的内容
	 */
	public void typeInPage(Class<? extends BaseTester> clazz ,String keyword, String content) {
		logger.info("往[ " + keyword + " ]输入内容:[" + content+"]");
		getElementInPage(clazz,keyword).sendKeys(content);
	}

	/**
	 * 点击当前页面的元素
	 * @param by定位元素方式对象
	 */
	public void click(String keyword) {
		clickInPage(this.getClass(), keyword);
	}
	
	/**
	 * 点击指定页面的元素
	 * @param by定位元素方式对象
	 */
	public void clickInPage(Class<? extends BaseTester> clazz ,String keyword) {
		logger.info("点击元素[ " + keyword + " ]");
		getElementInPage(clazz,keyword).click();
	}

	/**
	 * 获得当前页面元素的文本
	 * @param by 定位元素方式对象
	 * @return
	 */
	public String getText(String keyword) {
		return getTextInPage(this.getClass(), keyword);
	}
	
	/**
	 * 获得指定页面元素的文本
	 * @param by 定位元素方式对象
	 * @return
	 */
	public String getTextInPage(Class<? extends BaseTester> clazz,String keyword) {
		String text = getElementInPage(clazz,keyword).getText();
		logger.info("获取[ " + keyword + " ] 的文本信息：[" + text +" ]");
		return text;
	}
	
	//统一的检查点技术
//	assertTextPresent：断言页面元素文本值为某文本
	/**
	 * 断言页面元素文本值为某文本
	 * @param keyword 元素的关键信息：关键字
	 * @param expectedText 期望的文本
	 */
	public void assertTextPresent(String keyword,String expectedText){
		String actualText = getText(keyword);
		Assert.assertEquals(actualText, expectedText);
	}
	
	public void assertTextPresent(Class<? extends BaseTester> clazz,String keyword,String expectedText){
		String actualText = getText(keyword);
		Assert.assertEquals(actualText, expectedText);
	}
	
//	assertPartialTextPresent：断言页面元素文本值包含某文本
	public void assertPartialTextPresent(String keyword,String expectedText){
		Assert.assertTrue(getText(keyword).contains(expectedText));
	}
//	assertElementEditable：断言某元素可编辑
	public void assertElementEditable(String keyword){
		Assert.assertTrue(getElement(keyword).isEnabled());
	}
//	assertElementNotEditable：断言某元素不可编辑
	public void assertElementNotEditable(String keyword){
		Assert.assertFalse(getElement(keyword).isEnabled());
	}
//	assertURLContains：断言当前URL包含
	public void assertURLContains(String partialUrl){
		Assert.assertTrue(driver.getCurrentUrl().contains(partialUrl));
	}
//	assertTextNotPresent
//	assertTextNotPresentPrecesion
//	assertElementAttributeValueEquals
//	assertElementAttributeValueNotEquals
//	assertAlertTextContains
//	...

	
}
