package OrganizationModule;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import BaseclassUtility.Baseclass;
import GenericUtilities.ExcelFileUtility;
import GenericUtilities.JavaUtility;
import GenericUtilities.WebDriverUtility;
import POMUtilities.CreateNewOrgPomPage;
import POMUtilities.HomePomPage;
import POMUtilities.OrgInfoPomPage;
import POMUtilities.OrganizationPomPage;

//@Listeners(ListernersUtility.Listeners.class)
public class OrgScenariosTest extends Baseclass {

	@Test(groups = "smoke",retryAnalyzer = ListernersUtility.IRetryAnalyser.class)
	public void createOrg() throws IOException, InterruptedException {
		WebDriverUtility wutil = new WebDriverUtility();

		// Generate the random num
		JavaUtility jutil = new JavaUtility();
		int randomint = jutil.generateRandomNumber();

		// Fetch the data from excel
		ExcelFileUtility ex_util = new ExcelFileUtility();
		String orgname = ex_util.fetchDataFromExcelFile("organization", 1, 2) + randomint;

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
		String verifyorg_name = oip.getVerifyOrgname();

		// Hard Assert to validate org name
		Assert.assertEquals(verifyorg_name, orgname);

		// Click on org tab in the homepage
		home.getOrg_tab();

		// Delete the org
		driver.findElement(
				By.xpath("//a[text()='" + orgname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();

		Thread.sleep(3000);

		// Handle alert popup
		wutil.HandleAlertAndClickOnOk(driver);

		// close excel
		ex_util.closeExcelWorkbook();
	}

	@Test(groups = "reg", retryAnalyzer = ListernersUtility.IRetryAnalyser.class)

	public void createOrg_IndAndType() throws IOException, InterruptedException {
		WebDriverUtility wutil = new WebDriverUtility();

		// Generate the random num
		JavaUtility jutil = new JavaUtility();
		int randomint = jutil.generateRandomNumber();

		// Fetch data from Excel file
		ExcelFileUtility ex_util = new ExcelFileUtility();
		String orgname = ex_util.fetchDataFromExcelFile("organization", 7, 2) + randomint;
		String Ind = ex_util.fetchDataFromExcelFile("organization", 7, 3);
		String Type = ex_util.fetchDataFromExcelFile("organization", 7, 4);

		// Click on org tab in the homepage
		HomePomPage home = new HomePomPage(driver);
		home.getOrg_tab();

		// Click on plus symbol
		OrganizationPomPage org = new OrganizationPomPage(driver);
		org.getOrgplusicon();

		// Enter org name and click on save button
		CreateNewOrgPomPage cno = new CreateNewOrgPomPage(driver);
		cno.getOrgnameTF(orgname);

		WebElement industryDD = cno.getInd_DD();
		wutil.SelectDDByValue(industryDD, Ind);

		WebElement typeDD = cno.getType_DD();
		wutil.SelectDDByValue(typeDD, Type);

		cno.getSave_btn();

		// verify the org name
		OrgInfoPomPage oip = new OrgInfoPomPage(driver);
		String verifyorg_name = oip.getVerifyOrgname();

		// Hard Assert to validate org name
		Assert.assertEquals(verifyorg_name, orgname);

		// Verify Industry
		String Actind = oip.getVerifyindDD();

		// Hard Assert to validate industry
		Assert.assertEquals(Actind, Ind);

		// verify Type
		String Acttype = oip.getVerifytypeDD();

		// Hard Assert to validate type
		Assert.assertEquals(Acttype, Type);

		// Click on org tab in the homepage
		home.getOrg_tab();
		// Delete the org
		driver.findElement(
				By.xpath("//a[text()='" + orgname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();

		Thread.sleep(3000);

		// Handle alert popup
		wutil.HandleAlertAndClickOnOk(driver);

		// close excel
		ex_util.closeExcelWorkbook();
	}

	@Test(groups = "reg", retryAnalyzer = ListernersUtility.IRetryAnalyser.class)
	public void createOrganizationWithPhno() throws Exception {
		WebDriverUtility wutil = new WebDriverUtility();

		// Generate the random num
		JavaUtility jutil = new JavaUtility();
		int randomint = jutil.generateRandomNumber();

		// Fetch data from Excel file
		ExcelFileUtility ex_util = new ExcelFileUtility();
		String orgname = ex_util.fetchDataFromExcelFile("organization", 4, 2) + randomint;
		String phno = ex_util.fetchDataFromExcelFile("organization", 4, 3);

		// Click on org tab in the homepage
		HomePomPage home = new HomePomPage(driver);
		home.getOrg_tab();

		// Click on plus symbol
		OrganizationPomPage org = new OrganizationPomPage(driver);
		org.getOrgplusicon();

		// Enter org name and click on save button
		CreateNewOrgPomPage cno = new CreateNewOrgPomPage(driver);
		cno.getOrgnameTF(orgname);
		cno.getPhnoTF(phno);
		cno.getSave_btn();

		// verify the org name
		OrgInfoPomPage oip = new OrgInfoPomPage(driver);
		String verifyorg_name = oip.getVerifyOrgname();

		// Hard Assert to validate org name
		Assert.assertEquals(verifyorg_name, orgname);

		// Verify ph no
		String phnoTF = oip.getVerifyphno();

		// Hard Assert to validate contact name
		Assert.assertEquals(phnoTF, phno);

		// Click on org tab in the homepage
		home.getOrg_tab();
		// Delete the org
		driver.findElement(
				By.xpath("//a[text()='" + orgname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();

		Thread.sleep(3000);

		// Handle alert popup
		wutil.HandleAlertAndClickOnOk(driver);

		// close excel
		ex_util.closeExcelWorkbook();
	}

}
