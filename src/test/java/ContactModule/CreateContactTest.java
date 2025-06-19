package ContactModule;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import BaseclassUtility.Baseclass;
import GenericUtilities.ExcelFileUtility;
import GenericUtilities.JavaUtility;
import GenericUtilities.PropertyFileUtility;
import GenericUtilities.WebDriverUtility;
import POMUtilities.ContInfoPomPage;
import POMUtilities.ContactPompage;
import POMUtilities.CreateNewContactPompage;
import POMUtilities.HomePomPage;
import POMUtilities.LoginPomPage;

public class CreateContactTest extends Baseclass {

	@Test
	public void createContTest() throws Exception {

		WebDriverUtility wutil = new WebDriverUtility();
		// Generate the random num
		JavaUtility jutil = new JavaUtility();
		int randomint = jutil.generateRandomNumber();

		// Fetch the data from excel
		ExcelFileUtility ex_util = new ExcelFileUtility();
		String contname = ex_util.fetchDataFromExcelFile("contact", 1, 2) + randomint;

		// Click on contact tab in the homepage
		HomePomPage home = new HomePomPage(driver);
		home.getContact_tab();

		// Click on plus symbol
		ContactPompage cp = new ContactPompage(driver);
		cp.getContplusicon();

		// Enter contact name and click on save button
		CreateNewContactPompage cn = new CreateNewContactPompage(driver);
		cn.getLastnametf(contname);
		cn.getSave_btn();
		// verify the contact name
		ContInfoPomPage con_info = new ContInfoPomPage(driver);
		String header = con_info.getHeader();
		if (header.contains(contname)) {
			System.out.println("Test Pass");
		} else {
			System.out.println("Test Fail");
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

		// close excel
		ex_util.closeExcelWorkbook();
	}

}
