package baseClasses;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import policybazaar.pageClasses.PolicyBazaarHomePage;
import utilities.DateUtil;
import utilities.ReadPropertiesFile;

public class BaseUI extends BaseClass{

	public BaseUI(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
	}
	

	/*****************OpenApplication @throws Exception******************/
	
	public PolicyBazaarHomePage openWebsite() throws Exception {
		logger.log(Status.INFO, "Opening the WebSite");//To open website
		driver.get(ReadPropertiesFile.getUrl());//Url of website is present in properties file
		logger.log(Status.PASS, "Successfully Opened the Policy Bazaar Website");
		PolicyBazaarHomePage policybazaarhomepage = new PolicyBazaarHomePage(driver,logger);
		PageFactory.initElements(driver, policybazaarhomepage);//To initialize webelements 
		return policybazaarhomepage;
	}
	
	
	/****************** Reporting Functions ***********************/
	
	public void reportFail(String reportString) {
		logger.log(Status.FAIL, reportString);
		takeScreenShotOnFailure();
		Assert.fail(reportString);
	}
	

	public void reportPass(String reportString) {
		logger.log(Status.PASS, reportString);
	}
	

	/****************** Capture Screen Shot ***********************/
	
	public void takeScreenShotOnFailure() {
		logger.log(Status.INFO, "Capturing the error messages for invalid input");
		TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
		File sourceFile = takeScreenShot.getScreenshotAs(OutputType.FILE);
		logger.log(Status.PASS, "Successfully captured the error messages");
		File destFile = new File(System.getProperty("user.dir") + "\\ScreenShots\\" + DateUtil.getTimeStamp() + ".png");
		try {
			FileUtils.copyFile(sourceFile, destFile);
			logger.addScreenCaptureFromPath(
					System.getProperty("user.dir") + "\\ScreenShots\\" + DateUtil.getTimeStamp() + ".png");

		} catch (IOException e) {
			e.printStackTrace();					
		}
	}
	
	
	/******************************clicking function*************/
	
	public void click(WebElement element,String info,String pass) {
		try {
			logger.log(Status.INFO, info);
			element.click();
			logger.log(Status.PASS, pass);
		}catch(Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	
	/****************close function********************************/
	
	public void ClosePolicyBazzarPage() {
		driver.close();
	}
	
	
	/****************quit function********************************/
	
	public void quitPolicyBazzarPage() {
		driver.quit();
	}
	
}
