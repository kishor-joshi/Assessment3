package com.tutoralninja.helper;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import com.tutoralninja.utility.Constants;



/**
 * 
 * @author kishor.joshi
 *
 */

public class DataProviderClass {
	
	@DataProvider(name="userData")
	public  String[][] getdata() throws IOException {

	   return ExcelReader.getUserData(Constants.detailsExcelPath);
	}
	
	
	@DataProvider(name="className")
	public  String[][] getClassName() throws IOException {

	   return ExcelReader.getUserData(Constants.classNameExcelPath);
	}
	
	@DataProvider(name="productDetails")
	public  String[][] getProductDetails() throws IOException {

	   return ExcelReader.getUserData(Constants.productDetailsExcelPath);
	}
}
