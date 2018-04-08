import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.sun.javafx.PlatformUtil;

public class DriverUtil {
	
	
	WebDriver driver =null;
	WebDriverWait wait = null;
	
	public DriverUtil initialize() {
		if (PlatformUtil.isMac()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver");
			this.driver = new ChromeDriver();
		}
		if (PlatformUtil.isWindows()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			this.driver = new ChromeDriver();
		}
		if (PlatformUtil.isLinux()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
			this.driver = new ChromeDriver();
		}
		
		// Wait definition
		this.wait = new WebDriverWait(driver, 5000);
		return this;
	}
	
	public static class DriverUtilHelper{
		 public static final DriverUtil instance = new DriverUtil().initialize();
	}
	
	public static DriverUtil getInstance() {
		return DriverUtilHelper.instance;
	}

}
