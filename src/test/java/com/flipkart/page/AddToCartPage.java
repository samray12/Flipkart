package com.flipkart.page;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.flipkart.utils.Constant;

public class AddToCartPage {

WebDriver driver;
	
	public AddToCartPage(WebDriver webdriver)
	{
		driver = webdriver;
		PageFactory.initElements(webdriver, this);

	}

	BasePage basepage = new BasePage(); 
	Constant constant = new Constant();
	
	@FindBy(xpath = "//div[contains(@data-id,'SHOGHYKHGAAGTDZH')]")
	WebElement secondProduct;
	
	@FindBy(xpath = "//div[contains(@data-id,'SHOGHYJ2FMUHJ5HJ')]")
	WebElement thirdProduct;
	
	@FindBy(xpath = "//button[contains(text(),'Add to cart')]")
	WebElement addTocart;
	
	@FindBy(xpath = "//span[text()='Cart']")
	WebElement cart;
	
	@FindBy(xpath = "//div[@class='gE4Hlh']/a")
	List<WebElement> cartProductNames;
	
	@FindBy(xpath = "//div[@class='hl05eU']/div[1]")
	List<WebElement> cartProductPrices;
	
	@FindBy(xpath = "//div[@class='PWd9A7 xvz6eC']/div[@class='_1Y9Lgu']/span")
	WebElement totalPriceElement;
	
	
	
	 public  void addProductsToCart() {
		 
		 	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	         // Add the second product to the cart
	         secondProduct.click();
	        
	         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addTocart);
	        
	         wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Add to cart')]")));
	         addTocart.click();
	         System.out.println("Second product added to cart");
	         // Go back to the search results
	         driver.navigate().back();
	         // Add the third product to the cart
	         thirdProduct.click();
	         wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Add to cart')]")));
	         addTocart.click();
	         System.out.println("Third product added to cart");
	    }
	 
	 public  void validateCart() {
	        System.out.println("Going to Cart for validating products and total sum");
	        	// Click on the cart icon to go to the Cart page
	            WebElement cartIcon = driver.findElement(By.xpath("//a[@href='/viewcart?otracker=Cart_Icon_click']"));
	            cartIcon.click();
	            
	            // Validate that the correct products are added
	            if (cartProductNames.size() == 2 && cartProductPrices.size() == 2) {
	                System.out.println("Products in cart are correct.");

	                // Get the price of each product
	                double totalSum = 0;
	                for (int i = 0; i < cartProductPrices.size(); i++) {
	                    String priceText = cartProductPrices.get(i).getText().replace("₹", "").replace(",", "").trim();
	                    double price = Double.parseDouble(priceText);
	                    totalSum = totalSum + price;
	                    System.out.println(cartProductNames.get(i).getText() + ": ₹" + price);
	                }

	                // Validate the total sum
	               String totalPriceText = totalPriceElement.getText().replace("₹", "").replace(",", "").trim();
	                double totalPrice = Double.parseDouble(totalPriceText);

	                if (totalPrice == totalSum) {
	                    System.out.println("Total sum is correct: ₹" + totalSum);
	                } else {
	                    System.out.println("Total sum is incorrect. Expected: ₹" + totalSum + ", but got: ₹" + totalPrice);
	                }
	            } else {
	                System.out.println("Error: Incorrect number of products in the cart.");
	            }

	        } 
	    
	 public void validateaddTocart(String itemname,String sortingType) {
		 
		 basepage.openWebsite(constant.url);
		 SortPage sortpage = new SortPage(driver);
		 sortpage.serachItem(itemname);
		 sortpage.sortItem(sortingType);
		 addProductsToCart();
		 validateCart();
	 }
	 

}
