import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage{

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver,this);
    }

    //Locating username using id
    @FindBy(id = "user-name")
    WebElement username;

    //Locating password using id
    @FindBy(id = "password")
    WebElement password;

    //Locating login button using id
    @FindBy(id = "login-button")
    WebElement loginButton;

    //Locating successMessage using css
    @FindBy(css = "div[class=\"app_logo\"]")
    WebElement successMessage;

    //Locating errorMessage using css
    @FindBy(css = "h3[data-test=\"error\"]")
    WebElement errorMessage;

    //Locating missedDataErrorMessage using css
    @FindBy(css = "div[class=\"error-message-container error\"]")
    WebElement missedDataErrorMessage;

    public void LoginSteps(String user ,String password)
    {
        //Enter username
        username.clear();
        username.sendKeys(user);

        //Enter password
        this.password.sendKeys(password);
        this.password.sendKeys(Keys.ENTER);

    }

}