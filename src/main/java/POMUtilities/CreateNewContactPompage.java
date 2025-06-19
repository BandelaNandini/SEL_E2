package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewContactPompage {

	// Declare
	@FindBy(xpath = "//span[text()='Creating New Contact']")
	private WebElement header;

	@FindBy(name = "lastname")
	private WebElement lastnametf;

	@FindBy(xpath = "//img[@title='Select']")
	private WebElement orgplusicon;

	@FindBy(name = "support_start_date")
	private WebElement startdatetf;

	@FindBy(name = "support_end_date")
	private WebElement enddatetf;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement save_btn;

	@FindBy(id = "search_txt")
	private WebElement OrgSearchTF;

	@FindBy(name = "search")
	private WebElement OrgSearchbtn;

	// Initialize
	WebDriver driver;
	public CreateNewContactPompage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	// Utilization
	public String getHeader() {
		return header.getText();
	}

	public void getOrgSearchTF(String orgname) {
		OrgSearchTF.sendKeys(orgname);
	}

	public void getOrgSearchbtn() {
		OrgSearchbtn.click();
	}

	public void getLastnametf(String orgname) {
		lastnametf.sendKeys(orgname);
	}

	public void getOrgplusicon() {
		orgplusicon.click();
	}

	public WebElement getStartdatetf() {
		return startdatetf;
	}

	public WebElement getEnddatetf() {
		return enddatetf;
	}

	public void getSave_btn() {
		save_btn.click();
	}

}
