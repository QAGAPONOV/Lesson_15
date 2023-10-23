import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class Home extends Element{
    private final WebDriver driver;
    private Actions actions;
    @FindBy(xpath = "//*[@class='card b-card']")
    private List<WebElement> products;
    @FindBy(xpath = "//*[@class='btn btn--primary b-card__btn btn-basket']")
    private List<WebElement> addBasket;
    @FindBy(xpath = "//*[@class='quantity__plus']")
    private List<WebElement> quantityPlus;

    public Home(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public Product selectProduct(int itemNum) {
        visibilityOfElement(driver, products.get(itemNum)).click();
        return new Product(driver);
    }
    public Home SearchProduct(String productName) {
        visibilityOfElement(driver, searchOne).sendKeys(productName);
        return this;
    }
    public Home clickSearch() {
        visibilityOfElement(driver, searchOne).sendKeys(Keys.ENTER);
        return this;
    }
    public Basket inBasket() {
        elementToBeClickable(driver, icon).click();
        return new Basket(driver);
    }
}
