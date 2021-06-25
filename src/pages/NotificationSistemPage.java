package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotificationSistemPage extends BasicPage {

	public NotificationSistemPage(WebDriver driver, JavascriptExecutor js, WebDriverWait wait) {
		super(driver, js, wait);
	}

	public WebElement getMessage() {
		return driver.findElement(By.xpath(
				"//*[contains(@class, 'alert--success') or contains(@class, 'alert--danger')][contains(@style,'display: block')]"));
	}

	public String returnMessage() {
		return getMessage().getText();

	}

	public void waitMessageDisappear() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement message = driver.findElement(By.xpath("//*[contains(@class, 'system_message')]"));
		wait.until(ExpectedConditions.attributeContains(message, "style", "display: none;"));
	}

}
