/**
 * 
 */
package com.automationlearning;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

/**
 * @author CHIRAG
 *
 */
public class Tutorial13 {

	AppiumDriver<MobileElement> driver;
	String path;
	
	public void setup()
	{
		System.out.println("Session is creating");
		path = System.getProperty("user.dir");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("platformName", "Android");
		cap.setCapability("deviceName", "Nexus 9");
		cap.setCapability("app", path+"//app//ApiDemos.apk");
		try {
			driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), cap);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println("Session is created");
	}
	
	public void clickTab()
	{
		boolean flag = false;
		driver.findElementByAccessibilityId("Views").click();
		for(int i=0;i<=20;i++)
		{
			try {
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				driver.findElementByAccessibilityId("Tabs").click();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				break;
			} catch (Exception e) {
				verticalSwipe();
			}
		}
		driver.findElementByAccessibilityId("5. Scrollable").click();
	}
	
	public void verifyTab28()
	{
		boolean f =false;
		for(int i =1;i<=10;i++)
		{
			try {
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				f = driver.findElementByXPath("//*[@text='TAB 28']").isDisplayed();
				f = true;
				break;
			} catch (Exception e) {
				hortizonalSwipe();
			}
		}
		if(f)
		{
			System.out.println("Passed");
		}else
		{
			System.out.println("Failed");
		}
	}
		
	public void hortizonalSwipe()
	{
		Dimension dim = driver.manage().window().getSize();
		int height = dim.getHeight();
		int width = dim.getWidth();
		int y = (int)(height*0.20);
		int startx = (int)(width*0.75);
		int endx = (int)(width*0.35);
		driver.swipe(startx, y, endx, y, 500);
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
	public void tearDown()
	{
		driver.quit();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tutorial13 obj = new Tutorial13();
		obj.setup();
		obj.clickTab();
		obj.verifyTab28();
		obj.tearDown();
	}

}
