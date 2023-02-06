/**
 * 
 */
package com.macd.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.macd.base.BaseClass;
import com.macd.pageobjects.CartPage;
import com.macd.pageobjects.CustomizationPage;
import com.macd.pageobjects.HomePage;
import com.macd.pageobjects.LocationPage;
import com.macd.utilities.Log;
import com.macd.utilities.dataProvider;

/**
 * @author sarvagya.singhal
 *
 */
public class TC_011 extends BaseClass{
	HomePage homepage= new HomePage(driver);
	LocationPage locationpage = new LocationPage(driver);
	CustomizationPage customizationpage = new CustomizationPage(driver);
	CartPage cartpage = new CartPage(driver);
	

	@Parameters("browser")
	//Before method
	@BeforeMethod
	public void setUp(String browser) {
		// Launching the app and setting implicit wait for the driver
		launchApp(browser);
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test ( dataProvider = "addresstest" , dataProviderClass = dataProvider.class)
	public void cartModuleTest(String address, String build_Details, String foodItem) throws InterruptedException {
		Log.startTestCase("Verifying the error message ");
		homepage.clickonSelectNow();
		Thread.sleep(2000);
		locationpage.locationMethod(address, build_Details);
		Thread.sleep(6000);
		homepage.typeItemsInTextBox(foodItem);
		Thread.sleep(3000);
		customizationpage.selectAddonItems();
		Thread.sleep(4000);
		customizationpage.selectRodioButton();
		Thread.sleep(5000);
		homepage.clickonViewCartButton();
		Thread.sleep(5000);
		String actualResult = cartpage.clearSelectedItems();
		String expectedResult = "Are you sure you want to remove all items?";
		Assert.assertEquals(actualResult,expectedResult);
		ExtentTest TC_011 =  extent.createTest("Verifying the message after clicking on \"clear All\" link");
		if (actualResult.equals(expectedResult)) {
			TC_011.pass("The user is receiving the message as expected.");
		}
		else {
			TC_011.fail("The user is not receiving the message.");
		}
		
	}

}
