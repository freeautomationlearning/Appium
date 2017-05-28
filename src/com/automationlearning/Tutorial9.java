package com.automationlearning;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Tutorial9 {
	/**
	 * @author CHIRAG
	 */
	
	AppiumDriver<MobileElement> driver;
	public void setUp() throws MalformedURLException
	{
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("platformName", "Android");
		cap.setCapability("deviceName", "Nexus 9");
		cap.setCapability("appPackage", "com.android.calculator2");
		cap.setCapability("appActivity", "com.android.calculator2.Calculator");
		driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), cap);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	public void addTwoNumber()
	{
		driver.findElementById("com.android.calculator2:id/digit_7").click();
		driver.findElementByAccessibilityId("plus").click();
		driver.findElementByXPath("//*[@text='2']").click();
		driver.findElementByXPath("//android.widget.GridLayout[@resource-id='com.android.calculator2:id/pad_numeric']//android.widget.Button[@index='11']").click();
		String text = driver.findElementById("com.android.calculator2:id/result").getText();
		if(text.equals("9"))
		{
			System.out.println("Passed");
		}else
		{
			System.out.println("Failed"); 
		}
	}
	
	public void tearDown()
	{
		driver.quit();
	}
	
	
	/**
	 * @param args
	 * @author CHIRAG
	 * @throws MalformedURLException 
	 */
	
	public static void main(String[] args) throws MalformedURLException {
		Tutorial9 obj = new Tutorial9();
		obj.setUp();
		obj.addTwoNumber();
		obj.tearDown();

	}

}
