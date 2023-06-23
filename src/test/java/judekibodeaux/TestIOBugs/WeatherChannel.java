/*
	Site class for weather.com
*/
package judekibodeaux.TestIOBugs;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WeatherChannel extends BasePage {

	By locationSearchBar = By.cssSelector("#LocationSearch_input");
	By mainNavigation = By.cssSelector("span[class*='MainMenu']");
	By monthPicker = By.id("month-picker");
	By yearPicker = By.id("years-picker");
	By almanacLink = By.cssSelector("[data-testid='Almanac']");
	By updateCalendarButton = By.xpath("//span[contains(@class,'viewText')]/parent::button");
	By profileAvatar = By.cssSelector("div[class*='profileAvatarSmall']");
	By loadingIcon = By.id("eo4ObUaz6P81");
	By dateIcon = By.cssSelector("div[class*='Calendar'] svg[set='weather']");
	By clearRecentSearchesButton = By.cssSelector("button[data-testid='clearAll']");
	By recentSearchListItem = By.cssSelector("button[role='option']");
	
	public WeatherChannel(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}

	public void goToWeatherChannel() {
		driver.get("https://weather.com/");
		waitUntilVisible(profileAvatar);
	}

	public void searchForLocation(String location) {
		driver.findElement(locationSearchBar).sendKeys(location);
		waitUntilVisible(recentSearchListItem);
		driver.findElement(recentSearchListItem).sendKeys(Keys.ENTER);
		waitUntilVisible(profileAvatar);
	}

	public void openMainMenu() {
		clickOn(mainNavigation);
	}

	public void goToAlmanac() {
		clickOn(almanacLink);

		// The profile avatar is not directly relevant, but it's one of the last things
		// on the page to load, so once it is ready, everything else is ready.
		waitUntilVisible(profileAvatar);
	}

	public void selectJune() {
		new Select(driver.findElement(monthPicker)).selectByVisibleText("Jun");
	}

	public void select2022() {
		new Select(driver.findElement(yearPicker)).selectByValue("2022");
	}

	public void updateCalendar() {
		clickOn(updateCalendarButton);
		waitUntilInvisible(loadingIcon);
	}

	public void openRecentSearches() {
		clickOn(locationSearchBar);
	}
	
	public void clearRecentSearches() {
		clickOn(clearRecentSearchesButton);
	}

}
