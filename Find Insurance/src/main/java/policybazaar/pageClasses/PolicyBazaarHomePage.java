package policybazaar.pageClasses;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import baseClasses.BaseUI;

public class PolicyBazaarHomePage extends BaseUI{
	
	public PolicyBazaarHomePage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);//To call superclass constructor
	}
	
	/********* To find the web elements for Insurance products *********/
	@FindBy(xpath="/html/body/cclink/div[4]/div[1]/div/ul/li[2]/a")
	public WebElement insuranceProducts;
	
	@FindBy(xpath="/html/body/cclink/div[4]/div[1]/div/ul/li[2]/div/div/div[4]/ul/li[1]/a/span")
	public WebElement travelInsurance;
	
	@FindBy(xpath = "/html/body/cclink/div[4]/div[1]/div/ul/li[2]/div/div/div[3]/h3/a")
	public WebElement carInsurance;
	
	@FindBy(xpath="/html/body/cclink/div[4]/div[1]/div/ul/li[2]/div/div/div[2]/h3/a")
	public WebElement healthInsurance;
	
	@FindBy(xpath="/html/body/cclink/div[4]/div[1]/div/ul/li[2]/div/div/div[2]/ul")
	public List<WebElement> healthInsuranceList;
	
	
	public TravelInsurancePage clickTravelInsurance(){
		try {
			setWait(1);
			Actions action = new Actions(driver);
			action.moveToElement(insuranceProducts);//To move cursor on to the element Insurance products
			action.build().perform();
			action.moveToElement(travelInsurance);//To move cursor on to the element Travel Insurance
			logger.log(Status.INFO, "Clicking the Travel Insurance Link from the Other Insurance List");
			travelInsurance.click();//To click on Travel Insurance option
			logger.log(Status.PASS, "Clicked the Travel Insurance Link");
		}catch(Exception e) {
			reportFail(e.getMessage());
		}
		
		/*******To initialize Travel insurance web elements*********/
		TravelInsurancePage travelinsurancepage = new TravelInsurancePage(driver,logger);
		PageFactory.initElements(driver, travelinsurancepage);
		return travelinsurancepage;
	}
	
	public String[] HealthInsuranceList()
	{
		setWait(1);
		Actions action=new Actions(driver);//Initializing action
		action.moveToElement(insuranceProducts).build().perform();//To click on insurance products element
		action.moveToElement(healthInsurance).build().perform();//To click on health insurance element
		logger.log(Status.INFO, "Getting the Health Insurance List from the Policy Bazzar Home Page");
		String[] healthInsuranceTypes=new String[healthInsuranceList.size()];
		for(int i=0;i<healthInsuranceList.size();i++)
		{
			healthInsuranceTypes[i]=healthInsuranceList.get(i).getText();
			System.out.println(healthInsuranceTypes[i]);
		}
		logger.log(Status.PASS, "Retrieved the Health Insurance Names List");
		return healthInsuranceTypes;
	}
	
	public CarInsurancePage clickCarInsurance() {
		try {
			setWait(1);
			Actions action = new Actions(driver);//Initializing action
			action.moveToElement(insuranceProducts).build().perform();//To click on insurance products element
			action.moveToElement(carInsurance);//To move cursor on to the element car insurance
			action.build().perform();//To click on car insurance element
			logger.log(Status.INFO, "Clicking the Car Insurance Link from the Insurance Products");
			carInsurance.click();
			logger.log(Status.PASS, "Clicked the Car Insurance Link");
		}catch(Exception e) {
			reportFail(e.getMessage());
		}
		
		/************* To initialize carInsurance web elements *************/
		CarInsurancePage carinsurancepage = new CarInsurancePage(driver,logger);
		PageFactory.initElements(driver, carinsurancepage);
		return carinsurancepage;
	}
	
}
