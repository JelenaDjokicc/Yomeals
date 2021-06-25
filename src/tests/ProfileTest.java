package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTest extends BasicTest {

	@Test
	public void editProfile() throws InterruptedException, IOException {
		this.driver.get(baseURL + "/guest-user/login-form");
		Thread.sleep(2000);
		locationPopupPage.closeLocationDialog();
		Thread.sleep(1000);
		loginPage.login("customer@dummyid.com", "12345678a");
		Assert.assertTrue(notificationSistemPage.returnMessage().contains("Login Successfull"));
		Thread.sleep(2000);

		this.driver.get(baseURL + "/member/profile");
		profilePage.editProfile("Jana", "Milic", "address", "5945949", "6289", "India", "Delhi", "New Delhi");
		Assert.assertTrue(notificationSistemPage.returnMessage().contains("Setup Successful"));
		Thread.sleep(2000);
		
		authPage.logout();
		Assert.assertTrue(notificationSistemPage.returnMessage().contains("Logout Successfull"));
	}

	@Test
	public void changeProfileImage() throws InterruptedException, IOException {
		this.driver.get(baseURL + "/guest-user/login-form");
		Thread.sleep(2000);
		locationPopupPage.closeLocationDialog();
		Thread.sleep(1000);
		loginPage.login("customer@dummyid.com", "12345678a");
		Assert.assertTrue(notificationSistemPage.returnMessage().contains("Login Successfull"));
		Thread.sleep(2000);

		this.driver.get(baseURL + "/member/profile");
		profilePage.uploadImg();
		Assert.assertTrue(notificationSistemPage.returnMessage().contains("Profile Image Uploaded Successfully"));
		notificationSistemPage.waitMessageDisappear();

		profilePage.removeImg();
		Assert.assertTrue(notificationSistemPage.returnMessage().contains("Profile Image Deleted Successfully"));
		notificationSistemPage.waitMessageDisappear();

		authPage.logout();
		Assert.assertTrue(notificationSistemPage.returnMessage().contains("Logout Successful"));
		

	}
}