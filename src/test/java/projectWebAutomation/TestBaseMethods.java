package projectWebAutomation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class TestBaseMethods {

    public WebDriver driver;
    //public SoftAssert softAssert = new SoftAssert();
    @BeforeMethod(alwaysRun = true)
    public void setUpMethod(){
        //  softAssert =  new SoftAssert();
        WebDriverManager.chromedriver().setup();
        driver  = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }
    @AfterMethod
    public void tearDownMethod(){

        driver.quit();
         //softAssert.assertAll();
    }
}
