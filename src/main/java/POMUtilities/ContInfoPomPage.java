package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContInfoPomPage {

	// Declare
	@FindBy(xpath = "//span[contains(text(),'Contact Information')]")
	private WebElement header;

	@FindBy(id = "dtlview_Last Name")
	private WebElement verifyLastName;

	@FindBy(id = "mouseArea_Organization Name")
	private WebElement VerifyOrgName;

	@FindBy(id = "dtlview_Support Start Date")
	private WebElement verifyStartDate;

	@FindBy(id = "dtlview_Support End Date")
	private WebElement verifyEndDate;

	// Initialization
	WebDriver driver;
	public ContInfoPomPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Utilization
	public String getHeader() {
		return header.getText();
	}

	public String getVerifyLastName() {
		return verifyLastName.getText();
	}

	public String getVerifyOrgName() {
		return VerifyOrgName.getText();
	}

	public String getVerifyStartDate() {
		return verifyStartDate.getText();
	}

	public String getVerifyEndDate() {
		return verifyEndDate.getText();
	}

}
