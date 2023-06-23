/*
	Parent class for classes containing tests. It exposes methods related to setting up tests and closing them out.
*/
package judekibodeaux.TestIOBugs;

import java.util.Optional;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v112.emulation.Emulation;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseTest {
	
	public WebDriver driver;
	public WebDriverWait wait;
	
	public HugoBoss hb;
	public NbaStore nba;
	public WeatherChannel wc;
	
	@BeforeMethod(alwaysRun = true)
	public void initializeDriver() {
		driver = new ChromeDriver();
	}
	
	@BeforeMethod(onlyForGroups = { "hugoBoss" }, alwaysRun = true)
	public void openHugoBoss() {
		hb = new HugoBoss(driver);
	}
	
	@BeforeMethod(onlyForGroups = { "nbaStore" }, alwaysRun = true)
	public void openNbaStore() {
		nba = new NbaStore(driver);
	}
	
	@BeforeMethod(onlyForGroups = { "weatherChannel" }, alwaysRun = true)
	public void openWeatherChannel() {
		wc = new WeatherChannel(driver);
	}
	
	@BeforeMethod(onlyForGroups = { "mobile" }, dependsOnMethods = { "initializeDriver" }, alwaysRun = true)
	public void setupMobile() {
		DevTools devTools = ((ChromeDriver)driver).getDevTools();
		devTools.createSession();
		devTools.send(Emulation.setDeviceMetricsOverride(412, 914, 50, true, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
				Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));
	}
	
	@AfterMethod(alwaysRun=true)
	public void teardown() {
		driver.close();
	}

}
