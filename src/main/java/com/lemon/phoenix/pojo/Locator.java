package com.lemon.phoenix.pojo;

import org.apache.log4j.lf5.viewer.TrackingAdjustmentListener;

public class Locator {
	private String locatorBy;
	private String locatorValue;
	private String locatorDesc;
	
	public String getLocatorBy() {
		return locatorBy;
	}
	public void setLocatorBy(String locatorBy) {
		this.locatorBy = locatorBy;
	}
	public String getLocatorValue() {
		return locatorValue;
	}
	public void setLocatorValue(String locatorValue) {
		this.locatorValue = locatorValue;
	}
	public String getLocatorDesc() {
		return locatorDesc;
	}
	public void setLocatorDesc(String locatorDesc) {
		this.locatorDesc = locatorDesc;
	}
	public Locator(String locatorBy, String locatorValue, String locatorDesc) {
		super();
		this.locatorBy = locatorBy;
		this.locatorValue = locatorValue;
		this.locatorDesc = locatorDesc;
	}
	@Override
	public String toString() {
		return "Locator [locatorBy=" + locatorBy + ", locatorValue=" + locatorValue + ", locatorDesc=" + locatorDesc
				+ "]";
	}

	
	
}
