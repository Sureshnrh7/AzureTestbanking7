package login.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class LoginTests {
  String baseUrl = "https://demo.testfire.net/";
  WebDriver driver ; 
	
  @Test
  public void verifyValidCredentials() {
      String expTitle = "Altoro Mutual";
      String expMessageAfterLogin = "Hello Admin User";

      String username = "admin";
      String password = "admin"; 
	  
      driver.get(baseUrl);
      String actualTitle = driver.getTitle();

      Assert.assertEquals(actualTitle, expTitle);
      
      driver.findElement(By.id("LoginLink")).click();
      Assert.assertEquals(driver.getCurrentUrl(), "https://demo.testfire.net/login.jsp"); 
      
      driver.findElement(By.id("uid")).sendKeys(username);
      driver.findElement(By.id("passw")).sendKeys(password);
      driver.findElement(By.name("btnSubmit")).click();
      
      String actualMessageAfterLogin = driver.findElement(By.xpath("//h1")).getText();
      Assert.assertTrue(actualMessageAfterLogin.contains(expMessageAfterLogin));
      
      driver.findElement(By.id("LoginLink")).click();      
  }
  
  @Test
  public void verifyInvalidUsername() {
	  String expErrorMessage = "Login Failed: We're sorry, but this username or password was not found in our system. Please try again.";
      String username = "invalid_user";
      String password = "admin"; 
	  
      driver.get(baseUrl);

      driver.findElement(By.id("LoginLink")).click();
      
      driver.findElement(By.id("uid")).sendKeys(username);
      driver.findElement(By.id("passw")).sendKeys(password);
      driver.findElement(By.name("btnSubmit")).click();

      String actualErrorMessage = driver.findElement(By.id("_ctl0__ctl0_Content_Main_message")).getText();
      Assert.assertTrue(actualErrorMessage.contains(expErrorMessage));
  }
  
  @Test
  public void verifyInvalidPassword() {
	  String expErrorMessage = "Login Failed: We're sorry, but this username or password was not found in our system. Please try again.";
      String username = "admin";
      String password = "invalid_password"; 
	  
      driver.get(baseUrl);

      driver.findElement(By.id("LoginLink")).click();
      
      driver.findElement(By.id("uid")).sendKeys(username);
      driver.findElement(By.id("passw")).sendKeys(password);
      driver.findElement(By.name("btnSubmit")).click();

      String actualErrorMessage = driver.findElement(By.id("_ctl0__ctl0_Content_Main_message")).getText();
      Assert.assertTrue(actualErrorMessage.contains(expErrorMessage));	  
  }
  
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\suresh.nampalli\\eclipse-workspace\\SelfDrvn2.0\\Drivers\\chromedriver.exe");
	  driver = new ChromeDriver();
	  
	  driver.manage().window().maximize();
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
