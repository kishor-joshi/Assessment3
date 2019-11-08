package com.tutoralninja.utility;

import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;

import com.tutoralninja.helper.ElementHelper;
import com.tutoralninja.helper.PageManager;
import com.tutoralninja.helper.WaitHelperClass;


public class BaseClass  {
	
public 	ElementHelper helper=new ElementHelper();
public  Logger log=Logger.getLogger(BaseClass.class);
public PageManager pageManager=new PageManager();
public WaitHelperClass waitHelper=new WaitHelperClass();
public  WebDriver driver;
public Properties property;
public String currentURL;

/**
 * select driver class type
 * 
 * @param browserType
 * @throws IOException
 */	
	public void openBrowser(String browserType) throws IOException {
		
		//if mode of running is normal 
	if(Constants.modeOfRunning.equalsIgnoreCase("normal")) {
		
	
	switch (browserType) {
	
		case "chrome":
			ChromeOptions optionschrome = new ChromeOptions();
			optionschrome.addArguments("--disable-notifications");
			System.setProperty("webdriver.chrome.driver", Constants.chromeDriverPath);
			driver = new ChromeDriver(optionschrome);
			log.info("chrome browser launched");
			break;
			
		case "firefox":
			System.setProperty("webdriver.gecko.driver", Constants.fireFoxDriverPath);			
			driver = new FirefoxDriver();
			log.info("firefox browser launched");
			break;
			
		case "ie":			
			System.setProperty("webdriver.ie.driver", Constants.IEDriverPath);
			driver = new InternetExplorerDriver();
			log.info("IE browser launched");
			break;
	
		}
	}
	//through grid mode driver will launch.
	else {
		BaseClassForGrid.getDriver(browserType);
		log.info("driver is opened through grid");
	}
		driver.manage().window().maximize();
			
		
	}
	
	/**
	 * 
	 * @param configFilePath
	 * @throws IOException
	 */
	public void launchURL(String configFilePath) throws IOException {
		pageManager.manageTimeOuts(driver);
		property=pageManager.loadpropertyFile(configFilePath);
		log.info("URL is loading "+property.getProperty("url"));
		driver.get(property.getProperty("url"));	
		currentURL=driver.getCurrentUrl();
		Assert.assertEquals(currentURL, property.getProperty("url"));
		log.info("opened URL is currect");
		
	}
}
