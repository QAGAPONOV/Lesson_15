import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Basket extends Element{
    private static WebDriver driver;
    @FindBy (xpath = "//*[@class='quantity__plus']")
    private List<WebElement> plus;
    @FindBy (xpath = "//input[@type='number']")
    private WebElement field;
    @FindBy (xpath = "//*[@class='basket__items-counter hide']")
    private static WebElement basketHide;
    @FindBy (xpath = "//*[@alt='Wildberries']")
    private WebElement homeIcon;
    @FindBy (xpath = "//span[@data-tag='totalSum']")
    private static WebElement priceSum;
    @FindBy (xpath = "//*[@class='quantity__input']")
    private static List<WebElement> qInput;
    @FindBy (xpath = "//*[@class='b-item-price__lower']")
    private List<WebElement> priseLow;
    @FindBy (xpath = "//*[@data-tag='itemName']")

    private List<WebElement> productName;
    private static String totalSum;
    public Basket(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public Basket setQuantity(int prodId) {
        visibilityOfElement(driver, plus.get(prodId)).click();
        return this;
    }
    public double getTotalPriceExpected() {
        List<Double> res = priseLow.stream()
                .map(e -> e.getText()).map(e -> Double.valueOf(e))
                .collect(Collectors.toList());
        return res.stream().reduce(Double::sum).get();
    }
    public static String getProductCounters(int id) {
        return visibilityOfElement(driver, qInput.get(id)).getDomProperty("value");
    }
    public static String getBasketItemsCount() {
        return visibilityOfElement(driver, basketHide).getDomProperty("innerText");
    }
    public static String getTotalPrice() {
        Basket.totalSum = visibilityOfElement(driver, priceSum).getDomProperty("innerText");
        return totalSum;
    }
    public Basket getProductInform() {
        Map<String, String> prodInfo = IntStream.range(0, productName.size()).boxed()
                .collect(Collectors.toMap(i -> productName.get(i).getText(), i -> priseLow.get(i).getText()));
        setProductInfo(prodInfo);
        return this;
    }
    public Home backToHome() {
        visibilityOfElement(driver, homeIcon).click();
        return new Home(driver);
    }
}
