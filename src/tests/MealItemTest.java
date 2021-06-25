package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MealItemTest extends BasicTest{
	
	@Test
	public void addMealToCart() throws InterruptedException {
		this.driver.get(baseURL + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		Thread.sleep(1000);
		locationPopupPage.closeLocationDialog();
		Thread.sleep(1000);
		
		mealPage.addMealToCart(4);
		Assert.assertTrue(notificationSistemPage.returnMessage().contains( 
				                                  "The Following Errors Occurred: Please Select Location"));	
	
	    notificationSistemPage.waitMessageDisappear();
	    locationPopupPage.setLocation("City Center - Albany");
	    
		mealPage.addMealToCart(4);
		Assert.assertTrue(notificationSistemPage.returnMessage().contains("Meal Added To Cart"));		
		
}
	@Test
	public void addMealToFavorite() throws InterruptedException {
		this.driver.get(baseURL + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		Thread.sleep(1000);
		locationPopupPage.closeLocationDialog();
		Thread.sleep(1000);
		
		mealPage.addToFavorites();
		Assert.assertTrue(notificationSistemPage.returnMessage().contains("Please login first!"));
		
		this.driver.get(baseURL + "/guest-user/login-form");
		loginPage.login("customer@dummyid.com", "12345678a");
		
		this.driver.get(baseURL + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		Thread.sleep(1000);
		locationPopupPage.closeLocationDialog();
		Thread.sleep(1000);
		
		mealPage.addToFavorites();
		Assert.assertTrue(notificationSistemPage.returnMessage().contains("Product has been added to your favorites."));
		
	}
	
	@Test
	public void clearCart() throws InterruptedException, IOException {
		this.driver.get(baseURL + "/meals");
		Thread.sleep(1000);
		locationPopupPage.closeLocationDialog();
		Thread.sleep(1000);
		
	    locationPopupPage.setLocation("City Center - Albany");
	    
        File file = new File ("data/data(1).xlsx");
		
		FileInputStream fis = new FileInputStream (file);
		
		XSSFWorkbook workbook = new XSSFWorkbook (fis);
		
		XSSFSheet sheet = workbook.getSheet("Meals");

		for (int i = 1; i < 6; i++) {
			String meal  = sheet.getRow(i).getCell(0).getStringCellValue();
			int quantity = (int) sheet.getRow(i).getCell(1).getNumericCellValue();	
			
            driver.navigate().to(meal);	
            mealPage.addMealToCart(quantity);
            
            SoftAssert sa = new SoftAssert();
            sa.assertTrue(notificationSistemPage.returnMessage().contains("Meal Added To Cart"), "The message is not visible");
            
            Thread.sleep(1000);
            
            cartSummaryPage.removeMeals();
            sa.assertTrue(notificationSistemPage.returnMessage().contains("All meals removed from Cart successfully"), "The message is not visible");

		}
            
            
            
	
	}
	
}