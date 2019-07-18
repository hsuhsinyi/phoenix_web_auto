package com.lemon.phoenix.pojo;

import java.util.List;

public class Config {
	private String platformName;
	private String appPackage;
	private String appActivity;
	private List<String> classNames;
	public String getPlatformName() {
		return platformName;
	}
	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}
	public String getAppPackage() {
		return appPackage;
	}
	public void setAppPackage(String appPackage) {
		this.appPackage = appPackage;
	}
	public String getAppActivity() {
		return appActivity;
	}
	public void setAppActivity(String appActivity) {
		this.appActivity = appActivity;
	}
	public List<String> getClassNames() {
		return classNames;
	}
	public void setClassNames(List<String> classNames) {
		this.classNames = classNames;
	}
	public Config(String platformName, String appPackage, String appActivity, List<String> classNames) {
		super();
		this.platformName = platformName;
		this.appPackage = appPackage;
		this.appActivity = appActivity;
		this.classNames = classNames;
	}
	@Override
	public String toString() {
		return "Config [platformName=" + platformName + ", appPackage=" + appPackage + ", appActivity=" + appActivity
				+ ", classNames=" + classNames + "]";
	}

}
