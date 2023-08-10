package com.example.shop;

import com.example.shop.models.ShopDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShopDtoUnitTest {
	private static final long TEST_SHOP_ID = 1470258369;
	private static final String TEST_SHOP_NAME = "TestShop";

	@Test
	public void shopIdTest() {
		ShopDto shopDto1 = new ShopDto(TEST_SHOP_ID, TEST_SHOP_NAME, true);
		Assertions.assertEquals(TEST_SHOP_ID, shopDto1.getShopId());
		shopDto1.setShopId(null);
		Assertions.assertNull(shopDto1.getShopId());
		shopDto1.setShopId(TEST_SHOP_ID + 1);
		Assertions.assertEquals(TEST_SHOP_ID+1, shopDto1.getShopId());


		ShopDto shopDto2 = new ShopDto(TEST_SHOP_ID, TEST_SHOP_NAME, false);
		Assertions.assertEquals(TEST_SHOP_ID, shopDto2.getShopId());
		shopDto2.setShopId(null);
		Assertions.assertNull(shopDto2.getShopId());
		shopDto2.setShopId(TEST_SHOP_ID + 1);
		Assertions.assertEquals(TEST_SHOP_ID+1, shopDto2.getShopId());
	}

	@Test
	public void shopNameTest() {
		ShopDto shopDto1 = new ShopDto(TEST_SHOP_ID, TEST_SHOP_NAME, true);
		Assertions.assertEquals(TEST_SHOP_NAME, shopDto1.getShopName());
		shopDto1.setShopName(null);
		Assertions.assertNull(shopDto1.getShopName());
		shopDto1.setShopName(TEST_SHOP_NAME.toLowerCase());
		Assertions.assertEquals(TEST_SHOP_NAME.toLowerCase(), shopDto1.getShopName());

		ShopDto shopDto2 = new ShopDto(TEST_SHOP_ID, TEST_SHOP_NAME, false);
		Assertions.assertEquals(TEST_SHOP_NAME, shopDto2.getShopName());
		shopDto2.setShopName(null);
		Assertions.assertNull(shopDto2.getShopName());
		shopDto2.setShopName(TEST_SHOP_NAME.toLowerCase());
		Assertions.assertEquals(TEST_SHOP_NAME.toLowerCase(), shopDto2.getShopName());
	}

	@Test
	public void shopPublicTest() {
		ShopDto shopDto1 = new ShopDto(TEST_SHOP_ID, TEST_SHOP_NAME, true);
		Assertions.assertTrue(shopDto1.isShopPublic());
		shopDto1.setShopPublic(false);
		Assertions.assertFalse(shopDto1.isShopPublic());

		ShopDto shopDto2 = new ShopDto(TEST_SHOP_ID, TEST_SHOP_NAME, false);
		Assertions.assertFalse(shopDto2.isShopPublic());
		shopDto2.setShopPublic(true);
		Assertions.assertTrue(shopDto2.isShopPublic());
	}
}
