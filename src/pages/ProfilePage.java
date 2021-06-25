package pages;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasicPage {

	public ProfilePage(WebDriver driver, JavascriptExecutor js, WebDriverWait wait) {
		super(driver, js, wait);
	}

	public WebElement getProfile() {
		return driver.findElement(By.xpath("//*[@class='is-active']/a"));
	}

	public WebElement getFirstName() {
		return driver.findElement(By.name("user_first_name"));
	}

	public WebElement getLastName() {
		return driver.findElement(By.name("user_last_name"));
	}

	public WebElement getAddress() {
		return driver.findElement(By.name("user_address"));
	}

	public WebElement getPhoneNo() {
		return driver.findElement(By.name("user_phone"));
	}

	public WebElement getZip() {
		return driver.findElement(By.name("user_zip"));
	}

	public Select getCountry() {
		Select countrySelect = new Select(driver.findElement(By.id("user_country_id")));
		return countrySelect;
	}

	public Select getState() {
		Select stateSelect = new Select(driver.findElement(By.id("user_state_id")));
		return stateSelect;
	}

	public Select getCity() {
		Select citySelect = new Select(driver.findElement(By.id("user_city")));
		return citySelect;
	}

	private WebElement getProfileForm() {
		return this.driver.findElement(By.id("profileInfoFrmBlock"));
	}

	public WebElement getSaveBtn() {
		return this.getProfileForm().findElement(By.name("btn_submit"));
	}

	public WebElement getUploadImgBtn() {
		return driver.findElement(By.xpath("//*[@class='hover-elemnts']/a"));
	}

	public WebElement getRemoveImg() {
		return driver.findElement(By.className("remove"));
	}

	public void selectCountry(String country) {
		getCountry().selectByVisibleText(country);
	}

	public void selectState(String state) {
		getState().selectByVisibleText(state);
	}

	public void selectCity(String city) {
		getCity().selectByVisibleText(city);
	}

	public void uploadImg() throws IOException {

		js.executeScript("arguments[0].click();", getUploadImgBtn());

		String path = new File("images/cvet.png").getCanonicalPath();
		driver.findElement(By.xpath("//input [@type= 'file']")).sendKeys(path);
	}

	public void removeImg() {
		js.executeScript("arguments[0].click();", getRemoveImg());

	}

	public void editProfile(String firstName, String lastName, String address, String phoneNo, String zip,
			String country, String state, String city) throws InterruptedException {
		this.getFirstName().click();
		this.getFirstName().clear();
		this.getFirstName().sendKeys(firstName);

		this.getLastName().click();
		this.getLastName().clear();
		this.getLastName().sendKeys(lastName);

		this.getAddress().click();
		this.getAddress().clear();
		this.getAddress().sendKeys(address);

		this.getPhoneNo().click();
		this.getPhoneNo().clear();
		this.getPhoneNo().sendKeys(phoneNo);

		this.getZip().click();
		this.getZip().clear();
		this.getZip().sendKeys(zip);

		this.selectCountry(country);
		Thread.sleep(500);
		this.selectState(state);
		Thread.sleep(500);
		this.selectCity(city);

		Thread.sleep(1000);
		this.getSaveBtn().submit();

	}

}
