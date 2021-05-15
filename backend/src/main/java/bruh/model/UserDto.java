package bruh.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import static bruh.util.constants.RegularExpressions.LOGIN_REG_EXP;
import static bruh.util.constants.RegularExpressions.PASSWORD_REG_EXP;
import static bruh.util.constants.ValidationMessages.*;

public class UserDto {

    @NotBlank(message = FIELD_CANNOT_BE_NULL)
    @Pattern(regexp = LOGIN_REG_EXP, message = LOGIN_VALIDATE_MESSAGE)
    private String login;

    @NotBlank(message = FIELD_CANNOT_BE_NULL)
    @Pattern(regexp = PASSWORD_REG_EXP, message = PASSWORD_VALIDATE_MESSAGE)
    private String password;
}
