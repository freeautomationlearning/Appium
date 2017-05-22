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
public class Tutorial6 {

	AppiumDriver<MobileElement> driver;
	String path;
	
	public void setup()
	{
		System.out.println("Session is creating");
		path = System.getProperty("user.dir");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("platformName", "Android");
		cap.setCapability("deviceName", "Moto G");
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
	
	public void validateText()
	{
		driver.findElementByAccessibilityId("Accessibility").click();
		String text = driver.findElementByAccessibilityId("Accessibility Node Provider").getText();
		if(text.equalsIgnoreCase("Accessibility Node Provider"))
		{
			System.out.println("Passed");
		} else
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
	 */
	public static void main(String[] args) {
		Tutorial6 obj = new Tutorial6();
		obj.setup();
		obj.validateText();
		obj.tearDown();

	}
}
