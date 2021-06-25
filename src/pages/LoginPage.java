package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasicPage {

	public LoginPage(WebDriver driver, JavascriptExecutor js, WebDriverWait wait) {
		super(driver, js, wait);
	}

	public WebElement getEmail() {
		return driver.findElement(By.name("username"));
	}

	public WebElement getPassword() {
		return driver.findElement(By.name("password"));
	}

	public WebElement getLoginBtn() {
		return driver.findElement(By.name("btn_submit"));
	}

	public void login(String email, String password) throws InterruptedException {

		this.getEmail().click();
		this.getEmail().clear();
		this.getEmail().sendKeys(email);
		
		Thread.sleep(1000);
		
		this.getPassword().click();
		this.getPassword().clear();
		this.getPassword().sendKeys(password);

		this.getLoginBtn().click();

	}
//	Login Page:
//		get metode za sve potrebne elemente
//		metodu koja prijavljuje korisnika na sistem - kao parametri se prosleđuju imejl i lozinka
//		Notification Sistem Page:
//		get metodu za element koji prikazuje poruku
//		//*[contains(@class, 'alert--success') or contains(@class, 'alert--danger')][contains(@style,'display: block')]
//		metodu koja vraća poruku koja se nalazi u obaveštenju
//		metodu koja čeka da obaveštenje nestane
//		čeka se da element //*[contains(@class, 'system_message')]
//		za atribut style dobije vrednost  "display: none;"

}
