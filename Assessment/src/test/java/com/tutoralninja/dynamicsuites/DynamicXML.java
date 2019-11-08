package com.tutoralninja.dynamicsuites;


import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.tutoralninja.helper.ExcelReader;
import com.tutoralninja.utility.Constants;


	
public class DynamicXML {
		
		
 //This method will create an Xml file based on the XmlSuite data 
 public void createXmlFile(XmlSuite mSuite) 
  { 
   FileWriter writer; 
 try { 
	 writer = new FileWriter(new File("dynamicSuite.xml")); 
     writer.write(mSuite.toXml()); 
     writer.flush(); 
     writer.close(); 
	 
	  } catch (IOException e)
	 {
	 e.printStackTrace(); 
	  }
  }




@Test 
public void runSuites() throws IOException {	
	
	 
	
	runDynamicSuite();
				
}
public void runDynamicSuite() throws IOException {
	String[][] classNameArray=ExcelReader.getUserData(Constants.classNameExcelPath);
	String className,browserName;
	//Create an instance on TestNG 
    TestNG myTestNG = new TestNG(); 
   
   //Map<String,String> testngParams
	
    //Create an instance of XML Suite and assign a name for it. 
     XmlSuite mySuite = new XmlSuite(); 
   
     mySuite.setName("TestSuite"); 
     List<XmlTest> myTests = new ArrayList<XmlTest>(); 
     mySuite.setParallel(XmlSuite.ParallelMode.TESTS); 
    // mySuite.setParallel("tests");
     for(int index=0;index<classNameArray.length;index++) {
    	 className=classNameArray[index][0];
    	 browserName=classNameArray[index][1];
    		Map<String, String> browser = new HashMap<String, String>();
    		browser.put("browserType", browserName);
    	    //Create an instance of XmlTest and assign a name for it.  
    	    XmlTest myTest = new XmlTest(mySuite); 
    	   
    	    myTest.setName(browserName); 
    	 
    	 
    	    //Add any parameters that you want to set to the Test. 
    	    myTest.setParameters(browser); 
    	   
    	    
    	   // myTest.setParameters(""); 
    	  
    	    //Create a list which can contain the classes that you want to run.
    	    List<XmlClass> myClasses = new ArrayList<XmlClass>();
    	    myClasses.add(new XmlClass(className));
    	

    	    //Assign that to the XmlTest Object created earlier. 
    	    myTest.setXmlClasses(myClasses);   
    	    

    	    //Create a list of XmlTests and add the Xmltest you created earlier to it.
    	  
    	    myTests.add(myTest);  
    	}

    	    //add the list of tests to your Suite. 
    	    mySuite.setTests(myTests);   

    	    //Add the suite to the list of suites. 
    	    List<XmlSuite> mySuites = new ArrayList<XmlSuite>(); 
    	    mySuites.add(mySuite);   
    	    
    	    //Set the list of Suites to the testNG object you created earlier. 
    	    myTestNG.setXmlSuites(mySuites);
    	    
    	    
    	    mySuite.setFileName("dynamicXML.xml"); 
    	    mySuite.setThreadCount(3);   
    	    myTestNG.run();
    	   

    	    //Create physical XML file based on the virtual XML content 
    	    for(XmlSuite suite : mySuites) 
    	    {  
    	        createXmlFile(suite); 
    	    }   
    	    System.out.println("File generated successfully.");   

    	   }
	


	}

