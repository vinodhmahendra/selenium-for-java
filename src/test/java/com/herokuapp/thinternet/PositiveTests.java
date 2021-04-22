package com.herokuapp.thinternet;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveTests {
	
	@Test
	public void loginTest() throws Exception {
		
		System.out.println("starting login test");
		// create driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
//		WebDriver driver = 
//				new RemoteWebDriver(new URL("http://10.128.0.2:4444/wd/hub"),DesiredCapabilities.chrome());
		//sleep for 3 seconds
		sleep(3000);
		
		//maximize browser window
		driver.manage().window().maximize();
		
		String url = "https://the-internet.herokuapp.com/login";
		//open test page
		driver.get(url );
		System.out.println("page is opened");
		
		//sleep for 3 seconds
		sleep(2000);
		
		
		//enter username
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");
		sleep(1500);
		
		//enter password
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("SuperSecretPassword!");
		sleep(3000);
		
		//click login  button
		WebElement loginButton = driver.findElement(By.tagName("button"));
		loginButton.click();
		sleep(5000);
		
		//verifications
		
		//new url
		String expetedurl = "https://the-internet.herokuapp.com/secure";
//		String expetedurl = "https://the-internet.herokuapp.com/secure-broken"; error
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expetedurl,"Actual page url is not the same as expected");
		
		//logout button is visible
		WebElement logoutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
		Assert.assertTrue(logoutButton.isDisplayed(), "LogOut Button is not visible");
	;
	
		//successful login message
		//WebElement successMessage = driver.findElement(By.cssSelector("#flash"));
		WebElement successMessage = driver.findElement(By.xpath("//div[@id='flash']"));
		String expectedMessage = "You logged into a secure area!";
		String actualMessage = successMessage.getText();
//		Assert.assertEquals(actualMessage, expectedMessage, "Actual message is not the same as expected");
		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Actual message does not contain expected message.\n Actual Message :" +actualMessage+
				"\nExpected Message:" +expectedMessage);
		//close browser
		driver.quit();
		
	}

	private void sleep(long m) {
		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
