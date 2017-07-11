/**
 * 
 */
package com.automationlearning;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

/**
 * @author CHIRAG
 *
 *This video for run smae test script in different android version
 */
public class Tutorial23 {

	AppiumDriver<MobileElement> driver;
	String path;
	@Parameters("androidversion")
	@BeforeMethod
	public void setup(@Optional("7")String verion)
	{
		System.out.println("Session is creating");
		path = System.getProperty("user.dir");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("platformName", "Android");
		cap.setCapability("deviceName", "Nexus 9");
		cap.setCapability("app", path+"//app//ApiDemos.apk");
		if(verion.equals("7"))
		{
			cap.setCapability("udid", "192.168.14.101:5555");
		}else if(verion.equals("6"))
		{
			cap.setCapability("udid", "192.168.14.102:5555");
		}
		try {
			driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), cap);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println("Session is created");
	}
	
	@Test
	public void clickTab()
	{
		boolean flag = false;
		driver.findElementByAccessibilityId("Views").click();
		for(int i=0;i<=20;i++)
		{
			try {
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				driver.findElementByAccessibilityId("Tabs").click();
				break;
			} catch (Exception e) {
				verticalSwipe();
			}
		}
		flag = driver.findElementByAccessibilityId("1. Content By Id").isDisplayed();
		if(flag)
		{
			System.out.println("Passed");
		}else
		{
			System.out.println("Failed");
		}
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	public void verticalSwipe()
	{
		Dimension dim = driver.manage().window().getSize();
		int height = dim.getHeight();
		int width = dim.getWidth();
		int x = width/2;
		int starty = (int)(height*0.80);
		int endy = (int)(height*0.20);
		driver.swipe(x, starty, x, endy, 500);
		
	}
}
