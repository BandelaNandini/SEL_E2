package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrgInfoPomPage {

	// Declare
	@FindBy(xpath = "//span[contains(text(),'Organization Information')]")
	private WebElement header;

	@FindBy(id = "dtlview_Organization Name")
	private WebElement verifyOrgname;

	@FindBy(id = "dtlview_Phone")
	private WebElement verifyphno;

	@FindBy(id = "dtlview_Industry")
	private WebElement verifyindDD;

	@FindBy(id = "dtlview_Type")
	private WebElement verifytypeDD;

	// Initialize

	WebDriver driver;

	public OrgInfoPomPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Utilize

	public String getHeader() {
		return header.getText();
	}

	public String getVerifyOrgname() {
		return verifyOrgname.getText();
	}

	public String getVerifyphno() {
		return verifyphno.getText();
	}

	public String getVerifyindDD() {
		return verifyindDD.getText();
	}

	public String getVerifytypeDD() {
		return verifytypeDD.getText();
	}
}
