package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPomPage {
	// Declare
	@FindBy(linkText = "vtiger")
	private WebElement header;

	@FindBy(name = "user_name")
	private WebElement usernameTF;

	@FindBy(name = "user_password")
	private WebElement passwordTF;

	@FindBy(id = "submitButton")
	private WebElement login_btn;

	// Initialize
	WebDriver driver;

	public LoginPomPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Utilize

	public String getHeader() {
		return header.getText();
	}

	public void getUsernameTF(String user) {
		usernameTF.sendKeys(user);
	}

	public void getPasswordTF(String pass) {
		passwordTF.sendKeys(pass);
	}

	public void getLogin_btn() {
		login_btn.click();
	}

	public void login(String url, String username, String password) {
		driver.get(url);
		usernameTF.sendKeys(username);
		passwordTF.sendKeys(password);
		login_btn.click();
	}

}
