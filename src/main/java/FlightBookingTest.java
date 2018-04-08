import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class FlightBookingTest {

    WebDriver driver = null;
    WebDriverWait wait = null;
    

    @BeforeMethod
    public void initialize() {
    	this.driver = DriverUtil.getInstance().driver;
    	this.wait = DriverUtil.getInstance().wait;
    }
    
    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }
    
    @Test
    public void testThatResultsAppearForAOneWayJourney() {
        driver.get(Constant.BASE_URL);
        wait.until(ExpectedConditions.
				visibilityOfElementLocated(By.id("OneWay")));
        driver.findElement(By.id("OneWay")).click();

        driver.findElement(By.id("FromTag")).clear();
        driver.findElement(By.id("FromTag")).sendKeys("Bangalore");

        //wait for the auto complete options to appear for the origin
        wait.until(ExpectedConditions.
				visibilityOfElementLocated(By.id("ui-id-1")));
        List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
        originOptions.get(0).click();

        driver.findElement(By.id("ToTag")).clear();
        driver.findElement(By.id("ToTag")).sendKeys("Delhi");

        //wait for the auto complete options to appear for the destination
        wait.until(ExpectedConditions.
				visibilityOfElementLocated(By.id("ui-id-2")));
        //select the first item from the destination auto complete list
        List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
        destinationOptions.get(0).click();

        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[7]/a")).click();

        //all fields filled in. Now click on search
        driver.findElement(By.id("SearchBtn")).click();

        wait.until(ExpectedConditions.
				visibilityOfElementLocated(By.className("searchSummary")));
        //verify that result appears for the provided journey search
        Assert.assertTrue(isElementPresent(By.className("searchSummary")));

    }


    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
