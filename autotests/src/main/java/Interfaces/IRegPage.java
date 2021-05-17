package Interfaces;

import org.openqa.selenium.WebElement;

public interface IRegPage {
    WebElement getFieldLoginReg();
    WebElement getFieldPasswordReg();
    WebElement getFieldConfirmPasswordReg();
    WebElement getSwitchToAdminRegButton();
    WebElement getAdminFieldLoginReg();
    WebElement getAdminPasswordInput_SignUp();
    WebElement getAdminConfirmPasswordInput_SignUp();
    WebElement getAdminKey();
    WebElement getSignUpButton();
    WebElement getLog_as_guest_button();
    WebElement getSignInLink();
}
