package com.flipkart.page;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.flipkart.utils.Constant;

public class SortPage {
	
	WebDriver driver;
	
	public SortPage(WebDriver webdriver)
	{
		driver = webdriver;
		PageFactory.initElements(webdriver, this);

	}
	
	BasePage basepage = new BasePage();

	@FindBy(xpath = "//input[@name='q']")
	WebElement searchBox;
	
	@FindBy(xpath = "//span[text()='Next']")
	WebElement nextPageButton;
	
	@FindBy(xpath = "//div[@class='hl05eU']/div[1]")
	List<WebElement> priceElements;
	
	public void serachItem(String itemname) {
		searchBox.sendKeys(itemname);
		searchBox.sendKeys(Keys.ENTER);
	}
	
	
	public void sortItem(String sortingType) {
		WebElement sort = driver.findElement(By.xpath("//div[contains(text(),'"+sortingType+"')]"));
		sort.click();
	}
	
	public void validatePrice(int pageLimit) {
	    for (int page = 1; page <= pageLimit; page++) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Get product prices on the current page
            priceElements = driver.findElements(By.xpath("//div[@class='hl05eU']/div[1]"));

            // Validate ascending order of prices on the current page
            double prevPrice = 0;
            for (WebElement priceElement : priceElements) {
                String priceText = priceElement.getText().replace("₹", "").replace(",", "").trim();
                if (!priceText.isEmpty()) {
                    double currentPrice = Double.parseDouble(priceText);
                    if (currentPrice < prevPrice) {
                        System.out.println("Prices are not in ascending order on page No " + page);
                        break;
                    }
                    prevPrice = currentPrice;
                }
                else
                	System.out.println("Price in Unavailable...");
            }

            System.out.println("Page No " + page + " is sorted correctly in ascending order.");

            // Click next page if not the last one
            if (page < pageLimit) {
                
                nextPageButton.click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='_1AtVbE']")));
            }
        }
	}
	
	public void verifySortingPrice(String itemname,String sortingType,int pageLimit)
	{
		Constant constant = new Constant();
		basepage.openWebsite(constant.url);
		serachItem(itemname);
		sortItem(sortingType);
		validatePrice(pageLimit);
	}
	

}
