package bruh.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static bruh.util.constants.RegularExpressions.PASSWORD_REG_EXP;
import static bruh.util.constants.ValidationMessages.FIELD_CANNOT_BE_NULL;
import static bruh.util.constants.ValidationMessages.PASSWORD_VALIDATE_MESSAGE;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordDto {

    @NotBlank(message = FIELD_CANNOT_BE_NULL)
    private String login;

    @NotBlank(message = FIELD_CANNOT_BE_NULL)
    @Pattern(regexp = PASSWORD_REG_EXP, message = PASSWORD_VALIDATE_MESSAGE)
    private String password;

    @NotBlank(message = FIELD_CANNOT_BE_NULL)
    @Pattern(regexp = PASSWORD_REG_EXP, message = PASSWORD_VALIDATE_MESSAGE)
    private String newPassword;


}
