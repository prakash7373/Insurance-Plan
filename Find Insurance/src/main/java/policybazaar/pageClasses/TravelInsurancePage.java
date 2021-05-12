package policybazaar.pageClasses;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import baseClasses.BaseUI;

public class TravelInsurancePage extends BaseUI {
	public TravelInsurancePage(WebDriver driver,ExtentTest logger) {
		super(driver,logger);
	}
	
	/**************To find the web elements in Travel Insurance page**************/	
	@FindBy(xpath="//body/cclink[1]/div[4]/div[1]/div[1]/ul[1]/li[2]/div[1]/div[1]/div[4]/ul[1]/li[1]/a[1]/span[1]")
	WebElement travelinc;
	
	@FindBy(xpath="//body/div[@id='policybazaar']/section[1]/div[2]/section[1]/div[2]/article[1]/ul[1]/li[3]/a[1]")
	public WebElement student;
	
	@FindBy(id="destination-autocomplete")
	public WebElement desCountry;
	
	@FindBy(id="memage1")
	public WebElement age1;
	
	@FindBy(id="memage2")
	public WebElement age2;
	
	@FindBy(id="startdate")
	public WebElement startDate;
	
	
	@FindBy(xpath = "/html[1]/body[1]/div[8]/div[2]/div[1]/table[1]/thead[1]/tr[1]/th[2]/select[1]") //html[1]/body[1]/div[8]/div[2]/div[1]/table[1]/thead[1]/tr[1]/th[3]
	
	public WebElement monthSelectRight;
	
	@FindBy(xpath = "/html[1]/body[1]/div[9]/div[2]/div[1]/table[1]/thead[1]/tr[1]/th[2]/select[1]")
	public WebElement monthSelectLeft;
	
	@FindBy(xpath = "/html[1]/body[1]/div[8]/div[2]/div[1]/table[1]/thead[1]/tr[1]/th[2]/select[2]")
	public WebElement yearSelectRight;
	
	@FindBy(xpath = "/html[1]/body[1]/div[9]/div[2]/div[1]/table[1]/thead[1]/tr[1]/th[2]/select[2]")
	public WebElement yearSelectLeft;
	
	@FindBy(xpath = "//div[8]//div[2]//tr//td[@class='available' or @class='weekend available']")
	public List<WebElement> daysListRight;
	
	@FindBy(xpath = "//div[9]//div[2]//tr//td[@class='available' or @class='weekend available']")
	public List<WebElement> daysListLeft;
	
	@FindBy(id="enddate")
	public WebElement endDate;
	
	@FindBy(id="proceedStepOne")
	public WebElement proceed;
	
	@FindBy(id="travelgender")
	public WebElement travelerGender;
	
	@FindBy(id="travelname")
	public WebElement travelerName;
	
	@FindBy(id="travelmobile")
	public WebElement travelMobile;
	
	@FindBy(id="travelemail")
	public WebElement travelEmail; 
	
	@FindBy(xpath="//a[contains(text(),'Get Free Quotes')]")
	public WebElement getFreeQuotes;
	
	@FindBy(xpath = "//a[@class='pb-logo']")
	public WebElement policyBazaarButton;
	
	@FindBy(className="sort_select")
	public WebElement sortPrice;
	
	@FindBy(xpath="//div[@class='desktop leftLogo']/div[1]")
	public List<WebElement> insuranceProvider;
	
	@FindBy(xpath="//div[@class='col-md-3 cta desktop']/div[1]/button")
	public List<WebElement> insuranceCost;
	
	/*To click on student web element*/
	public void clickStudentCategory() {
		click(student,"Clicking the student category","Successfully clicked the student category");//To click on student element
	}
	/*Method to select date, year, month*/
	public void dateSelect(String date,List<WebElement> daysList,WebElement monthSelect,WebElement yearSelect){
		int day = Integer.parseInt(date.substring(0, 2));
		int month = Integer.parseInt(date.substring(3, 5));
		String year= date.substring(6, 10);
		
		/*To select year*/
		Select select = new Select(yearSelect);
		select.selectByVisibleText(year);
		setWait(1);
		
		/*To select month*/
		Select selectmonth = new Select(monthSelect);
		selectmonth.selectByIndex(month-1);
		setWait(1);
		
		/*To scroll down the web page*/
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,100)");
		
		setWait(1);
		
		Actions action = new Actions(driver);
		action.moveToElement(daysList.get(day-1)).click().perform();
	}
	
	/*Method to  select start date for a plan*/
	public void selectStartDate(String date){
		startDate.click();
		click(startDate,"Clicking the Start Date Calender","Successfully clicked the start date calender");
		dateSelect(date,daysListRight,monthSelectRight,yearSelectRight);
	}
	
	/*Method to select end date for a plan*/
	public void selectEndDate(String date){
		click(endDate,"Clicking the End Date Calender","Successfully clicked the End date calender");
		dateSelect(date,daysListLeft,monthSelectLeft,yearSelectLeft);
	}
	
	/*To click on proceed element*/
	public void clickProceed() {
		click(proceed,"Clicking the Proceed Button", "Successfully clicked the proceed button");
	}
	/*Method to enter name*/
	public void fillName(String name) {
		String prefix = name.substring(0, 3);//To select name prefix
		String travelername = name.substring(4);//To enter traveller name
		setWait(1);
		
		Select prefixselect = new Select(travelerGender);
		prefixselect.selectByVisibleText(prefix);
		travelerName.clear();
		travelerName.sendKeys(travelername);//sending traveler name details by using excel
	}
	
	/*Method to enter Mobile number*/
	public void fillMobile(String phone) {
		
		travelMobile.clear();
		travelMobile.sendKeys(phone);
	}
	
	/*Method to enter Email*/
	public void fillEmail(String mail) {
		travelEmail.clear();
		travelEmail.sendKeys(mail);
	}
	
	/*To click on Getfreequotes element*/
	public void clickGetQuotes() {
		click(getFreeQuotes,"Cliking the Get Free Quotes button", "Clicked the Get Free Quotes button");
	}
	/*Method to display three lowest travel insurance plans*/
	public void getThreeLowTravelPlans() throws IOException {
		try {
			logger.log(Status.INFO, "Getting the Lowest Three Travel Plan Quotes");
			Select priceSort = new Select(sortPrice);//To click on sortby drop down
			priceSort.selectByVisibleText("Price: Low to High");//To low to high in sortby drop down
			
			/*To display lowest three international plans*/
			Iterator<WebElement> itr = insuranceProvider.iterator(); 
			Iterator<WebElement> itr2 = insuranceCost.iterator(); 
			logger.log(Status.PASS,"Retrieved the Lowest Travel Plan Quotes"); 
			//for insurance provider name
			for(int i=0;i<3;i++) { 
				String insuranceName = itr.next().getAttribute("class"); 
				String[] providerName = insuranceName.split("\\s+"); String name = providerName[1];
			    System.out.println((i+1)+":"+" Insurance Provider's Name: "+name);
			  //based on the insurance provider name retrieving the amount
			  if(itr2.hasNext()) { 
				  String insuranceCost = itr2.next().getText(); String[]
                  providerCost = insuranceCost.split("\\s+"); 
				  String cost = providerCost[1];
			      System.out.println("Amount: "+cost); 
			      System.out.println("********"); }
			  
			  }
			 
			}catch(Exception e) {
			reportFail("Failed to Retrieve the Travel Plan Quotes");
		}
		
	}

}
