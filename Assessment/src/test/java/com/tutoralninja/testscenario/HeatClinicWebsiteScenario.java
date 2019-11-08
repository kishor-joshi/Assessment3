package com.tutoralninja.testscenario;

import java.io.IOException;


import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.heatclinic.testscript.HeatClinicTestscript;
import com.tutoralninja.helper.ExtendReport;
import com.tutoralninja.utility.Constants;


public class HeatClinicWebsiteScenario {
public	HeatClinicTestscript script=new HeatClinicTestscript();
/**
 * this method sets the specified browser,taking driverType from sute files.
 * @param browserType
 * @throws IOException
 */
@Parameters({"browserType"})
@BeforeTest
public void setBrowser(String browserType) throws IOException {
	script.openBrowser(browserType);
	ExtendReport.reportLog("openBrowser", "failed");
}



/**
 * this method launches specified URL taking URL from specific config properties file.
 * @throws IOException
 */
@Test(priority = 1)
public void setURL() throws IOException {
	script.launchURL(Constants.heatClinicConfigPropertiesFilePath);
	ExtendReport.reportLog("launchURL", "failed");
}
@Test(priority = 2)
public void menuValidation() throws Exception {
	script.navigateToEachMenuAndValidate();
	script.validateContentInMensMerchandise();
	script.switchToProductWindowAndValidate();
	script.ValidateCartItems();
}



}
