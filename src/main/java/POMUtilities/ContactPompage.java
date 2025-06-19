package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPompage {
	// Declare
	@FindBy(linkText = "Contacts")
	private WebElement header;
	@FindBy(xpath = "//img[@alt='Create Contact...']")
	private WebElement contplusicon;

	// Intialize
	WebDriver driver;
	public ContactPompage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Utilize
	public String getHeader() {
		return header.getText();
	}

	public void getContplusicon() {
		contplusicon.click();
	}

}
