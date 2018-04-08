import com.sun.javafx.PlatformUtil;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest {

	WebDriver driver = null;
	Logger logger = Logger.getLogger(SignInTest.class.getName());

	@Test
	public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

		this.driver = DriverUtil.getInstance().driver;
		driver.get("https://www.cleartrip.com/");
		waitFor(500);

		driver.findElement(By.linkText(Constant.YOUR_TRIP)).click();
		driver.findElement(By.id(Constant.SIGN_IN)).click();
		waitFor(1000);
		WebElement frame = driver.findElement(By.xpath("//*[@id='modal_window']"));
		driver.switchTo().frame(frame);
		logger.log(Level.WARNING, "-- Getting new Frame");
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		wait.until(ExpectedConditions.
				visibilityOfElementLocated(By.xpath("//*[@id='signInButton']")));
		
		logger.log(Level.WARNING, "-- Completed waiting for the sign in button");
		WebElement button = driver.findElement(By.xpath("//*[@id='signInButton']"));
		button.click();
		logger.log(Level.WARNING, "Button clicked");
		
		wait.until(ExpectedConditions.
				visibilityOfElementLocated(By.id("errors1")));
		String errors1 = driver.findElement(By.id("errors1")).getText();
		Assert.assertTrue(errors1.contains("There were errors in your submission"));
		driver.quit();
	}

	private void waitFor(int durationInMilliSeconds) {
		try {
			Thread.sleep(durationInMilliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace(); 
		}
	}

}
