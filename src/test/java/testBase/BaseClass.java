package testBase;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager; //log4j
import org.apache.logging.log4j.Logger;  //Log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;



public class BaseClass {
	

public WebDriver driver;
public Logger logger;
public Properties p;


	@BeforeClass(groups = {"Sanity", "Regression", "Master"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException 
	{
		
		//Loading config.properties file
		FileReader file = new FileReader(".//src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		
		
		
		
		logger=LogManager.getLogger(this.getClass());   //log4j
		
		
		
		switch(br.toLowerCase())
		{
		case "chrome": driver=new ChromeDriver(); break;
		case "edge": driver=new EdgeDriver(); break;
		default: System.out.println("No matching browser..");  return;
		}
		
		
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appURL"));  //reading properties file from config.properties
		driver.manage().window().maximize();
	}
	
	
	@AfterClass(groups = {"Sanity", "Regression", "Master"})
	public void tearDown() 
	{
	
		driver.quit();
	}

	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		
		File targetFile=new File(targetFilePath);
		
		//sourceFile.renameTo(targetFile);
		
		FileUtils.copyFile(sourceFile, targetFile);
			
		return targetFilePath;


}
}
