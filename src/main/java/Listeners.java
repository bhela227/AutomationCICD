import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class Listeners implements ITestListener {

    ExtentReports extent = ExtentReporterNG.getReporterObject();
    ExtentTest test;
    ThreadLocal<ExtentTest> threadLocal = new ThreadLocal<>(); //Thread Safe

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        threadLocal.set(test); //creates thread id
    }

    @Override
    public void onTestSuccess(ITestResult result) {
       String testName = result.getMethod().getMethodName();
        test.log(Status.PASS, testName + " passed all Assertions.");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        String testName = result.getMethod().getMethodName();
        WebDriver driver = null;
        try {
            driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

        String path = Screenshot.takeScrrenshot(driver, testName);

        //test.log(Status.FAIL, result.getMethod().getMethodName() + "failed.");
        threadLocal.get().log(Status.FAIL, result.getMethod().getMethodName() + "failed.");
        //test.fail(result.getThrowable());
        threadLocal.get().fail(result.getThrowable());

       // test.addScreenCaptureFromPath(path);

        //test.addScreenCaptureFromPath(path);
        threadLocal.get().addScreenCaptureFromPath(path);

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.skip("Test Skipped"+ result.getMethod().getMethodName());
    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
