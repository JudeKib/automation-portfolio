/*
	Site class for the NBA Store
*/
package judekibodeaux.TestIOBugs;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NbaStore extends BasePage {
	
	By hamburgerMenu = By.cssSelector("div.hamburger-menu-button");
	By jerseysNav = By.cssSelector("a[href='#hamburger-item-nav-4']");
	By houstonRocketsJerseysNav = By.cssSelector("a[href*='houston-rockets']");
	By womenOption = By.cssSelector("a.pill[data-trk-id='women']");
	By productTitle = By.cssSelector("div.product-card-title");
	By promoOverlay = By.cssSelector("i.icon-drop-up");
	
	public NbaStore(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}
	
	public void goToNbaStore() {
		driver.get("https://store.nba.com/");
		waitUntilInvisible(promoOverlay);
	}
	
	public void openMainMenu() {
		wait.until(ExpectedConditions.elementToBeClickable(hamburgerMenu));
		clickOn(hamburgerMenu);
	}
	
	public void openJerseysSubmenu() {
		clickOn(jerseysNav);
	}
	
	public void selectHoustonRockets() {
		waitUntilVisible(houstonRocketsJerseysNav);
		clickOn(houstonRocketsJerseysNav);
	}
	
	public void filterWomens() {
		clickOn(womenOption);
	}
	
	public boolean mensItemsPresent() {
		List<WebElement> jerseyTitles = driver.findElements(productTitle);
		return jerseyTitles.stream().map(t -> t.getText()).anyMatch(s -> s.contains("Men's"));
	}
	
	

}
