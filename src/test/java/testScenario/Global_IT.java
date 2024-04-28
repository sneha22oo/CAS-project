
package testScenario;
 
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
 
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
 
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
 
import testObjectRepository.Global_IT_Page;
import testObjectRepository.Home_page;
import userDefinedLibraries.DriverSetup;
import userDefinedLibraries.ExcelUtility;
import userDefinedLibraries.ExtentReportsForProject;
import userDefinedLibraries.ScreenShot;
 
public class Global_IT {
	WebDriver driver;
	JavascriptExecutor js;
	WebElement st;
	Home_page hp;
	Global_IT_Page gp;
	ExcelUtility excel;
	List<WebElement> newsHeadersList;
	int i;
	ExtentReports extent;
	ExtentTest test;
	ExtentReportsForProject extreportobj = new ExtentReportsForProject();
    String screenshotPath;
	String dataPath;
	int k=1;
	int a=1;
	 
	//Browser Instantiation
	@BeforeClass
	@Parameters({ "browser" }) 
	void setUp(String browser) {
		driver=DriverSetup.driverInstantiate(browser);
		System.out.println("Browser opened with the provided url");
		extent = extreportobj.setExtentReport();
		System.out.println("Report object Initialised");
 
		js=(JavascriptExecutor)driver;
		gp=new Global_IT_Page(driver);
		hp=new Home_page(driver);
		
		dataPath=System.getProperty("user.dir")+"\\testData\\OutputData.xlsx";
		excel=new ExcelUtility(dataPath);
	}

  //Capturing UserName and UserEmail
  @Test(priority=0)
  void captureUserInfo() {
	  test = extent.createTest("To capture user info");
	  test.log(Status.INFO, "Starting the test case");
	   hp.clickOnUserInfo();
	   ScreenShot.getScreenShot(driver,"UserInfo.png");
	   String userName=hp.userName.getText();
	   String userEmail=hp.userEmail.getText();
	   excel.writeData(dataPath,"Sheet1", 1, 1, userName);
	   excel.writeData(dataPath,"Sheet1", 2, 1, userEmail);
	   System.out.println("UserName is : "+userName);
	   System.out.println("UserEmail is : "+userEmail);
	   test.pass("UserInfo captured");
   }
  
  //Clicking on CorporateFunction and Mouse Hovered on Security&Technology and Clicking on IT 
  @Test(priority=1)
   void clickOnIT() {
	  test = extent.createTest("To click on IT");
	  hp.clickOnCorporateFunctions();
	  test.log(Status.INFO,"Clicked 'Corporate Functions'");
		   hp.mouseOverOnSecurityAndTechnology();
		   try {
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
      test.log(Status.INFO,"mouse hovered on 'SecurityAndTechnology'");
		   hp.clickOnIT();
	  test.log(Status.INFO,"Clicked 'IT'");
		   test.pass("Test passed");
		   System.out.println("Corporate Function and Click_On_IT report Generated");
	   }
  
    //Exploring The Apps Which Are Present on Global IT Page
    @Test(priority=2)
    void exploreApps(){
    	test = extent.createTest("To explore other apps in Global IT page");
    	gp.exploreApps();
    	test.pass("Explored all other apps in Global IT page");
    	System.out.println("Report For Exploring Apps is Generated");
    	
    }
    
    //Validating Apps by Comparing the Actual And Expected Titles
    @Test(priority=3,dataProvider="titles")
    void validateApps(String expct_title,String appName)  {
    	test = extent.createTest("To validate other apps in Global IT page");
    	String s=gp.app.get(i);
    	i++;
    	Assert.assertEquals(s, expct_title);
    	test.pass(appName+" is validated");
    	System.out.println("Report For Exploring "+appName+"  App is Generated");
    }
    
    //Verifying News Section Availability
    @Test(priority=4)
    void verifyNewsAvailability() {
    	test = extent.createTest("To Check the Availability of IT News section");
    	js.executeScript("arguments[0].scrollIntoView();",gp.apps.get(0));
    	WebElement IT_News=gp.IT_News;
    	boolean news=IT_News.isDisplayed();
    	ScreenShot.getScreenShot(driver,"ITNews.png");
    	test.createNode(" IT_News is Present");
    	Assert.assertEquals(news,true);
    	test.pass("Test Passed");
    	System.out.println("Report of News Section Availability is Generated");
    }
    
    //Verifying Awards Section Availability
    @Test(priority=5)
    void verifyAwardsAvailability() {
    	test = extent.createTest("To Check the Availability of IT Awards section");
    	js.executeScript("arguments[0].scrollIntoView();",gp.awardsView);
    	WebElement IT_Awards=gp.IT_Awards;
    	boolean awards=IT_Awards.isDisplayed();
    	ScreenShot.getScreenShot(driver,"ITAwards.png");
    	test.createNode(" IT_Awards is Present");
    	Assert.assertEquals(awards,true);
    	test.pass("Test Passed");
    	System.out.println("Report of Awards Section Availability is Generated");
    }
    
    //Displaying the Total News Count Present in the Global IT Page
    @Test(priority=6)
    void displayNewsCount() {
    	test = extent.createTest("To print news count");
    	newsHeadersList=gp.newsHeadersList;
    	int cnt=newsHeadersList.size();
    	System.out.println("Total News Count on Global IT page : "+cnt);
    	excel.writeData(dataPath,"Sheet1", 1, 2,String.valueOf(cnt));
    	test.pass("News count printed");
    }
    
    //Validating NewsHeader and ToolTip
    @Test(priority=7)
    void validateNewsHeaderAndTooltip() {
    	test = extent.createTest("To validate newsheaders and tooltips ");
    	for(WebElement w:newsHeadersList) {
			String header=w.getText();
			String tooltip=w.getAttribute("title");
			Assert.assertEquals(header, tooltip);
		}
    	test.pass("Newsheaders and their tooltips are matched");
    	System.out.println("Newsheader and Tooltip Validation Report Generated");
    }
    
    //Capturing Award Description By clicking on Every Award
	@Test(priority=8,dataProvider="awards")
	 void displayAwards(String a) {
	        	test = extent.createTest("To display all awards information ");
				String s="";
				js.executeScript("arguments[0].scrollIntoView();",gp.awards1);
				driver.findElement(By.linkText(a)).click();
				test.log(Status.INFO, "Clicked awardLink");
				try {
					Thread.sleep(5000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				List<WebElement> deslist=gp.descriptionsList;
				ScreenShot.getScreenShot(driver, "awards"+k+".png");
				for(WebElement d:deslist) {
					s=s+d.getText();
				}
				System.out.println(s);
				excel.writeData(dataPath,"Sheet2", k, 1,s);
				driver.navigate().back();
				try {
					Thread.sleep(5000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				test.pass("Displayed all awards information");
				System.out.println("Award "+k+" Description Report Generated and Written in Excel");
				k++;
	           }
	
	//Getting status and Taking ScreenShot of Passed and Failed Test Cases
	@AfterMethod
	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			// MarkupHelper is used to display the output in different colors
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			test.log(Status.FAIL,
			MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
			screenshotPath = ScreenShot.getScreenShot(driver, "TestCaseFailed"+a+".png");
			a++;
			// To add it in the extent report
			test.fail("Test Case Failed Snapshot is below " + test.addScreenCaptureFromPath(screenshotPath));
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
		} else if (result.getStatus() == ITestResult.SUCCESS) 
		{
			
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
			screenshotPath = ScreenShot.getScreenShot(driver, "TestCase Passed"+a+".png");
			test.pass("Test case passed snapshot is below " + test.addScreenCaptureFromPath(screenshotPath));
			a++;
		}
	}
 
	//Ending Report
	@AfterMethod(dependsOnMethods = "getResult")
	public void endReport() {
		// write results into the file
		extreportobj.endExtentReport();
 
	}
 
	//Closing The Browser after all the steps of scenario
	@AfterClass
	public void closeBrow() {
		DriverSetup.driverTearDown();
		System.out.println("The Browser Has Been Closed");
	}
	
	//DataProvider For Awards
    @DataProvider(name="awards")
	String[][] awardsInfo() {
        String path=".\\testData\\Data.xlsx";
		ExcelUtility xlutil=new ExcelUtility(path);
		int totalrows=xlutil.getRowCount("Sheet2");	
		int totalcols=xlutil.getCellCount("Sheet2",1);
		String[][] awards=new String[totalrows][totalcols];
		for(int i=1;i<=totalrows;i++)  //1   //read the data from xl storing in two deminsional array
		{	
			for(int j=0;j<totalcols;j++)  //0    i is rows j is col
			{
				awards[i-1][j]= xlutil.getCellData("Sheet2",i, j);  //1,0
			}
		}
		return awards;
    }
    
    //DataProvider For Titles
    @DataProvider(name="titles")
	public String[][] getTitles(){
		String path=".\\testData\\Data.xlsx";
		ExcelUtility xlutil=new ExcelUtility(path);
		int totalrows=xlutil.getRowCount("Sheet1");	
		int totalcols=xlutil.getCellCount("Sheet1",1);
		String[][] titles=new String[totalrows][totalcols];
		for(int i=1;i<=totalrows;i++)  //1   //read the data from xl storing in two deminsional array
		{	
			for(int j=0;j<totalcols;j++)  //0    i is rows j is col
			{
				titles[i-1][j]= xlutil.getCellData("Sheet1",i, j);  //1,0
			}
		}
		return titles;
	}
}
