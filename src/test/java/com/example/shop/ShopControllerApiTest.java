package com.example.shop;

import com.example.shop.controllers.ShopController;
import com.example.shop.models.ShopPojo;
import net.minidev.json.JSONObject;
import net.minidev.json.reader.JsonWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
		properties = {
				"server.port=8080"
		}
)
public class ShopControllerApiTest {
	private static final int OK_CODE = 200;
	private static final int DELTED_CODE = 204;
	private static final String TEST_SHOP_NAME = "TestShop";

	@Autowired
	private ShopController controller;

	@Test
	public void getShopsTest() {
		when().get("http://localhost:8080/shops/all")
				.then().statusCode(OK_CODE)
				.body("size()", greaterThanOrEqualTo(0));
	}

	@Test
	public void getShopTest() {
		ShopPojo shop = controller.getShops().getBody().stream().findAny().orElseThrow();
		when().get("http://localhost:8080/shops/" + shop.getShopId())
				.then().statusCode(OK_CODE)
				.body("shopId", equalTo(shop.getShopId().intValue()),
						"shopName", equalTo(shop.getShopName()),
						"shopPublic", equalTo(shop.getShopPublic()));
	}

	@Test
	public void addShopTest() {
		List<ShopPojo> all = controller.getShops().getBody();
		int allSize = all.size();
		ShopPojo shop = new ShopPojo();
		shop.setShopName(TEST_SHOP_NAME);
		shop.setShopPublic(true);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("shopName", shop.getShopName());
		jsonObject.put("shopPublic", shop.getShopPublic());

		given().contentType("application/json").body(jsonObject.toJSONString())
				.when().post("http://localhost:8080/shops/add")
				.then().statusCode(OK_CODE);
		all = controller.getShops().getBody();
		Assertions.assertTrue(all.stream().anyMatch(s -> TEST_SHOP_NAME.equals(shop.getShopName()) && s.getShopPublic()));
		Assertions.assertEquals(allSize + 1, all.size());
	}

	@Test
	public void deleteShopTest() {
		List<ShopPojo> all = controller.getShops().getBody();
		long targetId = all.stream().mapToLong(ShopPojo::getShopId).max().orElseThrow();
		int allSize = all.size();
		when().delete("http://localhost:8080/shops/delete/" + targetId)
				.then().statusCode(DELTED_CODE);
		all = controller.getShops().getBody();
		Assertions.assertFalse(all.stream().anyMatch(s -> s.getShopId() == targetId));
		Assertions.assertEquals(allSize - 1, all.size());
	}
}
