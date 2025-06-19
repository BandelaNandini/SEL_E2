package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePomPage {

	// Declare
	@FindBy(partialLinkText = "Home")
	private WebElement header;

	@FindBy(linkText = "Organizations")
	private WebElement org_tab;

	@FindBy(linkText = "Contacts")
	private WebElement contact_tab;

	@FindBy(xpath = "//img[contains(@src,'user.PNG')]")
	private WebElement adminicon;

	@FindBy(linkText = "Sign Out")
	private WebElement signout;

	// Initialize

	WebDriver driver;
	public HomePomPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	// Utilize

	public String getHeader() {
		return header.getText();
	}

	public void getOrg_tab() {
		org_tab.click();
	}

	public void getContact_tab() {
		contact_tab.click();
	}

	public WebElement getAdminicon() {
		return adminicon;
	}

	public void getSignout() {
		signout.click();
	}

}
