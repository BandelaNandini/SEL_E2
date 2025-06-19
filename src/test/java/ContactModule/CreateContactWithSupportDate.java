package ContactModule;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import GenericUtilities.ExcelFileUtility;
import GenericUtilities.JavaUtility;
import GenericUtilities.PropertyFileUtility;
import GenericUtilities.WebDriverUtility;
import POMUtilities.ContInfoPomPage;
import POMUtilities.ContactPompage;
import POMUtilities.CreateNewContactPompage;
import POMUtilities.HomePomPage;
import POMUtilities.LoginPomPage;

public class CreateContactWithSupportDate {

	@Test
	public void createConWithDate() throws Exception {

		// Fetch the data from property file
		PropertyFileUtility prop = new PropertyFileUtility();
		String BROWSER = prop.fetchDataFromPropFile("browser");
		String URL = prop.fetchDataFromPropFile("url");
		String USERNAME = prop.fetchDataFromPropFile("username");
		String PASSWORD = prop.fetchDataFromPropFile("password");
		String TIMEOUTS = prop.fetchDataFromPropFile("timeouts");

		// Generate the random num
		JavaUtility jutil = new JavaUtility();
		int randomint = jutil.generateRandomNumber();

		// Fetch the data from excel
		ExcelFileUtility ex_util = new ExcelFileUtility();
		String contname = ex_util.fetchDataFromExcelFile("contact", 4, 2) + randomint;

		// Launch the browser
		WebDriver driver = null;
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}

		WebDriverUtility wutil = new WebDriverUtility();

		// Maximize the window
		wutil.maximizeTheWindow(driver);

		// Implicit wait
		wutil.waitTillTheElementFound(driver, TIMEOUTS);

		// Navigate to an appln
		wutil.navigateToAnAppln(driver, URL);

		// Login to the appln
		LoginPomPage l = new LoginPomPage(driver);
		l.getUsernameTF(USERNAME);
		l.getPasswordTF(PASSWORD);
		l.getLogin_btn();

		// Click on contact tab in the homepage
		HomePomPage home = new HomePomPage(driver);
		home.getContact_tab();

		// Click on plus symbol
		ContactPompage cp = new ContactPompage(driver);
		cp.getContplusicon();

		// Enter contact name and pass the support date
		CreateNewContactPompage cn = new CreateNewContactPompage(driver);
		cn.getLastnametf(contname);

		WebElement strt_suppdate = cn.getStartdatetf();
		strt_suppdate.clear();

		// Fetching the current date
		String strtdate = jutil.getCurrentSystemdate();
		strt_suppdate.sendKeys(strtdate);

		WebElement end_suppdate = cn.getEnddatetf();
		end_suppdate.clear();

		// fEtching date after 30 days
		String enddate = jutil.getDateAfterGivenDays(30);
		end_suppdate.sendKeys(enddate);

		cn.getSave_btn();
		// verify the contact name
		ContInfoPomPage con_info = new ContInfoPomPage(driver);
		String header = con_info.getHeader();
		if (header.contains(contname)) {
			System.out.println("Test Pass");
		} else {
			System.out.println("Test Fail");
		}

		// Verify supp strt date
		String strt_date = con_info.getVerifyStartDate();
		if (strt_date.contains(strtdate)) {
			System.out.println("Successfully created strt date");
		} else {
			System.out.println("Not created strt date");
		}

		// Verify supp end date
		String end_date = con_info.getVerifyEndDate();
		if (end_date.contains(enddate)) {
			System.out.println("Successfully created end date");
		} else {
			System.out.println("Not created end date");
		}

		// Click on contact tab in the homepage
		home.getContact_tab();
		// Delete the contact
		driver.findElement(
				By.xpath("//a[text()='" + contname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();

		Thread.sleep(3000);

		// Handle alert popup
		wutil.HandleAlertAndClickOnOk(driver);

		// Mouse hovering on admin icon
		WebElement admin = home.getAdminicon();
		wutil.mouseHoverOnAnElement(driver, admin);

		// Logout of the appln
		home.getSignout();

		// Close the browser
		wutil.quitTheBrowser(driver);

		// close excel
		ex_util.closeExcelWorkbook();
	}

}
