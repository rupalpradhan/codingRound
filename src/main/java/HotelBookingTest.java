import java.util.logging.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class HotelBookingTest {
	Logger logger = Logger.getLogger(SignInTest.class.getName());

    WebDriver driver = null;
    
    public HotelBookingTest(){
    	this.driver = DriverUtil.getInstance().driver;
    	PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Hotels")
    private WebElement hotelLink;

    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;

    @Test
    public void shouldBeAbleToSearchForHotels() {
        driver.get(Constant.BASE_URL);
        hotelLink.click();
        localityTextBox.sendKeys(Constant.INDIRANAGAR_BANGALORE);
        new Select(travellerSelection).selectByVisibleText(Constant.SEARCH_ONE_ROOM_FOR_TWO_ADULT);
        searchButton.click();
        driver.quit();
    }
}
