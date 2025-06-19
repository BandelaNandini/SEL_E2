package GenericUtilities;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {

	public void maximizeTheWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}

	public void waitTillTheElementFound(WebDriver driver, String Timeouts) {
		long time = Long.parseLong(Timeouts);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
	}

	public void navigateToAnAppln(WebDriver driver, String url) {
		driver.get(url);
	}

	public void HandleAlertAndClickOnOk(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void HandleAlertAndClickOnCancel(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public String HandleAlertAndFetchText(WebDriver driver) {
		String text = driver.switchTo().alert().getText();
		return text;
	}

	public void HandleAlertAndPassTheText(WebDriver driver, String text) {
		driver.switchTo().alert().sendKeys(text);
	}

	public void mouseHoverOnAnElement(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}

	public void dragAndDropOnAnElement
	(WebDriver driver, WebElement dragable_ele, WebElement dropable_ele) {
		Actions act = new Actions(driver);
		act.dragAndDrop(dragable_ele, dropable_ele).perform();
	}

	public void closeTheBrowser(WebDriver driver) {
		driver.close();
	}

	public void quitTheBrowser(WebDriver driver) {
		driver.quit();
	}

	public String fetchParentWindowId(WebDriver driver) {
		String pwid = driver.getWindowHandle();
		return pwid;
	}

	public void switchToChildWindowBasedOnUrl(WebDriver driver, String partial_url) {
		Set<String> wids = driver.getWindowHandles();
		for (String id : wids) {
			driver.switchTo().window(id);
			if (driver.getCurrentUrl().contains(partial_url)) {
				break;
			}

		}
	}

	public void switchToChildWindowBasedOnTitle(WebDriver driver, String partial_title) {
		Set<String> wids = driver.getWindowHandles();
		for (String id : wids) {
			driver.switchTo().window(id);
			if (driver.getCurrentUrl().contains(partial_title)) {
				break;
			}

		}
	}

	public void switchToParentWindow(WebDriver driver, String pwid) {
		driver.switchTo().window(pwid);
	}

	public void SelectDDByValue(WebElement dd_ele, String value) {
		Select s = new Select(dd_ele);
		s.selectByValue(value);
	}

	public void SelectDDByIndex(WebElement dd_ele, int index) {
		Select s = new Select(dd_ele);
		s.selectByIndex(index);
	}

	public void SelectDDByVisibleText(WebElement dd_ele, String text) {
		Select s = new Select(dd_ele);
		s.selectByVisibleText(text);
	}

	public void switchToFrameOnIndex(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}

	public void switchToFrameOnText(WebDriver driver, String visibletext) {
		driver.switchTo().frame(visibletext);
	}

	public void switchToFrameOnElement(WebDriver driver, WebElement frameele) {
		driver.switchTo().frame(frameele);
	}

	public void switchToMainwebpageFromFrame(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void waitTillElementIsVisible
	(WebDriver driver, WebElement element, long time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitTillElementIsClickable
	(WebDriver driver, long time, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitTillTitleIsDisplayed(WebDriver driver, String title) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.titleContains(title));
	}

}
