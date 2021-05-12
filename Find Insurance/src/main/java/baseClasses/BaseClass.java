package baseClasses;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utilities.ExtentReportManager;
import utilities.ReadPropertiesFile;

public class BaseClass {
	
	public WebDriver driver;
	public ExtentReports report = ExtentReportManager.getReportInstance();//To generate html reports
	public ExtentTest logger;//To log steps onto the previously generated html report
	
	
	public void invokeBrowser() throws Exception {
		
		ReadPropertiesFile ReadPropertiesFile=	new ReadPropertiesFile();
		String userDir=System.getProperty("user.dir");//invoking browser name from getbrowser() in input.properties.file
		String webBrowser = utilities.ReadPropertiesFile.getbrowser();
		if(webBrowser.equalsIgnoreCase("chrome")) 
		{
			
			System.setProperty("webdriver.chrome.driver", userDir + "\\drivers\\chromedriver.exe");//call getChromeDriver() to connect with chrome browser
			driver = new ChromeDriver();
			//DesiredCapabilities capabilities = DesiredCapabilities.chrome(); //customize the desired capabilities for the browser
			//driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		}
		if (webBrowser.equalsIgnoreCase("Firefox")) 
		{
			
			System.setProperty("webdriver.gecko.driver",userDir + "\\drivers\\geckodriver.exe");//call getFireFoxDriver() to connect with firefox browser
			driver = new FirefoxDriver();
			//DesiredCapabilities capabilities = DesiredCapabilities.firefox(); //customize the desired capabilities for the browser
			//driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		}
	
		driver.manage().window().maximize();  //Maximizes the window
		driver.manage().deleteAllCookies();   //Delete all the cookies
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);  //Defining Implicit Wait for the driver
	}
	
	public void setWait(int i) {
		try {
			Thread.sleep(i*1000);//To pause execution for particular amount of time
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@AfterMethod
	public void flushReports() {
		report.flush();//To erase any previous data on the report and create new reports
	}
}
