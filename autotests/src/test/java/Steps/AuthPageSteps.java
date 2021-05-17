package Steps;

import Core.DriverConfig;
import Pages.AuthPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static Core.Config.*;

public class AuthPageSteps extends Steps{
    private final Actions action =  new Actions(DriverConfig.getDriver());

    @Given("^I open auth page$")
    public void iOpenMainPage(){
        logger.info("I open auth page");
        DriverConfig.getDriver().get(SITE_URL);
    }

    @And("^I check submitButton visibility$")
    public void iCheckSubmitButtonVisibility() {
        logger.info("I check submitButton visibility");
    }
}
