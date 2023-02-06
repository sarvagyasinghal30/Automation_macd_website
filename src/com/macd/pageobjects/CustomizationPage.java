package com.macd.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import com.aventstack.extentreports.ExtentTest;
import com.macd.base.BaseClass;
import com.macd.utilities.Log;


public class CustomizationPage extends BaseClass{
	
	By tomatoKetchup = By.xpath("(//p[contains(text(),'1')])[1]");
	By cheese = By.xpath("(//p[contains(text(),'48')])[1]");
	By addToCartButton = By.xpath("//button[contains(text(),'Add to Cart')]");
	By removeButton = By.xpath("(//p[contains(text(),'Remove')])[1]");
	By radioButton = By.xpath("(//p[@class=\"non-strike\"])[1]");

	public CustomizationPage(WebDriver driver1) {
		this.driver = driver1;
	}
	
	public String selectAddonItems() throws InterruptedException {
		// adding add-on items 
		Log.info("Select add-on items");
		driver.findElement(tomatoKetchup).click();
		Thread.sleep(4000);
		driver.findElement(cheese).click();
		Thread.sleep(2000);
		// checking the item is added 
		String color = driver.findElement(cheese).getCssValue("background-color");
		String checkAdd = Color.fromString(color).asHex();
		return checkAdd;
	}
	
	public void selectRodioButton() throws InterruptedException {
		// adding the add-on item
		driver.findElement(radioButton).click();
		Thread.sleep(4000);
		//click on add to cart button
		driver.findElement(addToCartButton).click();
	}
	
	public void removemethod() throws InterruptedException {
		try {
		//Adding add-on item	
		Log.info("Remove the selected add-on items");
		driver.findElement(radioButton).click();
		Thread.sleep(4000);
		// click on remove link 
		driver.findElement(removeButton).click();
		Thread.sleep(3000);
		// checking the error message.
		driver.findElement(By.id("error")).getText();
		Thread.sleep(3000);
		// click on add to cart button
		driver.findElement(addToCartButton).click();
		}
		catch(Exception e) {
			//creating an object for extent report
			ExtentTest TC_013 =  extent.createTest("Verifying the message when the user Remove button");
			TC_013.fail("The user is not receiving any message as expected");
			Assert.fail("The appliction is not throwing any message");
			// click on add to cart button
			driver.findElement(addToCartButton).click();
		}
	}
	
	public boolean removeAddon_item() throws InterruptedException {
		//Adding add-on item
		driver.findElement(radioButton).click();
		Thread.sleep(4000);
		// click on remove link 
		driver.findElement(removeButton).click();
		Thread.sleep(3000);
		// checking the item is removed after clicking on remove link
		boolean isSelected = driver.findElement(radioButton).isSelected();
		Thread.sleep(3000);
		// click on add to cart button
		driver.findElement(addToCartButton).click();
		return isSelected;
	}

}

