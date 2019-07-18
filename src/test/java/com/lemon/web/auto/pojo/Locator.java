package com.lemon.web.auto.pojo;

/**
 * web自动化中一个网页元素的定位描述
 * @author happy
 * @date 2019年1月4日
 * @desc 
 * @email
 */
public class Locator {
	//元素的名称：描述信息
	private String name;
	//定位的方式
	private String type;
	//定位的值
	private String value;
	
	private String clickable;
	
	private String displayed;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Locator(String name, String type, String value) {
		super();
		this.name = name;
		this.type = type;
		this.value = value;
	}
	
	public Locator(String name, String type, String value, String clickable, String displayed) {
		super();
		this.name = name;
		this.type = type;
		this.value = value;
		this.clickable = clickable;
		this.displayed = displayed;
	}

	public String getClickable() {
		return clickable;
	}

	public void setClickable(String clickable) {
		this.clickable = clickable;
	}

	public String getDisplayed() {
		return displayed;
	}

	public void setDisplayed(String displayed) {
		this.displayed = displayed;
	}

	@Override
	public String toString() {
		return "Locator [name=" + name + ", type=" + type + ", value=" + value + ", clickable=" + clickable
				+ ", displayed=" + displayed + "]";
	}
}
