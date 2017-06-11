/**
 * 
 */
package com.automationlearning;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

/**
 * @author CHIRAG
 *
 */
public class Tutorial20 {

	AppiumDriver<MobileElement> driver;

	public void setUp() throws MalformedURLException
	{
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("platformName", "Android");
		cap.setCapability("deviceName", "Nexus 9");
		cap.setCapability("appPackage", "com.android.messaging");
		cap.setCapability("appActivity", "com.android.messaging.ui.conversationlist.ConversationListActivity");
		driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"),cap);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void testAddContact() throws InterruptedException, IOException
	{
		try {
			TouchAction ac = new TouchAction(driver);
			MobileElement ele = driver.findElementById("com.android.messaging:id/conversation_snippet");
			ac.longPress(ele).perform().release();
			Thread.sleep(2000);
			boolean flag = driver.findElementById("com.android.messaging:id/action_add_contact12").isDisplayed();
			if(flag)
			{
				System.out.println("Passed");
			}else
			{
				System.out.println("Failed");
				getscreenshot(driver);
			}
		} catch (Exception e) {
			getscreenshot(driver);
		}
		
	}
	
	public void getscreenshot(AppiumDriver<MobileElement> d) throws IOException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		Date date = new Date();
		String fileName = sdf.format(date);
		File des = d.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(des, new File(System.getProperty("user.dir")+"//Screenshot//"+fileName+".png"));
		System.out.println("Screenshot is captured");
	}
	
	public void tearDown()
	{
		driver.quit();
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		Tutorial20 obj = new Tutorial20();
		obj.setUp();
		obj.testAddContact();
		obj.tearDown();
	}

}
