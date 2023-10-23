import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class Product extends Element{
    private static WebDriver driver;
    @FindBy(xpath = "//*[@class='product-price-current__value']")
    private WebElement productPrice;
    @FindBy(xpath = "//*[@data-tag='productName']")
    private WebElement productName;
    @FindBy(xpath = "//a[@href='/basket']")
    private WebElement basketB;
    @FindBy(xpath = "//*[@class='quantity__plus']")
    private WebElement quantityPlus;

    public Product(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public Product toBasket() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By
                        .xpath("//*[@class='basket-button__button btn btn--primary-gradient']"))).click();
        return this;
    }
    public Product clickQuantity() {
        visibilityOfElement(driver, quantityPlus).click();
        return this;
    }
    public Product searchProduct(String productName) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(searchOne)).sendKeys(productName);
        return this;
    }
    public Home inHome() {
        visibilityOfElement(driver, logo).click();
        return new Home(driver);
    }
    public Basket inBasket() {
        visibilityOfElement(driver, basketB).click();
        return new Basket(driver);
    }
}
