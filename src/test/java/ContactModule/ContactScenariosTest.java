package ContactModule;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import BaseclassUtility.Baseclass;
import GenericUtilities.ExcelFileUtility;
import GenericUtilities.JavaUtility;
import GenericUtilities.WebDriverUtility;
import ListernersUtility.UtilityClassObject;
import POMUtilities.ContInfoPomPage;
import POMUtilities.ContactPompage;
import POMUtilities.CreateNewContactPompage;
import POMUtilities.CreateNewOrgPomPage;
import POMUtilities.HomePomPage;
import POMUtilities.OrgInfoPomPage;
import POMUtilities.OrganizationPomPage;

//Changes Done To check pull
@Listeners(ListernersUtility.Listeners_Vtiger.class)
public class ContactScenariosTest extends Baseclass {

	@Test(groups = "smoke", retryAnalyzer = ListernersUtility.IRetryAnalyser.class)
	public void createContTest() throws Exception {

		//Create Contact Test
		WebDriverUtility wutil = new WebDriverUtility();
		// Generate the random num
		JavaUtility jutil = new JavaUtility();
		int randomint = jutil.generateRandomNumber();

		// Fetch the data from excel
		ExcelFileUtility ex_util = new ExcelFileUtility();
		String contname = ex_util.fetchDataFromExcelFile("contact", 1, 2) + randomint;

		// Click on contact tab in the homepage
		UtilityClassObject.getTest().log(Status.INFO, "Click on contact tab in the homepage");
		HomePomPage home = new HomePomPage(driver);
		home.getContact_tab();

		// Click on plus symbol
		UtilityClassObject.getTest().log(Status.INFO, "Click on plus symbol");
		ContactPompage cp = new ContactPompage(driver);
		cp.getContplusicon();

		// Enter contact name and click on save button
		UtilityClassObject.getTest().log(Status.INFO, "Enter contact name and click on save button");
		CreateNewContactPompage cn = new CreateNewContactPompage(driver);
		cn.getLastnametf(contname);
		cn.getSave_btn();

		// verify the contact name
		UtilityClassObject.getTest().log(Status.INFO, "Verifying the contact name");
		ContInfoPomPage con_info = new ContInfoPomPage(driver);
		String lastname = con_info.getVerifyLastName();

		// Hard Assert to validate contact name
		Assert.assertEquals(lastname, contname);

		// Click on contact tab in the homepage
		UtilityClassObject.getTest().log(Status.INFO, "Click on contact tab");
		home.getContact_tab();

		// Delete the contact
		UtilityClassObject.getTest().log(Status.INFO, "Delete the contact");
		driver.findElement(
				By.xpath("//a[text()='" + contname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();

		Thread.sleep(3000);

		// Handle alert popup
		UtilityClassObject.getTest().log(Status.INFO, "Handling alert popup");
		wutil.HandleAlertAndClickOnOk(driver);

		// close excel
		ex_util.closeExcelWorkbook();
	}

	@Test(groups = "reg", retryAnalyzer = ListernersUtility.IRetryAnalyser.class)
	public void createConWithOrg() throws IOException, InterruptedException {
		WebDriverUtility wutil = new WebDriverUtility();

		// Generate the random num
		JavaUtility jutil = new JavaUtility();
		int randomint = jutil.generateRandomNumber();

		// Fetch the data from excel
		ExcelFileUtility ex_util = new ExcelFileUtility();
		String contname = ex_util.fetchDataFromExcelFile("contact", 7, 3) + randomint;
		String orgname = ex_util.fetchDataFromExcelFile("contact", 7, 2) + randomint;

		// Create ORG
		// Click on org tab in the homepage
		UtilityClassObject.getTest().log(Status.INFO, "Click on org tab in home page");
		HomePomPage home = new HomePomPage(driver);
		home.getOrg_tab();

		// Click on plus symbol
		UtilityClassObject.getTest().log(Status.INFO, "Click plus icon");
		OrganizationPomPage org = new OrganizationPomPage(driver);
		org.getOrgplusicon();

		// Enter org name and click on save button
		UtilityClassObject.getTest().log(Status.INFO, "Enter org name and click on save button");
		CreateNewOrgPomPage cno = new CreateNewOrgPomPage(driver);
		cno.getOrgnameTF(orgname);
		cno.getSave_btn();

		// verify the org name
		OrgInfoPomPage oip = new OrgInfoPomPage(driver);
		String verifyorg_name = oip.getVerifyOrgname();

		// Hard Assert to validate org name
		UtilityClassObject.getTest().log(Status.INFO, "Validating OrgName using Asssertions");
		Assert.assertEquals(verifyorg_name, orgname);

		// Click on contact tab in the homepage
		UtilityClassObject.getTest().log(Status.INFO, "Click on contact tab");
		home.getContact_tab();

		// Click on plus symbol
		UtilityClassObject.getTest().log(Status.INFO, "Click on plus contact icon");
		ContactPompage cp = new ContactPompage(driver);
		cp.getContplusicon();

		// Enter contact name and click on save button
		UtilityClassObject.getTest().log(Status.INFO, "Enter contact name and click on save button");
		CreateNewContactPompage cn = new CreateNewContactPompage(driver);
		ContInfoPomPage con_info = new ContInfoPomPage(driver);
		cn.getLastnametf(contname);
		String pwid = wutil.fetchParentWindowId(driver);
		cn.getOrgplusicon();
		UtilityClassObject.getTest().log(Status.INFO, "Switch to child window");

		wutil.switchToChildWindowBasedOnUrl(driver, "module=Accounts&action");

		cn.getOrgSearchTF(orgname);
		cn.getOrgSearchbtn();
		driver.findElement(By.xpath("//a[text()='" + orgname + "']")).click();

		UtilityClassObject.getTest().log(Status.INFO, "Switch to parent window");
		wutil.switchToParentWindow(driver, pwid);
		cn.getSave_btn();

		// verify the contact name
		String lastname = con_info.getVerifyLastName();

		// Hard Assert to validate contact name
		UtilityClassObject.getTest().log(Status.INFO, "Verify contact name using Assertions");
		Assert.assertEquals(lastname, contname);

		// Verify org in cont info page
		UtilityClassObject.getTest().log(Status.INFO, "Verify org name using Assertions");
		String verifyorg = driver
				.findElement(By.xpath("//td[@id='mouseArea_Organization Name']/a[text()='" + orgname + "']")).getText();

		// Hard Assert to validate contact name with org name
		Assert.assertEquals(verifyorg, orgname);

		// Click on contact tab in the homepage
		UtilityClassObject.getTest().log(Status.INFO, "Click on contact tab");
		home.getContact_tab();
		// Delete the contact
		UtilityClassObject.getTest().log(Status.INFO, "Delete the contact");
		driver.findElement(
				By.xpath("//a[text()='" + contname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();

		Thread.sleep(3000);

		// Handle alert popup
		UtilityClassObject.getTest().log(Status.INFO, "Handling the popup");
		wutil.HandleAlertAndClickOnOk(driver);

		// Click on org tab in the homepage
		UtilityClassObject.getTest().log(Status.INFO, "Click on org tab");
		home.getOrg_tab();

		// Delete the org
		UtilityClassObject.getTest().log(Status.INFO, "Delete the org");
		driver.findElement(
				By.xpath("//a[text()='" + orgname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();

		Thread.sleep(3000);

		// Handle alert popup
		UtilityClassObject.getTest().log(Status.INFO, "Handling the popup");
		wutil.HandleAlertAndClickOnOk(driver);

		// close excel
		ex_util.closeExcelWorkbook();
	}

	@Test(groups = "reg", retryAnalyzer = ListernersUtility.IRetryAnalyser.class)
	public void createConWithDate() throws Exception {
		WebDriverUtility wutil = new WebDriverUtility();

		// Generate the random num
		JavaUtility jutil = new JavaUtility();
		int randomint = jutil.generateRandomNumber();

		// Fetch the data from excel
		ExcelFileUtility ex_util = new ExcelFileUtility();
		String contname = ex_util.fetchDataFromExcelFile("contact", 4, 2) + randomint;

		// Click on contact tab in the homepage
		UtilityClassObject.getTest().log(Status.INFO, "Click on contact tab in homepage");
		HomePomPage home = new HomePomPage(driver);
		home.getContact_tab();

		// Click on plus symbol
		UtilityClassObject.getTest().log(Status.INFO, "Click on plus symbol");
		ContactPompage cp = new ContactPompage(driver);
		cp.getContplusicon();

		// Enter contact name and pass the support date
		UtilityClassObject.getTest().log(Status.INFO, "Enter contact name and pass the support date");
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
		UtilityClassObject.getTest().log(Status.INFO, "verify the contact name Using Assertions");
		ContInfoPomPage con_info = new ContInfoPomPage(driver);
		String lastname = con_info.getVerifyLastName();

		// Hard Assert to validate contact name
		Assert.assertEquals(lastname, contname);

		// Verify supp strt date
		UtilityClassObject.getTest().log(Status.INFO, "verify the Start Date");
		String strt_date = con_info.getVerifyStartDate();

		// Hard Assert to validate supp strt date
		Assert.assertEquals(strt_date, strtdate);

		// Verify supp end date
		UtilityClassObject.getTest().log(Status.INFO, "verify the end date");
		String end_date = con_info.getVerifyEndDate();

		// Hard Assert to validate end date
		Assert.assertEquals(end_date, enddate);

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
