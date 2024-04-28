package userDefinedLibraries;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

 
public class ExtentReportsForProject {

	public ExtentSparkReporter spark;
	public ExtentReports report;
	public ExtentTest test;
 
	public ExtentReports setExtentReport() {
		// Create an object of Extent Reports
		report = new ExtentReports();
		// specify location of the report
		spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/FunctionalTestingReport.html");
		report.attachReporter(spark);
		
		// Passing General information
		report.setSystemInfo("Host Name", "localhost");
		report.setSystemInfo("Environment", "QA");
		report.setSystemInfo("User Name", "SnehaOdeyar");
		spark.config().setDocumentTitle("Automation Report");
		// Name of the report
		spark.config().setReportName("Functional Testing - Test Execution Cycle 1 - Release V1.0");
		// Dark Theme
		spark.config().setTheme(Theme.STANDARD);// only 2 themes available STANDARD and DARK
		return report;
	}
	
	public void endExtentReport() {
		// write results into the file
		report.flush();
	}
}
