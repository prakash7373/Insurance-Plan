package testCases;

import java.util.Hashtable;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseClasses.BaseClass;
import baseClasses.BaseUI;
import policybazaar.pageClasses.CarInsurancePage;
import policybazaar.pageClasses.PolicyBazaarHomePage;
import policybazaar.pageClasses.TravelInsurancePage;
import utilities.ReadPropertiesFile;
import utilities.TestDataProvider;

public class PolicyBazaarTestCases extends BaseClass{
	PolicyBazaarHomePage policybazaarhomepage;
	TravelInsurancePage travelinsurancepage;
	CarInsurancePage carinsurancepage;
	
	
	@Test(priority=0,groups="Smoke Test")
	public void openWebSite() throws Exception {
	logger = report.createTest("Display Lowest Travel Plan Quotes(Policy Bazaar)");
	invokeBrowser();//opening of the browser
	BaseUI pageBaseUI = new BaseUI(driver,logger);
	PageFactory.initElements(driver, pageBaseUI);//To initialize web elements
	policybazaarhomepage = pageBaseUI.openWebsite();
	}
	
	/******************Function calls for TravelInsurance page********************/
	@Test(dataProvider = "getThreeLowestTravelQuotes",priority=1,groups={"Regression Test","Smoke Test"})
	public void displayLowestTravelPlans(Hashtable<String, String> testData) throws Exception {
		travelinsurancepage = policybazaarhomepage.clickTravelInsurance();
		travelinsurancepage.clickStudentCategory();
		travelinsurancepage.desCountry.sendKeys(testData.get("Country"));
		travelinsurancepage.desCountry.sendKeys(Keys.ENTER);
		travelinsurancepage.age1.sendKeys(testData.get("Age 1"));
		travelinsurancepage.age2.sendKeys(testData.get("Age 2"));
		setWait(1);
		travelinsurancepage.selectStartDate(testData.get("Start Date"));
		travelinsurancepage.selectEndDate(testData.get("End Date"));
		travelinsurancepage.clickProceed();
		travelinsurancepage.fillName(testData.get("Traveller Name"));
		travelinsurancepage.fillMobile(testData.get("Traveller Phone"));
		travelinsurancepage.fillEmail(testData.get("Traveller Mail ID"));
		travelinsurancepage.clickGetQuotes();
		travelinsurancepage.getThreeLowTravelPlans();
		driver.navigate().to(ReadPropertiesFile.getUrl());		
	}
	
	/********************Function calls for carInsurance page***********************************/
	@Test(dataProvider = "getErrorDetailsTestData",priority=2,groups={"Regression Test","Smoke Test"})
	public void getErrorForInvalidDetails(Hashtable<String, String> testData) throws Exception {
					
		BaseUI pageBaseUI = new BaseUI(driver,logger);
		carinsurancepage = policybazaarhomepage.clickCarInsurance();
		carinsurancepage.proceedWithoutCarNumber();
		
		carinsurancepage.searchCity(testData.get("RTO & City Name"));
		carinsurancepage.searchBrand(testData.get("Car Name"));
		carinsurancepage.typeFuel(testData.get("Fuel Variant"));
		carinsurancepage.selectVariant(testData.get("Model Variant"));
		carinsurancepage.carYear();
		carinsurancepage.cName(testData.get("Owner Name"));
		carinsurancepage.eEmail(testData.get("Owner Email"));
		carinsurancepage.ePnumber("Owner Phone");
		carinsurancepage.proceedClick();
		pageBaseUI.takeScreenShotOnFailure();
		driver.navigate().to(ReadPropertiesFile.getUrl());
	}
	
	/*****************To retrieve the items in Health insurance****************************/
	@Test(priority=3,groups={"Regression Test","Smoke Test"})
	public void HealthInsuranceMenuList() throws Exception {
		BaseUI pageBaseUI = new BaseUI(driver,logger);
		policybazaarhomepage.HealthInsuranceList();
		pageBaseUI.ClosePolicyBazzarPage();
		pageBaseUI.quitPolicyBazzarPage();
	}
	
	
	@DataProvider
	public Object[][] getErrorDetailsTestData(){
		return TestDataProvider.getTestData("Policy Bazaar Tests.xlsx", "CarInsuranceData", "getErrorForInvalidDetails");
	}
	
	@DataProvider()
	public Object[][] getThreeLowestTravelQuotes(){
		return TestDataProvider.getTestData("Policy Bazaar Tests.xlsx", "Travel Plan Quotes", "displayLowestTravelPlans");
	}
}
