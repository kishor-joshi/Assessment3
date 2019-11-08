package com.heatclinic.testscript;

import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.tutoralninja.helper.ExcelReader;
import com.tutoralninja.utility.BaseClass;
import com.tutoralninja.utility.Constants;
import com.tutoralninja.utility.ValidationHelper;

public class HeatClinicTestscript extends BaseClass{
	String menuText,actualcontentText,expectedcontentText,parentWindowHandler,subWindowHandler,
	actualColour,actualSize,actualProductname,actualperonalizeName,actualTotalPrice,unitPrice;
	WebElement menuElement,menElement,cartElement;
	String[][] menuContent;
	Set<String> handles ;
	Iterator<String> iterator;
	Properties userProperties;
	
	
	
public void navigateToEachMenuAndValidate() throws Exception {
	menuContent=ExcelReader.getUserData(Constants.menuContentExcelPath);
	pageManager.manageTimeOuts(driver);
	property=pageManager.loadpropertyFile(Constants.heatClinicPropertiesFilePath);
	
	for(int index=1;index<7;index++) {
		menuElement=helper.getElement(driver, property, "eachmenu", "###", index+"");
		menuText=menuElement.getText();
		log.info("clicking on "+menuText);
		menuElement.click();
		log.info("navigated to: "+menuText);
		String locator=menuContent[index][1];
		
		actualcontentText=helper.getElement(driver, locator).getText();
		expectedcontentText=menuContent[index][2];
		Assert.assertEquals(actualcontentText, expectedcontentText);
		log.info("content validated in: "+menuText+" content is: "+actualcontentText);
		
	}
}

public void validateContentInMensMerchandise() throws Exception {
	pageManager.manageTimeOuts(driver);
	menuContent=ExcelReader.getUserData(Constants.menuContentExcelPath);
	property=pageManager.loadpropertyFile(Constants.heatClinicPropertiesFilePath);
	menuElement=helper.getElement(driver, property, "MerchandiseLink");
	pageManager.movieToPerticularElement(driver, menuElement);
	menElement=helper.getElement(driver, property, "menLink");
	pageManager.movieToPerticularElement(driver, menElement);//
	menElement.click();
	actualcontentText=helper.getElement(driver, property,"menContent").getText();
	expectedcontentText=property.getProperty("expectedcontentinMenLink");
	Assert.assertEquals(actualcontentText, expectedcontentText);
	log.info("content validated in:Mens. content is: "+actualcontentText);
	helper.getElement(driver, property, "buynow_button").click();
	
}
public void switchToProductWindowAndValidate() throws Exception {
	pageManager.manageTimeOuts(driver);
	property=pageManager.loadpropertyFile(Constants.heatClinicPropertiesFilePath);
	userProperties=pageManager.loadpropertyFile(Constants.dressDetailsPath);
	parentWindowHandler = driver.getWindowHandle(); // Store your parent window
	subWindowHandler = null;
	
	handles = driver.getWindowHandles(); // get all window handles		
	iterator = handles.iterator();
	while (iterator.hasNext()){
	    subWindowHandler = iterator.next();
	    helper.getElement(driver, property, "colourLink").click();
	    log.info("colour selected");
	    helper.getElement(driver, property, "size_link").click();
	    log.info("size selected");
	    helper.getElement(driver, property, "personalName_input").sendKeys(userProperties.getProperty("PersonalizedName"));
	    log.info("name sent");
	    helper.getElement(driver, property, "buynowbutton").click();
	    log.info("added to cart");
	    
	}
	driver.switchTo().window(subWindowHandler); // switch to popup window

	// Now you are in the popup window, perform necessary actions here

	driver.switchTo().window(parentWindowHandler);  // switch back to parent window 
	waitHelper.explictWaitHelper(driver, "cartLink", "clickable", property).click();
	log.info("navigated to cart");
}

public void ValidateCartItems() throws Exception {
	pageManager.manageTimeOuts(driver);
	property=pageManager.loadpropertyFile(Constants.heatClinicPropertiesFilePath);
	userProperties=pageManager.loadpropertyFile(Constants.dressDetailsPath);
	
	Thread.sleep(2000);
	 
	
	   actualSize=  helper.getElement(driver, property, "sizeincart").getText();
	  
	   actualperonalizeName= helper.getElement(driver, property, "personalnameincart").getText();
	   actualProductname= helper.getElement(driver, property, "productnameincart").getText();
	   actualColour= helper.getElement(driver, property, "colorinpart").getText();
	   ValidationHelper.varifyProduct(actualSize,userProperties.getProperty("ShirtSize") );
	   log.info("size validated:passed "+actualSize);
	   ValidationHelper.varifyProduct(actualperonalizeName,userProperties.getProperty("PersonalizedName") );
	   log.info("perosnalName validated:passed "+actualperonalizeName);
	   ValidationHelper.varifyProduct(actualProductname,userProperties.getProperty("ITEMName") );
	   log.info("Item Name validated:passed "+actualProductname);
	   ValidationHelper.varifyProduct(actualColour,userProperties.getProperty("ShirtColor") );
	   log.info("color validated:passed "+actualColour);
	   helper.getElement(driver, property, "quentyinput").clear();//
		 helper.getElement(driver, property, "quentyinput").sendKeys(userProperties.getProperty("increaseQuenty"));
		 helper.getElement(driver, property, "update").click();	   
		 log.info("quenty updated");
		 actualTotalPrice= helper.getElement(driver, property, "totalprice").getText();
		 ValidationHelper.varifyProduct(actualTotalPrice,userProperties.getProperty("TotalPrice") );
		 log.info("total price validated:passed "+actualTotalPrice);
		 unitPrice=helper.getElement(driver, property, "price").getText();
		 ValidationHelper.varifyProduct(unitPrice,userProperties.getProperty("Price") );
		 log.info("unit price validated:passed "+unitPrice);
	  
	 
	
}

}
