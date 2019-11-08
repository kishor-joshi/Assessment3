package com.tutoralninja.utility;

import java.io.File;
/**
 * this class having  filepath and constants. 
 * @author kishor.joshi
 *
 */

public class Constants {
public static final String projectFilePath=System.getProperty("user.dir")+File.separator;
public static final String libFolderPath=projectFilePath+"libs"+File.separator;
public static final String chromeDriverPath=libFolderPath+"chromedriver.exe";
public static final String fireFoxDriverPath=libFolderPath+"geckodriver.exe";
public static final String IEDriverPath=libFolderPath+"IEDriverServer.exe";
public static final String extendReportFolderPath=projectFilePath+"extendReport"+File.separator;
public static final String extendReportFile=extendReportFolderPath+"extendReport.html";
public static final int    waitingTime=10;
public static final int    explictWaitTime=15;
public static final int    loadingTime=10;
public static final int    implicitTime=10;
public static final long   mxaximumFluentWait=20;
public static final long   pollingWait=3;
public static final long   sleepingwait=2000;
public static final String modeOfRunning="normal";
public static final String gridNodeURL="http://55.55.52.108:2000/wd/hub";
public static final String testResourcePath=projectFilePath+"src"+File.separator+"test"+File.separator+"resources"+File.separator;
public static final String locatorFilePath=testResourcePath+"locators"+File.separator;
public static final String testDataFilePath=testResourcePath+"testdata"+File.separator;
public static final String configFolderFilePath=testResourcePath+"configFolder"+File.separator;
public static final String atmecsConfigPropertiesFilePath=configFolderFilePath+"atmecsConfig.properties";
public static final String tutorialNinjaConfigPropertiesFilePath=configFolderFilePath+"ninjaConfig.properties";
public static final String heatClinicConfigPropertiesFilePath=configFolderFilePath+"heatClincConfig.properties";
public static final String locatorsPropertiesFilePath=locatorFilePath+"locators.properties";
public static final String tutorialNinjaPropertiesFilePath=locatorFilePath+"tutorialningalocators.properties";
public static final String heatClinicPropertiesFilePath=locatorFilePath+"heatclinic.properties";
public static final String detailsExcelPath=testDataFilePath+"Details.xlsx";//
public static final String classNameExcelPath=testDataFilePath+"className.xlsx";
public static final String productDetailsExcelPath=testDataFilePath+"productDetails.xlsx";
public static final String cartProductListExcelPath=testDataFilePath+"productListincart.xlsx";
public static final String menuContentExcelPath=testDataFilePath+"menuContent.xlsx";
public static final String dressDetailsPath=testDataFilePath+"DressDetails.properties";

}
