import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
    public static ExtentReports getReporterObject(){
        ExtentReports extent = new ExtentReports();
        String path = System.getProperty("user.dir")+"\\reports\\report.html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(path);
        sparkReporter.config().setReportName("SeleniumHomePage Test");
        sparkReporter.config().setDocumentTitle("Selenium Test Report");
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Tester", "HS");
        return extent;
    }
}
