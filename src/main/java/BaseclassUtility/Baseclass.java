package BaseclassUtility;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import GenericUtilities.DatabaseUtility;
import GenericUtilities.PropertyFileUtility;
import GenericUtilities.WebDriverUtility;
import ListernersUtility.UtilityClassObject;
import POMUtilities.HomePomPage;
import POMUtilities.LoginPomPage;

public class Baseclass {
	public WebDriver driver = null;

	public static WebDriver sdriver = null;

	DatabaseUtility db_util = new DatabaseUtility();
	PropertyFileUtility prop = new PropertyFileUtility();
	WebDriverUtility wutil = new WebDriverUtility();

	@BeforeSuite
	public void connectionWithDB() throws SQLException {
		db_util.getDatabaseConnection();
		Reporter.log("Connected with the database", true);
	}

	@AfterSuite
	public void closetheConnWithDB() throws SQLException {
		db_util.closeDatabaseConnection();
		Reporter.log("Disconnected with the Database", true);
	}

	@BeforeTest
	public void configParallelExe() {
		Reporter.log("Configure Parallel Exe", true);
	}

	@AfterTest
	public void closeParallelExe() {
		Reporter.log("Close the parallel Exe", true);
	}

//	@Parameters("browser")
	@BeforeClass
	public void LaunchTheBrowser() throws IOException {
//		String Browser = prop.fetchDataFromPropFile("browser");
		String Browser = System.getProperty("browser", prop.fetchDataFromPropFile("browser"));
		Reporter.log("Launch the browser", true);
		if (Browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (Browser.equals("edge")) {
			driver = new EdgeDriver();
		} else if (Browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}
		sdriver = driver;
		UtilityClassObject.setDriver(driver);
	}

	@AfterClass
	public void closeTheBrowser() {
		Reporter.log("Close the browser", true);
		driver.quit();
	}

	@BeforeMethod
	public void login() throws IOException {
//		String URL = prop.fetchDataFromPropFile("url");
//		String USERNAME = prop.fetchDataFromPropFile("username");
//		String PASSWORD = prop.fetchDataFromPropFile("password");
//		String TIMEOUTS = prop.fetchDataFromPropFile("timeouts");

		String URL = System.getProperty("url", prop.fetchDataFromPropFile("url"));
		String USERNAME = System.getProperty("username", prop.fetchDataFromPropFile("username"));
		String PASSWORD = System.getProperty("password", prop.fetchDataFromPropFile("password"));
		String TIMEOUTS = System.getProperty("timeouts", prop.fetchDataFromPropFile("timeouts"));
		LoginPomPage l = new LoginPomPage(driver);
		Reporter.log("Navigated and logged in", true);
//		wutil.navigateToAnAppln(driver, URL);
		wutil.maximizeTheWindow(driver);
		l.login(URL, USERNAME, PASSWORD);
		wutil.waitTillTheElementFound(driver, TIMEOUTS);

	}

	@AfterMethod
	public void logout() {
		Reporter.log("logout", true);
		HomePomPage home = new HomePomPage(driver);
		WebElement admin = home.getAdminicon();
		wutil.mouseHoverOnAnElement(driver, admin);
		home.getSignout();

	}
}
