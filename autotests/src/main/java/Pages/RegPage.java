package Pages;

import Core.DriverConfig;
import Interfaces.IRegPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class RegPage implements IRegPage {
    WebDriver driver;

    public RegPage() {
        PageFactory.initElements(DriverConfig.getDriver(), this);
    }

    @FindBy(css = "#UserNameInput_SignUp")
    private WebElement fieldLoginReg;

    @FindBy(css = "#PasswordInput_SignUp")
    private WebElement fieldPasswordReg;

    @FindBy(css = "#ConfirmPasswordInput")
    private WebElement fieldConfirmPasswordReg;

    @FindBy(css = "#user__block > div.container__button > button.SignUpButton")
    private WebElement signUpButton;

    @FindBy(css = "#adminButton")
    private WebElement switchToAdminRegButton;

    @FindBy(css = "#AdminNameInput_SignUp")
    private WebElement adminFieldLoginReg;

    @FindBy(css = "#AdminPasswordInput_SignUp")
    private WebElement adminPasswordInput_SignUp;

    @FindBy(css = "#AdminConfirmPasswordInput")
    private WebElement adminConfirmPasswordInput_SignUp;

    @FindBy(css = "#AdminKey")
    private WebElement adminKey;

    @FindBy(css = "#user__block > div.container__button > button.GuestButton")
    private WebElement log_as_guest_button;

    @FindBy(css = "#user__block > div.form__lastWords > a")
    private WebElement signInLink;

    @FindBy(css = "#user__block > div.form__lastWords > a")
    private WebElement authPageTitle;
}
