import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Properties;

public class HomePageTest {

    public WebDriver driver;
    public HomePage hp;

    @BeforeTest(alwaysRun = true)
    public void initializeDriver() {
        BaseTest bt = new BaseTest();
        driver = bt.initializeDriver();
        hp = new HomePage(driver);
        Properties prop = bt.getProperties();
        String url = prop.getProperty("url");
        driver.get(url);
    }

    @AfterTest(alwaysRun = true)
    public void closeDriver() {
        driver.quit();
    }

    @Test
    public void fNameTest() {
        String expected = "Harpreet";
        hp.fName.clear();
        hp.fName.sendKeys(expected);
        String actual = hp.fName.getAttribute("value");
        Assert.assertEquals(actual, expected);
    }

    @Test(retryAnalyzer = Retry.class, groups = {"smoke"})
    public void lNameTest() {
        String expected = "Singh";
        hp.lName.clear();
        hp.lName.sendKeys("Singh");
        String actual = hp.lName.getAttribute("value");
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void raduioButtonf(){
        hp.femaleRadioButton.click();
        Assert.assertTrue(hp.femaleRadioButton.isSelected());
    }

}
