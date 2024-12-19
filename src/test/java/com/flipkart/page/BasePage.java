package com.flipkart.page;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	public static WebDriver driver;
	

	public WebDriver getDriver() {
		driver = WebDriverManager.chromedriver().create();
		return driver;
	}
	public void openWebsite(String url)
	{
		 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		 driver.manage().window().maximize();
		 driver.get(url);
	}
	

	public void tearDown()
	{
		driver.quit();
	}
}
