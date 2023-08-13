package com.example.shop.e2e;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

//@SpringBootTest
public class E2EShopTest {
	private static final String SITE = "http://localhost:4000/";
	private static final String TEST_SHOP_NAME = "TestShop";

	private WebDriver driver;

	private void loadSite(WebDriver driver) {
		driver.manage().window().maximize();
		driver.get(SITE);
		if (!(new ShopPageLoader(driver).loadPage()))
			Assertions.fail();
	}

	private int countShops() {
		WebElement shopsDiv = driver.findElements(By.id("shops_div")).stream().findAny().orElse(null);
		if(shopsDiv == null) {
			Assertions.fail();
		}
		return shopsDiv.findElements(By.tagName("tr")).size();
	}

	@BeforeEach
	public void init() {
		// инициализируем веб-драйвер
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
	}
	@Test
	public void deleteEmptyTest() throws Exception {
		loadSite(driver);
		List<WebElement> buttons = driver.findElements(By.tagName("button"));
		WebElement deleteButton = null;
		for(WebElement button : buttons) {
			if(button.getText().equals("Delete shop")) {
				deleteButton = button;
				break;
			}
		}
		Assertions.assertNotNull(deleteButton);
		deleteButton.click();
		Thread.sleep(1000);

		List<WebElement> validationElements = driver.findElements(By.id("id_validation"));
		WebElement validationElement = validationElements.stream().filter(ve -> ve.findElements(By.tagName("p")).stream().anyMatch(p -> "Must be not empty".equals(p.getText()))).findAny().orElse(null);

		Assertions.assertNotNull(validationElement);
		Assertions.assertTrue(validationElement.getAttribute("style").contains("visibility: visible;"));
	}

	/**
	 * Проверка обновления списка магазинов при нажатии кнопки Refres
	 * @throws Exception
	 */
	@Test
	public void refreshTest() throws Exception {
		loadSite(driver);
		int shopCount = countShops();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("shopName", TEST_SHOP_NAME);
		jsonObject.put("shopPublic", true);

		given().contentType("application/json").body(jsonObject.toJSONString())
				.when().post("http://localhost:4000/shops/add");

		List<WebElement> buttons = driver.findElements(By.tagName("button"));
		WebElement refreshButton = null;
		for(WebElement button : buttons) {
			if(button.getText().equals("Refresh")) {
				refreshButton = button;
				break;
			}
		}
		Assertions.assertNotNull(refreshButton);
		refreshButton.click();
		Thread.sleep(1000);
		Assertions.assertEquals(shopCount+1, countShops());
	}

	/**
	 * Проевряем наличие соцсетей в footer'е
	 */
	@Test
	public void headerTest() {
		loadSite(driver);
		WebElement footer = driver.findElement(By.tagName("header"));
		int headerLinksById = 0;
		for (WebElement webElement : footer.findElements(By.className("a"))) {
			String href = webElement.getAttribute("href");
			if (href != null) {
				if (href.startsWith("#")) {
					headerLinksById++;
				}
			}
		}
		Assertions.assertTrue(headerLinksById >= 2);
	}

	/**
	 * Проевряем наличие соцсетей в footer'е
	 */
	@Test
	public void footerTest() {
		loadSite(driver);
		WebElement footer = driver.findElement(By.tagName("footer"));
		Map<String, Boolean> socialNetworks = Map.of("telegram.org", true, "vk.com", true);
		for (WebElement webElement : footer.findElements(By.className("a"))) {
			String href = webElement.getAttribute("href");
			if (href != null) {
				for (Map.Entry<String, Boolean> entry : socialNetworks.entrySet()) {
					if (!entry.getValue()) {
						socialNetworks.put(entry.getKey(), true);
						break;
					}
				}
			}
		}
		Assertions.assertTrue(socialNetworks.values().stream().allMatch(b -> b));
	}

	@AfterEach
	public void destroy() {
		driver.close();
	}
}
