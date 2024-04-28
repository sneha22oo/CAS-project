package testObjectRepository;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class Home_page {

		
		public WebDriver driver;
		
		@FindBy(xpath="//div[contains(@id,'O365_MainLink_Me')]//ancestor::button")
		public WebElement userInfoButton;
		
		@FindBy(id="mectrl_currentAccount_primary")
		public WebElement userName;
		
		@FindBy(id="mectrl_currentAccount_secondary")
		public WebElement userEmail;
		
		@FindBy(xpath="//button[@name='Corporate Functions']")
		public WebElement corporateFunctions;
		
		@FindBy(xpath="//a[@name='Security and Technology']")
		public WebElement securityAndTechnology;
		
		@FindBy(xpath="//div[contains(@id,'ContextualMenu')]//li[1]")
		public WebElement IT;
		
		public Home_page(WebDriver driver) {
			
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
		
		public void clickOnUserInfo() {
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			userInfoButton.click();
			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void clickOnCorporateFunctions() {
			corporateFunctions.click();
		}
		
     	public void mouseOverOnSecurityAndTechnology() {
		try {
			Actions ac=new Actions(driver);
			ac.moveToElement(securityAndTechnology).perform();
			Thread.sleep(5000);
			IT.isDisplayed();
			}
			catch(Exception e) {
				JavascriptExecutor js=(JavascriptExecutor)driver;
				String script="var ele=arguments[0];"
			                   +"var mouseEvent=document.createEvent('MouseEvents');"
						       +"mouseEvent.initEvent('mouseover',true,true);"
			                   +"ele.dispatchEvent(mouseEvent);";
				js.executeScript(script,securityAndTechnology);
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}	       
			}
     	}
		public void clickOnIT() {
			IT.click();
		}
	}

