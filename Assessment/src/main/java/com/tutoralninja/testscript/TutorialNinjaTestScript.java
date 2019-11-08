package com.tutoralninja.testscript;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.tutoralninja.helper.ExcelReader;
import com.tutoralninja.utility.BaseClass;
import com.tutoralninja.utility.Constants;
import com.tutoralninja.utility.ValidationHelper;

public class TutorialNinjaTestScript extends BaseClass {
	WebElement addToCartElement,productLink,inputElement,cartElement;
	String actualProductName,actualQuenty,actualPrice,actualExTax,actualTotalPrice,avalability;
public	String cartProductList[][];



public void SearchProductAndAddToCart(String expectedProductName,String expectedQuenty,String expectedPrice,String expectedExTax) throws Exception {
	pageManager.manageTimeOuts(driver);
	property=pageManager.loadpropertyFile(Constants.tutorialNinjaPropertiesFilePath);
	inputElement=helper.getElement(driver, property, "searchinput");
	inputElement.clear();
	inputElement.sendKeys(expectedProductName);	
	log.info(" searched product name: " +expectedProductName);
	helper.getElement(driver, property, "searchButton").click();
	pageManager.scrollAndViewAtTop(driver, helper.getElement(driver, property, "reference"));
	helper.getElement(driver, property, "clickonProduct").click();
	actualPrice=helper.getElement(driver, property, "priceofproduct").getText();
	actualExTax=helper.getElement(driver, property, "taxOfproduct").getText();
	avalability=helper.getElement(driver, property, "avalability").getText();
	
	//System.out.println(actualPrice+actualExTax+avalability);
	ValidationHelper.validatePrice(actualPrice, expectedPrice);
	log.info("unit price is validated:passed ");
	helper.getElement(driver, property, "quentityinput").clear();
	helper.getElement(driver, property, "quentityinput").sendKeys(expectedQuenty);
	addToCartElement=helper.getElement(driver, property, "addToCartButton");	
	addToCartElement.click();
	log.info(expectedProductName+" product added to the cart");
}




public void validateCartItems() throws Exception {
	pageManager.manageTimeOuts(driver);
	property=pageManager.loadpropertyFile(Constants.tutorialNinjaPropertiesFilePath);
	addToCartElement=waitHelper.fluentWaitHelper(driver, "cart", "staleelementexception", property);
	pageManager.movieToPerticularElement(driver, addToCartElement);
	
	addToCartElement.click();
	cartProductList=ExcelReader.getUserData(Constants.cartProductListExcelPath);
	Thread.sleep(Constants.sleepingwait);
	//cartElement=waitHelper.fluentWaitHelper(driver, "viewcartbutton", "staleelementexception", property);
	cartElement=waitHelper.explictWaitHelper(driver,"viewcartbutton", "clickable", property);
	//pageManager.movieToPerticularElement(driver, cartElement);	
	cartElement.click();
	actualProductName=helper.getElement(driver, property, "cartproductname1").getText();
	actualPrice=helper.getElement(driver, property, "cartproductname1").getText();	
	actualTotalPrice=(helper.getElement(driver, property, "totalprice1")).getText();
	
	//2nd product validation in the cart.	
	ValidationHelper.validate(actualProductName, cartProductList[1][0]);
	log.info("product addrd to the cart is correct "+actualProductName);	
	ValidationHelper.validatePrice(actualTotalPrice, cartProductList[1][3]);	
	log.info("total price in the cart is correct "+actualTotalPrice);
	log.info("1st product validation passed "+actualProductName);
	
	
	//2nd product validation in the cart.			
	actualProductName=helper.getElement(driver, property, "cartproductname2").getText();
	actualPrice=helper.getElement(driver, property, "cartproductname2").getText();	
	actualTotalPrice=(helper.getElement(driver, property, "totalprice2")).getText();
	ValidationHelper.validate(actualProductName, cartProductList[2][0]);
	log.info("product addrd to the cart is correct "+actualProductName);	
	
	ValidationHelper.validatePrice(actualTotalPrice, cartProductList[2][3]);	
	log.info("total price in the cart is correct "+actualTotalPrice);
	log.info("2nd product validation in the cart passed "+actualProductName);
}


public void ValidateCartAfterRemvingIntem() throws Exception {
	pageManager.manageTimeOuts(driver);
	property=pageManager.loadpropertyFile(Constants.tutorialNinjaPropertiesFilePath);
	//removing  2nd item from cart
	helper.getElement(driver, property, "removebutton").click();
	log.info("product is removed from the cart");
	actualProductName=helper.getElement(driver, property, "cartproductname1").getText();
	actualTotalPrice=(helper.getElement(driver, property, "totalprice1")).getText();
	cartProductList=ExcelReader.getUserData(Constants.cartProductListExcelPath);	
	ValidationHelper.validate(actualProductName, cartProductList[1][0]);
	log.info("product addrd to the cart is correct "+actualProductName);
	ValidationHelper.validatePrice(actualTotalPrice, cartProductList[1][3]);	
	log.info("total price in the cart is correct "+actualTotalPrice);
	log.info("product validation passed after removed one product from the cart");
}

}
