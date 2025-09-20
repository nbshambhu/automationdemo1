package com.automationDemo.automationdemo1;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCase {
	public static WebDriver webDriver;

	@BeforeMethod
	public WebDriver CreateWebdriver(String browerName) {
		switch (browerName.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			webDriver = new ChromeDriver();
			break;
		case "firefox":
		case "mozilla":
		case "mozilla-firefox":
			WebDriverManager.firefoxdriver().setup();
			webDriver = new FirefoxDriver();
			break;
		case "edge":
		case "msedge":
		case "microsoftedge":
			WebDriverManager.edgedriver().setup();
			webDriver = new EdgeDriver();
			break;
		case "ie":
		case "msie":
		case "microsoftie":
			WebDriverManager.iedriver().setup();
			webDriver = new InternetExplorerDriver();
			break;
		default:
			throw new IllegalArgumentException("Unsupported Browser" + browerName);
		}
		System.out.println("Created a Driver instance for " + browerName);
		return webDriver;
	}

	@Test
	public Boolean launchBrowser(WebDriver webDriver) {
		try {
			String url = "https://procurement.gosmartagro.com/#/login";
			webDriver.get(url);;
			Duration dr = Duration.ofSeconds(10);
			WebDriverWait wt = new WebDriverWait(webDriver, dr);
			wt.until(webdriver -> ((JavascriptExecutor) webdriver).executeScript("return document.readyState", null)
					.equals("completed"));
			webDriver.manage().timeouts().implicitlyWait(dr);
			webDriver.manage().window().fullscreen();
			System.out.println("Opened Browser and navigated to " + url);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Test
	public Boolean Login(WebDriver webDriver, String userName, String password) {
		try {
			WebElement wl = webDriver.findElement(By.name("userName"));
			wl.clear();
			wl.click();
			wl.sendKeys(userName);
			wl = webDriver.findElement(By.name("password"));
			wl.clear();
			wl.click();
			wl.sendKeys(password);
			wl = webDriver.findElement(By.xpath("//button[text()=\"Sign In\"]"));
			wl.click();
			System.out.println("Login Successfull");
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@AfterMethod
	public void quit() {
		webDriver.quit();
	}
}
