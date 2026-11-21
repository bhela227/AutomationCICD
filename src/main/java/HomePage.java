import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "fname")
    WebElement fName;

    @FindBy(id = "lname")
    WebElement lName;

    @FindBy(xpath = "//input[@type='radio' and @value='f']")
    WebElement femaleRadioButton;

    @FindBy(xpath = "//input[@type='radio' and @value='m']")
    WebElement maleRadioButton;

    @FindBy(name = "newsletter")
    WebElement checkbox;

}
