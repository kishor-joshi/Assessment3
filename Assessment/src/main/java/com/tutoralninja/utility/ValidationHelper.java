package com.tutoralninja.utility;

import org.testng.Assert;

public class ValidationHelper {
public static void validate(String actual,String expected) {
	String actualText=actual.replace("$", "");
	if(!actualText.equalsIgnoreCase(expected))
		Assert.assertTrue(false,"text not matched");
}
public static void validatePrice(String actualprice,String expectedPrice) {
	String actualpriceText=actualprice.replace(",", "");
	if(!actualpriceText.contains(expectedPrice))
		Assert.assertTrue(false,"price not matched");
}
public static void varifyProduct(String actual,String expected) {
	String actualText=actual.toUpperCase();
	String expectedText=expected.toUpperCase();
	if(!actualText.contains(expectedText))
		Assert.assertTrue(false,"text not matched");
		
}
}
