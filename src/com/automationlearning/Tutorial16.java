/**
 * 
 */
package com.automationlearning;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
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
public class Tutorial16 {

	AppiumDriver<MobileElement> driver;
	String path;
	public void setUp()
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
	
	public void getWebViewText()
	{
		System.out.println("First Current Context : "+driver.getContext());
		driver.findElementByAccessibilityId("Views").click();
		for(int i=0;i<=20;i++)
		{
			try {
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				driver.findElementByAccessibilityId("WebView").click();
				break;
			} catch (Exception e) {
				verticalSwipe();
			}
		}
		// Using this method we switch to web view
		switchContext("WEBVIEW");
		String webViewText = driver.findElementByXPath("//a[text()='Hello World! - 1']").getText();
		System.out.println(webViewText);
		
	}
		/**
	 * This method for switch view
	 * 
	 * @param context
	 * @author CHIRAG
	 */
	
	public void switchContext(String context)
	{
		System.out.println("Before Switching : "+driver.getContext());
		Set<String> con = driver.getContextHandles();
		for(String c : con)
		{
			System.out.println("Available Context : "+c);
			if(c.contains(context))
			{
				driver.context(c);
				break;
			}
		}
		System.out.println("After Switching : "+driver.getContext());
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
		Tutorial16 obj = new Tutorial16();
		obj.setUp();
		obj.getWebViewText();
		obj.tearDown();
	}

}
