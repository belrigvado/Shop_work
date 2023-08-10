package com.example.shop;

import com.example.shop.models.ShopPojo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShopPojoUnitTest {
	private static final long TEST_SHOP_ID = 1470258369;
	private static final String TEST_SHOP_NAME = "TestShop";

	@Test
	public void shopIdTest() {
		ShopPojo shopPojo = new ShopPojo();
		Assertions.assertNull(shopPojo.getShopId());
		shopPojo.setShopId(TEST_SHOP_ID);
		Assertions.assertEquals(TEST_SHOP_ID, shopPojo.getShopId());
		shopPojo.setShopId(null);
		Assertions.assertNull(shopPojo.getShopId());
	}

	@Test
	public void shopNameTest() {
		ShopPojo shopPojo = new ShopPojo();
		Assertions.assertNull(shopPojo.getShopName());
		shopPojo.setShopName(TEST_SHOP_NAME);
		Assertions.assertEquals(TEST_SHOP_NAME, shopPojo.getShopName());
		shopPojo.setShopName(null);
		Assertions.assertNull(shopPojo.getShopName());
	}

	@Test
	public void shopPublicTest() {
		ShopPojo shopPojo = new ShopPojo();
		Assertions.assertFalse(shopPojo.getShopPublic());
		shopPojo.setShopPublic(true);
		Assertions.assertTrue(shopPojo.getShopPublic());
		shopPojo.setShopPublic(false);
		Assertions.assertFalse(shopPojo.getShopPublic());
	}
}
