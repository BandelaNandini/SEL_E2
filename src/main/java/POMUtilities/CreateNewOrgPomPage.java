package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewOrgPomPage {

	// Declare

	@FindBy(xpath = "//span[text()='Creating New Organization']")
	private WebElement header;

	@FindBy(name = "accountname")
	private WebElement orgnameTF;

	@FindBy(id = "phone")
	private WebElement phnoTF;

	@FindBy(name = "industry")
	private WebElement ind_DD;

	@FindBy(name = "accounttype")
	private WebElement type_DD;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement save_btn;

	// Initialize

	WebDriver driver;
	public CreateNewOrgPomPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Utilize

	public String getHeader() {
		return header.getText();
	}

	public void getOrgnameTF(String orgname) {
		orgnameTF.sendKeys(orgname);
	}

	public void getPhnoTF(String phno) {
		phnoTF.sendKeys(phno);
	}

	public WebElement getInd_DD() {
		return ind_DD;
	}

	public WebElement getType_DD() {
		return type_DD;
	}

	public void getSave_btn() {
		save_btn.click();
	}

}
