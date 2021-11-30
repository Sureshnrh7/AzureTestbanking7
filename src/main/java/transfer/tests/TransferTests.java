package transfer.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class TransferTests {
	  String baseUrl = "https://demo.testfire.net/";
	  WebDriver driver ; 
	  
	  @Test
	  public void verifyTransferFunds() {
		  String amount = "100";
		  
		  // Click on transfer funds
		  driver.findElement(By.id("MenuHyperLink3")).click();
		  
		  String expTitle = "Transfer Funds";
		  String actualTitle = driver.findElement(By.xpath("//h1")).getText();
		  Assert.assertEquals(actualTitle,  expTitle);
		  
		  Select accounts = new Select(driver.findElement(By.id("toAccount")));
		  accounts.selectByIndex(1);
		  
		  driver.findElement(By.id("transferAmount")).sendKeys(amount);
		  driver.findElement(By.id("transfer")).click();
		  
		  String expTransferMessage = amount + " was successfully transferred from Account 800000 into Account 800001 at";
		  String actualTransferMessage = driver.findElement(By.id("_ctl0__ctl0_Content_Main_postResp")).getText();
		  Assert.assertTrue(actualTransferMessage.contains(expTransferMessage));
		  
		  // Sign out
		  driver.findElement(By.id("LoginLink")).click();    
		  
	  }
	     
	  
	  @BeforeClass
	  public void beforeClass() {
		  System.setProperty("webdriver.chrome.driver", "C:\\Users\\suresh.nampalli\\eclipse-workspace\\SelfDrvn2.0\\Drivers\\chromedriver.exe");//"C:\\Users\\venkat\\eclipse-workspace\\test-banking\\src\\test\\drivers\\chromedriver.exe");
		  driver = new ChromeDriver();
		  
		  driver.manage().window().maximize();

		  String username = "admin";
	      String password = "admin"; 

	      driver.get(baseUrl);
	      
	      driver.findElement(By.id("LoginLink")).click();
	      
	      driver.findElement(By.id("uid")).sendKeys(username);
	      driver.findElement(By.id("passw")).sendKeys(password);
	      driver.findElement(By.name("btnSubmit")).click();
	  }

	  @AfterClass
	  public void afterClass() {
		  driver.quit();
	  }
	  
}
