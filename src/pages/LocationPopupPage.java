package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocationPopupPage extends BasicPage {

	public LocationPopupPage(WebDriver driver, JavascriptExecutor js, WebDriverWait wait) {
		super(driver, js, wait);
	}

	public WebElement getLocation() {
		return driver.findElement(By.xpath("//*[@class='location-selector']/a"));
	}

	public WebElement getCloseElement() {
		return driver.findElement(By.className("close-btn-white"));
	}

	public WebElement getKeyword() {
		return driver.findElement(By.xpath("//*[@id='locality_keyword']"));
	}

	public WebElement getLocationItem(String locationName) {
		return driver.findElement(By.xpath("//li/a[contains(text(), '\" + locationName + \"')]/.."));
	}

	public WebElement getLocationInput() {
		return driver.findElement(By.xpath("//*[@id='location_id']"));
	}

	public WebElement getSubmit() {
		return driver.findElement(By.xpath("//*[@name='btn_submit']"));
	}

	public void clickLocation() {
		getLocation().click();
	}

	public void setLocation(String locationName) {
		getLocation().click();
		getKeyword().click();
		String dataValue = getLocationItem(locationName).getAttribute("data-value");
		js.executeScript("arguments[0].value=arguments[1]", getLocationInput(), dataValue);
		js.executeScript("arguments[0].click();", getSubmit());
	}

	public void closeLocationDialog() {
		getCloseElement().click();
	}

}
