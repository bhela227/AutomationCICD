import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;

public class Screenshot {


    public static String takeScrrenshot(WebDriver driver, String testcaseName){

        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir")+"\\reports\\"+testcaseName+".png";
        File destination = new File(path);
        try {
            FileUtils.copyFile(source, destination);
        }catch(IOException e){
            e.printStackTrace();
        }
        return path;
    }

}
