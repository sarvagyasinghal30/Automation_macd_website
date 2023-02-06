/**
 * 
 */
package com.macd.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.macd.base.BaseClass;
import com.macd.utilities.Log;

/**
 * @author sarvagya.singhal
 *
 */
public class LocationPage extends BaseClass {
	
	private By searchlocationTextBox = By.xpath("//input[@id=\"menu-search\"]");
	private By donebutton = By.xpath("//button[@class=\"save-address\"]");
	private By buildingDetailsTextBox = By.id("building");
	private By saveAddress = By.xpath("//button[@class=\"save-address\"]");
	private By errormessage = By.xpath("//span[@class=\"error\"]");
	private By closeButton = By.xpath("//button[contains(text(),' Close ')]");
	private By currentLocation = By.xpath("//div[@class=\"use-current-location margn-bottom\"]");
	
	public LocationPage(WebDriver driver1) {
		this.driver = driver1;
	}
	

	
	public void locationMethod(String address, String builDetails) throws InterruptedException {
		// Entering Address in the text box
		Log.info("Enter the address into the text box");
		driver.findElement(searchlocationTextBox).sendKeys(address);
		Thread.sleep(2000);
		
		//Selecting the address from the suggested list 
		Log.info("Select the address from the list of addresses.");
		driver.findElement(By.xpath("(//p[@class=\"store-sub-text margn-bottom\"])[1]")).click();
		Thread.sleep(2000);
		
		//After selecting click on "Done" button
		Log.info("Click on the \"Done\" button.");
		driver.findElement(donebutton).click();
		Thread.sleep(2000);
		
		
		//Enter Building Details into the text box
		Log.info("Enter the Building details into the text box");
		driver.findElement(buildingDetailsTextBox).sendKeys(builDetails);
		Thread.sleep(5000);
		
		
		//Click on "Save and Select Address" button
		Log.info("Click on the \"Save and Select Address\" button");
		driver.findElement(saveAddress).click();
		}	
		
	
	public String checkMessage(String address) throws InterruptedException {
		
		// Entering the Address which is out of range in the text box.
		Log.info("Enter the address into the text box");
		driver.findElement(searchlocationTextBox).sendKeys(address);
		Thread.sleep(4000);
		
		//Selecting the address from the suggested list.
		Log.info("Select the address from the list of addresses.");
		driver.findElement(By.xpath("(//p[@class=\"store-sub-text margn-bottom\"])[1]")).click();
		Thread.sleep(2000);
		
		//After selecting click on "Done" button.
		Log.info("Click on the \"Done\" button.");
		driver.findElement(donebutton).click();
		Thread.sleep(6000);
		
		//Storing the message into the variable.
		Log.info("Verify the message");
		String err_message = driver.findElement(errormessage).getText();
		Thread.sleep(7000);
		
		
		//Click on "Close" button.
		Log.info("Click on the \"Close\" button.");
		driver.findElement(closeButton).click();
		return err_message;
	
	}
	
	public String current_Location() throws InterruptedException {
		
		//Click on the text box
		Log.info("Click on the text box");
		driver.findElement(searchlocationTextBox).click();
		Thread.sleep(2000);
		
		//Click on "Use Current Location" option.
		Log.info("Click on the \"Use Current Locatio\" option");
		driver.findElement(currentLocation).click();
		Thread.sleep(2000);
		
		
		//After selecting click on "Done" button.
		Log.info("Click on the \"Done\" button.");
		driver.findElement(donebutton).click();
		Thread.sleep(2000);
		
		
		//Storing the message into the variable.
		String err_message = driver.findElement(errormessage).getText();
		Log.info("Click on the \"Close\" button.");
		
		Thread.sleep(3000);
		//Click on "Close" button.
		driver.findElement(closeButton).click();
		return err_message;
	}
	
	public void invalidAddress(String address) throws InterruptedException {
		try {
		// Entering special characters in the text box
		driver.findElement(searchlocationTextBox).sendKeys(address);
		Thread.sleep(5000);
		//checking the error message
		driver.findElement(By.id("error message")).isDisplayed();
		}
		catch(NoSuchElementException e) {
			System.out.println(e);
			// creating an object for Extent report
			ExtentTest TC_004 =  extent.createTest("Verify error message, after typing only special charactes into the text box");
			TC_004.fail("The application is not displaying any error message as expected.");
		}
	}
	public void click_On_backIcon() {
		try {
		Log.info("click on back icon");
		// click on back button
		driver.findElement(By.className("back-icon")).click();
		// checking the element
		driver.findElement(By.id("map")).isDisplayed();
		}
		catch(Exception e){
			System.out.println(e);
			// creating an object for Extent report
			ExtentTest TC_004 =  extent.createTest("Back button is not working");
			TC_004.fail("The application is not functioning as expected; it has become stuck. ");
			Assert.fail("The application has become stuck. ");
		}
	}

}
