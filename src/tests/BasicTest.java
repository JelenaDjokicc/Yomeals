package tests;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import pages.AuthPage;
import pages.CartSummaryPage;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.MealPage;
import pages.NotificationSistemPage;
import pages.ProfilePage;

public abstract class BasicTest {

	protected WebDriver driver;
	protected JavascriptExecutor js;
	protected WebDriverWait wait;
	protected Actions actions;
	protected LocationPopupPage locationPopupPage;
	protected String baseURL = "http://demo.yo-meals.com";
	protected LoginPage loginPage;
	protected ProfilePage profilePage;
	protected NotificationSistemPage notificationSistemPage;
	protected AuthPage authPage;
	protected MealPage mealPage;
	protected CartSummaryPage cartSummaryPage;

	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, 2);
		actions = new Actions(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

		loginPage = new LoginPage(driver, js, wait);
		locationPopupPage = new LocationPopupPage(driver, js, wait);
		profilePage = new ProfilePage(driver, js, wait);
		notificationSistemPage = new NotificationSistemPage(driver, js, wait);
		authPage = new AuthPage(driver, js, wait);
		mealPage = new MealPage(driver, js, wait);
		cartSummaryPage = new CartSummaryPage(driver, js, wait);
	}

	@AfterMethod
	public void screenshotAndClean(ITestResult result) throws InterruptedException, IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
			LocalDateTime now = LocalDateTime.now();
			String dt = dtf.format(now);
			TakesScreenshot scrShot = ((TakesScreenshot) driver);
			File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(SrcFile, new File("./screenshots/" + dt + ".jpg"));

		}

		Thread.sleep(500);
		this.driver.quit();
	}
}