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


/**
 * @author sarvagya.singhal
 *
 */
public class TC_010 extends BaseClass{
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
	
	@Test 
	public void cartModuleTest() throws InterruptedException {
		Log.startTestCase("Verifying added items on the cart page");
		homepage.clickonSelectNow();
		Thread.sleep(6000);
		locationpage.locationMethod("ANS Books, Kannappar Thidal", "121");
		Thread.sleep(6000);
		homepage.typeItemsInTextBox("Burger");
		Thread.sleep(3000);
		customizationpage.selectAddonItems();
		Thread.sleep(4000);
		customizationpage.selectRodioButton();
		Thread.sleep(5000);
		homepage.clickonViewCartButton();
		Thread.sleep(5000);
		String actualResult = cartpage.checkAllItems();
		String expectedResult = "McAloo Tikki Burger\n"
				+ "Coke,Extra Tomato Ketchup Sachet,Extra Cheese";
		Assert.assertEquals(actualResult, expectedResult);
		
		ExtentTest TC_010 =  extent.createTest("Verifying the item and add-on items in the cart page");
		if (actualResult.equals(expectedResult)) {
			TC_010.pass("The items is displayed as expected.");
		}
		else {
			TC_010.fail("The items is not displayed.");
		}
		
	}

}
