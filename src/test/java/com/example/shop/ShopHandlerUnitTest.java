package com.example.shop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class ShopHandlerUnitTest {
	private static final String TEST_SHOP_NAME = "TestShop";
	private static final String TEST_EMPTY_SHOP_NAME = "";

	@Test
	public void checkLengthTest() {
		Assertions.assertTrue(ShopHandler.checkLength(TEST_SHOP_NAME, -5));
		Assertions.assertTrue(ShopHandler.checkLength(TEST_SHOP_NAME, 0));
		Assertions.assertTrue(ShopHandler.checkLength(TEST_SHOP_NAME, 7));
		Assertions.assertTrue(ShopHandler.checkLength(TEST_SHOP_NAME, 8));
		Assertions.assertFalse(ShopHandler.checkLength(TEST_SHOP_NAME, 9));


		Assertions.assertTrue(ShopHandler.checkLength(TEST_EMPTY_SHOP_NAME, -5));
		Assertions.assertTrue(ShopHandler.checkLength(TEST_EMPTY_SHOP_NAME, -1));
		Assertions.assertTrue(ShopHandler.checkLength(TEST_EMPTY_SHOP_NAME, 0));
		Assertions.assertFalse(ShopHandler.checkLength(TEST_EMPTY_SHOP_NAME, 1));
	}

	@Test
	public void checkFirstLetterTest() {
		Assertions.assertTrue(ShopHandler.checkFirstLetter(TEST_SHOP_NAME));
		Assertions.assertFalse(ShopHandler.checkFirstLetter(TEST_SHOP_NAME.toLowerCase()));
	}
}
