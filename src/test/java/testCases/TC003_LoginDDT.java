package testCases;

import testBase.BaseClass;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

	
/*Data is valid  - login success - test pass  - logout
Data is valid -- login failed - test fail

Data is invalid - login success - test fail  - logout
Data is invalid -- login failed - test pass
*/	


	
@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "Datadriven")	//getting dataprovider from different class
public void Verify_LoginDDT(String email, String pwd, String exp) throws InterruptedException 
{
	
	logger.info("***** Starting TC003_LoginDDT ******");	
	
	
	try 
	{
		
	//homepage
    HomePage hp =new HomePage(driver);
    hp.clickMyAccount();
    hp.clicklogin();
    
    //loginpage
    LoginPage lp=new LoginPage(driver);
    lp.setEmail(email);
    lp.setPassword(pwd);
    lp.clickLogin();

    
    //MyAccountPage
    MyAccountPage macc=new MyAccountPage(driver);
    boolean targetPage=macc.isMyAccountPageExist();



    
    
    /*Data is valid  - login success - test pass  - logout
                     - login failed - test fail

    Data is invalid - login success - test fail  - logout
                    - login failed - test pass
*/



          if(exp.equalsIgnoreCase("valid"))
        {
            if(targetPage==true) 
            
            {
				Assert.assertTrue(true);
            	macc.ClickLogout();
			} 
            
            else 
            {
                Assert.assertTrue(false);
			}

        
        
            
            
          if(exp.equalsIgnoreCase("Invalid")) 
          {
            if(targetPage==true) 
            
            {
				macc.ClickLogout();
            	Assert.assertTrue(false);
			} 
            else
            {
                Assert.assertTrue(true);
			}
        
        }
}
	} 
	catch (Exception e) 
	{
	 Assert.fail();	
	}
	      Thread.sleep(3000);
          logger.info("***** Finished TC003_LoginDDT ******");	  
          
}

}



//Note: Data driven we are using when we have different sets of data, we have to validate multiple
// scenarios or edge case scenarios.
// eg. Login - Valid and Invalid scenarios

