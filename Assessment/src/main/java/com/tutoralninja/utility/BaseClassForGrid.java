package com.tutoralninja.utility;

import java.net.MalformedURLException;


import java.net.URL;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class BaseClassForGrid {


private static DesiredCapabilities getBrowserCapabilities(String browserType) {
switch (browserType) {

case "firefox":
DesiredCapabilities descapability= new DesiredCapabilities();
descapability.setCapability(CapabilityType.BROWSER_NAME, "firefox");
return descapability;


case "chrome":
 descapability= new DesiredCapabilities();
descapability.setCapability(CapabilityType.BROWSER_NAME, "chrome");
return descapability;


default:
 descapability= new DesiredCapabilities();
descapability.setCapability(CapabilityType.BROWSER_NAME, "internet explorer");
return descapability;

}
}
public static RemoteWebDriver getDriver(String browser) throws MalformedURLException {
	System.out.println("grid running");
return new RemoteWebDriver(new URL(Constants.gridNodeURL), getBrowserCapabilities(browser));
}
}
