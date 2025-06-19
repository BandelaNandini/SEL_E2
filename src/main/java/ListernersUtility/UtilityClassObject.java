package ListernersUtility;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class UtilityClassObject {

	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	public static ExtentTest getTest() {
		return test.get();
	}

	public static void setTest(ExtentTest act_test) {
		test.set(act_test);
	}

	public static WebDriver getDriver() {
		return driver.get();
	}

	public static void setDriver(WebDriver util_driver) {
		driver.set(util_driver);
	}

}
