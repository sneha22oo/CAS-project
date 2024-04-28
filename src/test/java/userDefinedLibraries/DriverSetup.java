package userDefinedLibraries;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverSetup {
		
		public static WebDriver driver;
		public static String url = "https://be.cognizant.com/";
		public static String browsertype;

		public static WebDriver driverInstantiate(String browser) {
			browsertype = browser;
			if (browsertype.equalsIgnoreCase("chrome")) {
				driver = new ChromeDriver();
			} else if (browsertype.equalsIgnoreCase("edge")) {
				driver = new EdgeDriver();
			}
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
			return driver;
		}

		public static void driverTearDown() {
			driver.quit();
		}

	}

