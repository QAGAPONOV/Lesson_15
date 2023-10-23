import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Testing {
    private static Home homePage;
    private static Product productPage;
    private static Basket basketPage;
    private static WebDriver driver;

    @Before
    static void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        homePage = new Home(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.wildberries.by/");
    }
    @After
    static void exit() {
        driver.quit();
    }
    @Test
    void test() {
        String productOne = "Казан 10 литров";
        String productTwo = "Казан 12 литров";

        homePage.searchProduct(productOne).clickSearch().selectProduct(0).inBasket()
                .clickQuantity().inHome();

        homePage.searchProduct(productTwo).clickSearch().selectProduct(0)
                .addToBasket().clickQuantity().inHome()
                .inBasket()
                .getProductInform();
        Element.getProductInfo().forEach((k, v) -> System.out.println(k + " | " + v));

        String onePrice = String.format("",Element.getTotalPriceFromInfo());
        System.out.println(onePrice);

        String twoPrice = Basket.getTotalPrice().replaceAll(" ", "");
        System.out.println(twoPrice);

        int totalCountAtAllAtBasket = Integer.parseInt(Basket.getBasketItemsCount());
        int firstProductCountAtBasket = Integer.parseInt(Basket.getProductCounters(0));
        int secondProductCountAtBasket = Integer.parseInt(Basket.getProductCounters(1));
        System.out.println(Basket.getBasketItemsCount());
        System.out.println(Basket.getProductCounters(0) + " | " + Basket.getProductCounters(1));

        Assertions.assertAll(() -> Assertions.assertTrue(Element.getProductInfo().containsKey(productOne)),
                () -> Assertions.assertTrue(Element.getProductInfo().containsKey(productTwo)),
                () -> Assertions.assertTrue(twoPrice.equals(onePrice)),
                () -> Assertions.assertTrue((firstProductCountAtBasket + secondProductCountAtBasket)
                        == totalCountAtAllAtBasket));
    }
}
