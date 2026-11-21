import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    private WebDriver driver;

    private Properties prop;

    public Properties getProperties(){
        prop = new Properties();
        // System.getProperty("user.dir") = C:\Users\harpr\IdeaProjects\SeleniumDemoReports

        String propFilePath = System.getProperty("user.dir")+"\\src\\test\\resources\\GlobalData.properties";
        try {
            FileInputStream fis = new FileInputStream(propFilePath);
            prop.load(fis);
        }catch(IOException e){
            e.printStackTrace();
        }
        return prop;
    }



    public WebDriver initializeDriver() {

        prop = getProperties();
        boolean headless = false;

        String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
        if(browserName.contains("chrome")){
            if(browserName.contains("headless")){
                headless = true;
                browserName="chrome";
            }else {
                browserName = "chrome";
            }
        } else if(browserName.contains("firefox")){
            if(browserName.contains("headless")){
                headless = true;
                browserName="firefox";
            }else {
                browserName = "firefox";
            }
        }

        switch(browserName){
            case "chrome" :
                if(headless){
                   driver = new ChromeDriver(new ChromeOptions().addArguments("headless"));
                   driver.manage().window().setSize(new Dimension(1440,900));
                }else{
                    driver = new ChromeDriver();
                }
                break;
            case "firefox":
                if(headless){
                    driver = new FirefoxDriver(new FirefoxOptions().addArguments("--headless"));
                    driver.manage().window().setSize(new Dimension(1440,900));
                }else{
                    driver = new FirefoxDriver();
                }
                break;
        }
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        //driver.get(prop.getProperty("url"));
        //wait.until(d->d.getTitle()!=null && !d.getTitle().isEmpty());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();
        return driver;
    }




}
