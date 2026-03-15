package testCases;



import testBase.BaseClass;


import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	//public WebDriver driver;
	
	
	

	@Test(groups = {"Regression","Master"})
	public void verify_account_registration()                     
	{
	
		logger.info("***** Starting TC001_AccountRegistrationTest ******");
		
		
		try
		{
		HomePage hp=new HomePage(driver);                                        //Homepage
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount Link...");
		
		hp.clickRegister();
		logger.info("Clicked on Register Link...");
		
		
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);    //AccountRegistration page
		
		logger.info("Providing customer details...");
		regpage.setFirstname("Johny");
		regpage.setLastname("David");
		regpage.setEmail("idnneon7776@gmail.com");   // everytime add new email id
		regpage.SetTelephone("213445556");

		//String password = randomAlphaNumeric();

		regpage.setPassword("xyz12345");
		regpage.setConfirmPassword("xyz12345");
		
		regpage.setprivacyPolicy();
		regpage.clickContinue();
		
		logger.info("Validating expected message...");
		String confmsg=regpage.getConfirmationmsg();
		if (confmsg.equals("Your Account Has Been Created!")) 
		{
			Assert.assertTrue(true);
		
		} 
		else 
		{
			logger.error("Test failed...");
			logger.debug("Debug logs...");
            Assert.assertFalse(false);
		}
		
		
		//Assert.assertEquals(confmsg, "Your Account Has Been Created!!!");
		}
		catch (Exception e) {
			//logger.error("Test failed", e);
			//logger.debug("Debug logs...");
	        //Assert.fail("Exception occurred: " + e.getMessage());
			Assert.fail();
		}
		
		logger.info("***** Finished TC001_AccountRegistrationTest ******");
	}
	
	
	   
		
	
}
