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

//		get metoda za element koji prikazuje lokaciju u hederu 		
	public WebElement getLocation() {
		return driver.findElement(By.xpath("//*[@class='location-selector']/a"));
	}

//		get metodu za close element	
	public WebElement getCloseElement() {
		return driver.findElement(By.className("close-btn-white"));
	}

//			get metode potrebne za implementaciju metode u kojoj se postavlja lokacija

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
//	metodu koja otvara iskačući dijalog
//	klikom na lokaciju iz hedera
	public void clickLocation () {
		getLocation().click();
	}		
//	metodu koja postavlja lokaciju - naziv lokacije (locationName) se prosleđuje kao parametar metode
//	metoda prvo klikne na element keyword element
//	čita vrednost data-value atributa location item elementa	
//	postavlja lokaciju izvršavajući JavaScript kod
//	Skripta: arguments[0].value=arguments[1]
//	prvi argument skripte je location input
//	drugi argument skripte je vrednost pročitanog atributa iz drugog koraka.
//	Klikće na submit element preko skripte arguments[0].click();
	public void setLocation (String locationName) {
		getKeyword().click();
		String dataValue = getLocationItem(locationName).getAttribute("data-value");
		js.executeScript("arguments[0].value=arguments[1]", getLocationInput(), dataValue);
		js.executeScript("arguments[0].click();", getSubmit());
	}
//	metodu koja zatvara iskačući dijalog, klikom na X dugme
	public void closeLocationDialog () {
		getCloseElement().click();
	}

}
