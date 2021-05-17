package Steps;

import Core.DriverConfig;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.junit.Assert;

import static Core.Config.ADMIN_KEY;
import static Core.Config.REG_PAGE_URL;

public class RegPageSteps extends Steps{

    @Given("^I open reg page$")
    public void iOpenRegPage(){
        logger.info("I open reg page");
        DriverConfig.getDriver().get(REG_PAGE_URL);
    }

    @And("^I fill in field Login$")
    public void fillInFieldLogin() {
        logger.info("I fill in field Login");
        iRegPage.getFieldLoginReg().sendKeys("Testname1");
    }

    @And("^I fill in field Password$")
    public void iFillInFieldPassword() {
        logger.info("I fill in field Password");
        iRegPage.getFieldPasswordReg().sendKeys("qwerty123");
    }

    @And("^I fill in field Confirm password$")
    public void iFillInFieldConfirmPassword() {
        logger.info("I fill in field Confirm password");
        iRegPage.getFieldConfirmPasswordReg().sendKeys("qwerty123");
    }

    @And("^I click Sign up button$")
    public void iClickSubmitButton() {
        logger.info("I click Sign up button");
        iRegPage.getSignUpButton().click();
    }

    @And("^I switch registration form to admin$")
    public void iSwitchRegistrationFormToAdmin() {
        logger.info("I switch registration form to admin");
        iRegPage.getSwitchToAdminRegButton().click();
    }

    @And("^I fill in the admin field login$")
    public void iFillInTheAdminFieldLogin() {
        logger.info("I fill in the admin field login");
        iRegPage.getAdminFieldLoginReg().sendKeys("testAdmin123");
    }

    @And("^I fill in the admin field password$")
    public void iFillInTheAdminFieldPassword() {
        logger.info("I fill in the admin field password");
        iRegPage.getAdminPasswordInput_SignUp().sendKeys("qwerty123");
    }

    @And("^I fill in the admin field confirm password$")
    public void iFillInTheAdminFieldConfirmPassword() {
        logger.info("I fill in the admin field confirm password");
        iRegPage.getAdminConfirmPasswordInput_SignUp().sendKeys("qwerty123");
    }

    @And("^I fill in the admin key field$")
    public void iFillInTheAdminKeyField() {
        logger.info("I fill in the admin key field");
        iRegPage.getAdminKey().sendKeys(ADMIN_KEY);
    }

    @And("^I click log_as_guest_button$")
    public void iClickLog_as_guest_button() {
        logger.info("I click log_as_guest_button");
        iRegPage.getLog_as_guest_button().click();
    }

    @And("^I click Sign in link$")
    public void iClickSignInLink() {
        logger.info("I click Sign in link");
        iRegPage.getSignInLink().click();
    }

    @And("^I check that auth page opened$")
    public void iCheckThatAuthPageOpened() {
        logger.info("I check that auth page opened");
        Assert.assertEquals(
                "auth page does not open",
                DriverConfig.driver.getTitle(),
                "Sign In"
        );
    }
}
