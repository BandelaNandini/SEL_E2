package ContactModule;

import java.io.IOException;
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
import POMUtilities.CreateNewOrgPomPage;
import POMUtilities.HomePomPage;
import POMUtilities.LoginPomPage;
import POMUtilities.OrgInfoPomPage;
import POMUtilities.OrganizationPomPage;

public class CreateContactWithOrgTest {

	@Test
	public void createConWithOrg() throws IOException, InterruptedException {
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
		String contname = ex_util.fetchDataFromExcelFile("contact", 7, 3) + randomint;
		String orgname = ex_util.fetchDataFromExcelFile("contact", 7, 2) + randomint;

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

		// Create ORG
		// Click on org tab in the homepage
		HomePomPage home = new HomePomPage(driver);
		home.getOrg_tab();

		// Click on plus symbol
		OrganizationPomPage org = new OrganizationPomPage(driver);
		org.getOrgplusicon();

		// Enter org name and click on save button
		CreateNewOrgPomPage cno = new CreateNewOrgPomPage(driver);
		cno.getOrgnameTF(orgname);
		cno.getSave_btn();

		// verify the org name
		OrgInfoPomPage oip = new OrgInfoPomPage(driver);
		String org_header = oip.getHeader();
		if (org_header.contains(orgname)) {
			System.out.println("Test Pass");
		} else {
			System.out.println("Test Fail");
		}

		// Click on contact tab in the homepage
		home.getContact_tab();
		// Click on plus symbol
		ContactPompage cp = new ContactPompage(driver);
		cp.getContplusicon();

		// Enter contact name and click on save button
		CreateNewContactPompage cn = new CreateNewContactPompage(driver);
		ContInfoPomPage con_info = new ContInfoPomPage(driver);
		cn.getLastnametf(contname);
		String pwid = wutil.fetchParentWindowId(driver);
		cn.getOrgplusicon();
		wutil.switchToChildWindowBasedOnUrl(driver, "module=Accounts&action");

		cn.getOrgSearchTF(orgname);
		cn.getOrgSearchbtn();
		driver.findElement(By.xpath("//a[text()='" + orgname + "']")).click();

		wutil.switchToParentWindow(driver, pwid);

		cn.getSave_btn();
		// verify the contact name
		String con_header = con_info.getHeader();
		if (con_header.contains(contname)) {
			System.out.println("Test Pass");
		} else {
			System.out.println("Test Fail");
		}

		// Verify org in cont info page
		String verifyorg = driver
				.findElement(By.xpath("//td[@id='mouseArea_Organization Name']/a[text()='" + orgname + "']")).getText();
		if (verifyorg.contains(orgname)) {
			System.out.println("contact successfully created with org");
		} else {
			System.out.println("contact has not created with proper org");

		}

		// Click on contact tab in the homepage
		home.getContact_tab();
		// Delete the contact
		driver.findElement(By.xpath("//a[text()='" + contname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']")).click();

		Thread.sleep(3000);

		// Handle alert popup
		wutil.HandleAlertAndClickOnOk(driver);
		// Click on org tab in the homepage
		home.getOrg_tab();
		// Delete the org
		driver.findElement(
				By.xpath("//a[text()='" + orgname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
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
