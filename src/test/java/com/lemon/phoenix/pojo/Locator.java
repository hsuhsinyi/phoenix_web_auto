package com.lemon.phoenix.pojo;

/**
 * 页面的一个元素的描述
 * 
 * 
 * 
 * @author tommy
 * @Date 2018年3月20日
 * @Desc
 * @Email 1754739303@qq.com
 */
public class Locator {
	// driver怎么找到这个元素？？

	// 1:通过哪个方式去找：cssSeletor、xpath、id、name：by（"value"）
	private String by;

	// 2:通过什么值定位到这个元素：mobilephone、pwd、。。。
	private String value;

	// 3:简单的描述:用户名输入框、密码输入框、确认密码输入框...
	private String desc;

	public String getBy() {
		return by;
	}

	public void setBy(String by) {
		this.by = by;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "Locator [by=" + by + ", value=" + value + ", desc=" + desc + "]";
	}

	public Locator(String by, String value, String desc) {
		super();
		this.by = by;
		this.value = value;
		this.desc = desc;
	}

}
