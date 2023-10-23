import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.Map;

public class Element {
    public WebElement icon;
    public WebElement logo;
    public WebElement searchOne;
    @FindBy(xpath = "//input[@class='search-component-input']")
    public WebElement searchTwo;
    private static Map<String, String> productInfo;

    public static Map<String, String> getProductInfo() {
        return productInfo;
    }

    public static double getTotalPriceFromInfo() {
        return productInfo.values().stream().map(v -> v.replace("р.", ""))
                .map(v -> v.replace(",", "."))
                .map(v -> v.replace(" ", ""))
                .mapToDouble(v -> Double.parseDouble(String.valueOf(v))).sum();
    }

    public static void setProductInfo(Map<String, String> productInfo) {
        Element.productInfo = productInfo;
    }
    public static WebElement visibilityOfElement(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(element));
    }
    public static WebElement elementToBeClickable(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(element));
    }
}
