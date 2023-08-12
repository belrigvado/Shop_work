package com.example.shop.e2e;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ShopPageLoader extends PageLoader {
	public ShopPageLoader(WebDriver driver) {
		super(driver);
	}

	@Override
	public boolean loadPage() {
		return Loader.load(() -> !driver.findElements(By.id("greetings")).isEmpty());
	}
}
