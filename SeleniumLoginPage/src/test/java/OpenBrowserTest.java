import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class OpenBrowserTest{

    WebDriver driver = null;
    LoginPage login ;

    @BeforeTest
    public void openBrowser() throws InterruptedException {
        //1- Bridge between test script and browser
        String chromePath = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe" ;
        System.setProperty("webdriver.chrome.driver",chromePath);

        //2- create new object of WebDriver
        driver = new ChromeDriver();

        //3-maximize screen and sleep 3sec
        driver.manage().window().maximize();
        Thread.sleep(3000);

        //4-create new object of LoginPage
        login = new LoginPage(driver);

    }

    @Test
    public void testLoginPageElements() throws InterruptedException {

        driver.navigate().to("https://www.saucedemo.com/");

        Thread.sleep(3000);

        //First Assertion
        Assert.assertTrue(login.username.isDisplayed(), "Username input is not displayed");
        //Second Assertion
        Assert.assertTrue(login.password.isDisplayed(), "Password input is not displayed");
        //Third Assertion
        Assert.assertTrue(login.loginButton.isDisplayed(), "Login button is not displayed");
    }



    @Test
    public void testValidData() throws InterruptedException {

        driver.navigate().to("https://www.saucedemo.com/");

        Thread.sleep(1000);

        login.LoginSteps("standard_user","secret_sauce");

        Thread.sleep(3000);

        String expectedResult = "Swag Labs";
        String actualResult = login.successMessage.getText();

        //Assertion
        Assert.assertTrue(actualResult.contains(expectedResult),"Swag Labs message didn't display after login");

    }


    @Test
    public void testInValidData() throws InterruptedException {

        driver.navigate().to("https://www.saucedemo.com");

        login.LoginSteps("tom","Super!");

        Thread.sleep(3000);

        String expectedResult = "Epic sadface: Username and password do not match any user in this service";

        String actualResult = login.errorMessage.getText();

        //Assertion
        Assert.assertTrue(actualResult.contains(expectedResult),"Error message text is incorrect for invalid data");

    }

    @Test
    public void testNoUsername() throws InterruptedException {

        driver.navigate().to("https://www.saucedemo.com");

        login.LoginSteps("","password");

        Thread.sleep(3000);

        String expectedResult = "Epic sadface: Username is required";

        String actualResult = login.missedDataErrorMessage.getText();

        //Assertion
        Assert.assertTrue(actualResult.contains(expectedResult),"Error message text is incorrect for empty username");


    }

    @Test
    public void testNoPassword() throws InterruptedException {

        driver.navigate().to("https://www.saucedemo.com");

        login.LoginSteps("username","");

        Thread.sleep(3000);

        String expectedResult = "Epic sadface: Password is required";

        String actualResult = login.missedDataErrorMessage.getText();

        //First Assertion
        Assert.assertTrue(actualResult.contains(expectedResult),"Error message text is incorrect for empty password");

    }

    @AfterTest
    public void closeDriver()
    {
        // close driver
        driver.quit();

    }

}
