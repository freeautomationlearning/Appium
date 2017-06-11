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
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

/**
 * @author CHIRAG
 *
 */
public class Tutorial19 {

	AppiumDriver<MobileElement> driver;
	
	public void setUp() throws MalformedURLException
	{
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("platformName", "Android");
		cap.setCapability("deviceName", "Nexus 9");
		cap.setCapability("appPackage", "com.android.messaging");
		cap.setCapability("appActivity", "com.android.messaging.ui.conversationlist.ConversationListActivity");
		driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"),cap);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	public void longPress() throws InterruptedException
	{
		TouchAction ac = new TouchAction(driver);
		MobileElement ele = driver.findElementById("com.android.messaging:id/conversation_snippet");
		ac.longPress(ele).perform().release();
		Thread.sleep(2000);
		boolean flag = driver.findElementById("com.android.messaging:id/action_add_contact").isDisplayed();
		if(flag)
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
	 * @throws MalformedURLException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		// TODO Auto-generated method stub
		Tutorial19 obj = new Tutorial19();
		obj.setUp();
		obj.longPress();
		obj.tearDown();
	}

}
