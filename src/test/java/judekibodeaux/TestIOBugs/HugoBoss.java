/*
	Site class for the Hugo Boss web shop
*/
package judekibodeaux.TestIOBugs;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HugoBoss extends BasePage {
	
	By declinePromo = By.cssSelector("#bx-element-2180370-6P7vL5M button");
	By hamburgerIcon = By.cssSelector("div[class*='menu-btn-wrapper']");
	By needAssistanceNav = By.cssSelector("button[class*='chat-has-agent']");
	By moreInspiration = By.cssSelector("a[href='https://www.hugoboss.com/us/inspiration/'] div[class*='item__content']");
	By eyewearTile = By.xpath("//img[alt='BOSS Eyewear']/parent::picture");
	By crosshair = By.cssSelector("div.crosshair");
	By nextArrow = By.cssSelector("button[aria-label='Next']");
	By promoAccess = By.id("bx-element-2204667-l4fXsPR");
	By declineSecondPromo = By.cssSelector("#bx-element-2204668-tM0KJWZ button");
	By helpOptions = By.xpath("//h3[contains(@class,'service-sidebar')]");
	
	public HugoBoss(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}
	
	public void goTo() {
		driver.get("https://www.hugoboss.com/us/");
		clickOn(declinePromo);
	}
	
	public void openMainMenu() {
		clickOn(hamburgerIcon);
	}
	
	public void openHelpSidebar() {
		waitUntilVisible(needAssistanceNav);
		clickOn(needAssistanceNav);
	}
	
	public void goToOrderStatus () {
		waitUntilVisible(helpOptions);
		driver.findElements(helpOptions).get(3).click();
		waitUntilInvisible(helpOptions);
	}
	
	public void goToMoreInspiration() {
		while (!driver.findElement(moreInspiration).isDisplayed()) {
			driver.findElement(nextArrow).click();
		}
		clickOn(moreInspiration);
		waitUntilVisible(promoAccess);
		clickOn(promoAccess);
		clickOn(declineSecondPromo);
	}
	
	public void goToBossEyewear() {
		clickOn(eyewearTile);
	}
	
	public void clickCrosshair() {
		clickOn(crosshair);
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.hugoboss.com/us/men-glasses/?prefn1=brand&prefv1=BOSS");
	}
}
