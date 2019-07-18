package com.lemon.phoenix.pojo;

import java.util.List;

public class Page {
	private String activityName;
	private String pageDesc;
	// locator元素的集合
	private List<Locator> locators;

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getPageDesc() {
		return pageDesc;
	}

	public void setPageDesc(String pageDesc) {
		this.pageDesc = pageDesc;
	}

	public List<Locator> getLocators() {
		return locators;
	}

	public void setLocators(List<Locator> locators) {
		this.locators = locators;
	}

	public Page(String activityName, String pageDesc, List<Locator> locators) {
		super();
		this.activityName = activityName;
		this.pageDesc = pageDesc;
		this.locators = locators;
	}

	@Override
	public String toString() {
		return "Page [activityName=" + activityName + ", pageDesc=" + pageDesc + ", locators=" + locators + "]";
	}

}
