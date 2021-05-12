package policybazaar.pageClasses;

import java.util.List;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import baseClasses.BaseUI;

public class CarInsurancePage extends BaseUI{
	
	public CarInsurancePage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);//To call superclass constructor
	}
/******** To find the web elements in CarInsurance page************/
	
	@FindBy(xpath = "//*[@id=\"frmCar\"]/div[1]/div/div/div[6]/a[1]")
	public WebElement proceedWithoutNumber;
	
	@FindBy(xpath = "//a[@class='pb-logo']")
	public WebElement policyBazaarButton;
	
	@FindBy(className="react-autosuggest__input")	
	public WebElement searchKey;
	
	@FindBy(xpath = "//body/div[@id='app']/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")
	public WebElement citySerach;
	
	@FindBy(xpath ="//body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")
	public WebElement carBrand;
	
	@FindBy(xpath="//*[@id=\"modelScroll\"]/li[3]/span")
	public WebElement carModel;
	
	@FindBy(xpath = "//ul//div//li//span")
	public List<WebElement> fuelType;
	
	@FindBy(xpath = "//body/div[@id='app']/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")
	public WebElement variant;
	
	@FindBy(xpath = "//b[contains(text(),'Brand New Car')]")
	public WebElement year;
	
	@FindBy(xpath = "//input[@id='name']")
	public WebElement Name;

	@FindBy(xpath = "//input[@id='email']")
	public WebElement errorEmail;
	
	@FindBy(xpath = "//input[@id='mobileNo']")
	public WebElement errorMobileno;
	
	@FindBy(xpath = "//span[contains(text(),'View Prices')]")
	public WebElement clickProceed;
	
	@FindBy(xpath ="//div[@class='msg-error show']")
	public List<WebElement> errorMessageList;
	

	
	/*Proceed without car number web element method*/
	public void proceedWithoutCarNumber() {
		click(proceedWithoutNumber,"Clicking the Proceed Without CarNumber button","Clicked the Proceed Without CarNumber button");
		
	}
	/*Method to select RTO and City*/
	public void searchCity(String RTOValue) {
		try {
			//To delete previous values
			searchKey.sendKeys(Keys.CONTROL+"a");
			searchKey.sendKeys(Keys.DELETE);
			setWait(1);
			//sending the RTOValue by using excel
			citySerach.sendKeys(RTOValue);
			citySerach.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
		}catch (Exception e) {
			reportFail("Failed to Select the RTO and City");
			System.out.println("Failed to Select the RTO and City");
		}
		
	}
	/*Method to select car Brand*/
	public void searchBrand(String carName) {
		try {
			logger.log(Status.INFO, "Selecting the CAR Brand in suggestion list by passing the Value in Search");
			setWait(2);
			//To delete the previous values
			searchKey.sendKeys(Keys.CONTROL+"a");
			searchKey.sendKeys(Keys.DELETE);
			setWait(1);
			//sending the CarName by using excel file
			carBrand.sendKeys(carName);
			carBrand.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
			logger.log(Status.PASS, "Successfully selected the Car Brand");
		} catch (Exception e) {
			reportFail("Failed to Select the Car Brand");
			System.out.println("Failed to Select the Car Brand");
		}
		
	}
	
	/*Method to select fuel type*/
	public void typeFuel(String fType) {
		try {
			logger.log(Status.INFO, "Selecting the Fuel Type in List");
			setWait(1);
			//sending fuel type value by using excel file
			for (WebElement Fuel : fuelType) {
				if (Fuel.getText().equalsIgnoreCase(fType)) {
					Fuel.click();
					break;
				}
			}
			logger.log(Status.PASS, "Successfully selected the Fuel Type");
		} catch (Exception e) {
			reportFail("Failed to select the Fuel Type");
		}
		
	}
	/*Method to select the car variant*/
	public void selectVariant(String Variant) {
		
		logger.log(Status.INFO, "Selecting the car version in List");
		//To delete the previous values
		searchKey.sendKeys(Keys.CONTROL+"a");
		searchKey.sendKeys(Keys.DELETE);
		setWait(1);
		variant.sendKeys(Variant);
		variant.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
		logger.log(Status.INFO, "Selecting the CAR Brand in suggestion list by passing the Value in Search");
		
	}
	/*Method to select a particular year*/
	public void carYear() {
		year.click();//To click Year
	}
	/*Method to enter full name*/
	public void cName(String Na) {
		setWait(3);
		Name.sendKeys(Keys.CONTROL+"a");
		Name.sendKeys(Keys.DELETE);
		Name.sendKeys(Na);
		
	
	}
	/*Method to  enter invalid email*/
	public void eEmail(String mail) {
		errorEmail.clear();//To clear the previous data
		errorEmail.sendKeys(mail);//sending mail id by using excel file
		
	}
	/*Method to enter invalid phone number*/
	public void ePnumber(String mno) {
		errorMobileno.clear();//To clear the previous data
		errorMobileno.sendKeys(mno);//sending mobile no by using excel file
	}
	/*Method to click on view prices*/
	public void proceedClick() {
		clickProceed.click();
	}

}
