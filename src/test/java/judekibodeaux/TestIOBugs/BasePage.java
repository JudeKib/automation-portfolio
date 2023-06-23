/*
	Parent class for website/page classes. It exposes reusable utilities for automating general web pages.
*/
package judekibodeaux.TestIOBugs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	public WebDriver driver;
	public WebDriverWait wait;
	
	public void clickOn(By locator) {
		driver.findElement(locator).click();
	}
	
	public void reloadWait(By locator) {
		wait.until(ExpectedConditions.stalenessOf(driver.findElement(locator)));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitUntilVisible(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void waitUntilInvisible(By locator) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}


}
