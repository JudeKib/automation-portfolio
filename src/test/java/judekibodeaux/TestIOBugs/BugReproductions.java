package judekibodeaux.TestIOBugs;

import static org.openqa.selenium.support.locators.RelativeLocator.*;

import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v110.network.Network;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BugReproductions extends BaseTest {

	@Test(groups = { "mobile", "hugoBoss" })
	public void hugobossOrderStatusLink() {
		hb.goTo();
		hb.openMainMenu();
		hb.openHelpSidebar();
		hb.goToOrderStatus();
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.hugoboss.com/us/orderstatus");
	}

	@Test(groups = { "mobile", "hugoBoss" })
	public void hugobossEyewearLink() {
		hb.goTo();
		hb.goToMoreInspiration();
		hb.goToBossEyewear();
		hb.clickCrosshair();
		Assert.assertEquals(driver.getCurrentUrl(),
				"https://www.hugoboss.com/us/men-glasses/?prefn1=brand&prefv1=BOSS");
	}

	//There's a weird overlay now
	@Test(groups = { "mobile", "nbaStore" })
	public void nbastoreWomensJerseys() {
		DevTools devTools = ((ChromeDriver)driver).getDevTools();
		devTools.createSession();
		devTools.send(Network.emulateNetworkConditions(false, 700, 400, 100, Optional.empty()));
		
		nba.goToNbaStore();
		nba.openMainMenu();
		nba.openJerseysSubmenu();		
		nba.selectHoustonRockets();
		nba.filterWomens();
		Assert.assertFalse(nba.mensItemsPresent());
	}


	@Test(groups = { "weatherChannel" })
	public void weatherMissingData() {
		wc.goToWeatherChannel();
		wc.searchForLocation("Austin, TX");
		wc.openMainMenu();
		wc.goToAlmanac();
		wc.selectJune();
		wc.select2022();
		wc.updateCalendar();

		List<WebElement> calendarIcons = driver.findElements(wc.dateIcon);

		for (WebElement icon : calendarIcons) {
			String date = driver.findElement(with(By.tagName("span")).above(icon)).getText();

			if (date.equals("23")) {
				Assert.assertFalse(icon.getDomAttribute("name").equals(""), "Icon missing for June " + date);
			}
		}
	}
	
	@Test(groups = { "weatherChannel" })
	public void weatherClearRecentSearches() {
		wc.goToWeatherChannel();
		wc.searchForLocation("Dallas, TX");
		wc.searchForLocation("Houston, TX");
		wc.openRecentSearches();
		wc.clearRecentSearches();
		Assert.assertTrue(driver.findElements(wc.recentSearchListItem).size() == 0);
		
	}

}
