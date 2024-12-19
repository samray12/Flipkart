package com.flipkart.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.flipkart.page.AddToCartPage;
import com.flipkart.page.BasePage;
import com.flipkart.page.SortPage;

public class PageTest {
	
	BasePage basepage = new BasePage();
	WebDriver driver = basepage.getDriver();
	
	SortPage sortpage = new SortPage(driver);
	AddToCartPage addtocartpage = new AddToCartPage(driver);

	@Test
	public void sortingPrice() {
		sortpage.verifySortingPrice("shoes", "Low to High", 2);
	}
	
	@Test
	public void addTocart() {
		addtocartpage.validateaddTocart("shoes", "Low to High");
	}
	
	@AfterTest
	public void closeBrowser() {
		basepage.tearDown();
	}
}
