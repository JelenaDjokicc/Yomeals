package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthPage extends BasicPage {

	public AuthPage(WebDriver driver, JavascriptExecutor js, WebDriverWait wait) {
		super(driver, js, wait);
	}

	public WebElement getAccount() {
		return driver.findElement(By.xpath("//div[@class = 'right-head']/div[2]"));
	}

	public WebElement getLogout() {
		return driver.findElement(By.linkText("Logout"));
	}

	public void logout() {
		this.getAccount().click();
		this.getLogout().click();
	}
}
