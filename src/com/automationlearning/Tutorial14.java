/**
 * 
 */
package com.automationlearning;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

/**
 * @author CHIRAG
 *
 */
public class Tutorial14 {

	AppiumDriver<MobileElement> driver;
	public void setUp() throws MalformedURLException
	{
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("platformName", "Android");
		cap.setCapability("deviceName", "Moto G");
		cap.setCapability("browserName", "Chrome");
		driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), cap);
		driver.get("http://www.google.com");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	public void searchKeyword() throws InterruptedException
	{
		driver.findElementByName("q").sendKeys("appium");
		Thread.sleep(5000);
	}
	
	public void tearDown()
	{
		driver.quit();
	}
	/**
	 * @param args
	 * @throws MalformedURLException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		// TODO Auto-generated method stub
		Tutorial14 obj = new Tutorial14();
		obj.setUp();
		obj.searchKeyword();
		obj.tearDown();

	}

}
