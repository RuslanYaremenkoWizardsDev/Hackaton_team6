package Pages;

import Core.DriverConfig;
import Interfaces.IAuthPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;

@Getter
public class AuthPage implements IAuthPage {
    WebDriver driver;

    public AuthPage() {
        PageFactory.initElements(DriverConfig.getDriver(), this);
    }


    @FindBy(css = "body > main > div > div.signIn-form__submit-button > button.SignInButton")
    private WebElement submitButton;


//    public static WebElement waitForVisibility(WebElement element, int timeOfWait, int... timeOfTryOut) {
//        WebElement webElement = null;
//        int timeOfRevision = timeOfTryOut.length == 0
//                ? 100
//                : timeOfTryOut[0];
//        try {
//            webElement = new WebDriverWait(DriverConfig.getDriver(),
//                    timeOfWait,
//                    timeOfRevision
//            ).until(ExpectedConditions.visibilityOf(element));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return webElement;
//    }
}

