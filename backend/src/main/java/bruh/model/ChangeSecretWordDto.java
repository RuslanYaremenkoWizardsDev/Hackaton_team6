package bruh.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import static bruh.util.constants.ValidationMessages.FIELD_CANNOT_BE_NULL;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeSecretWordDto {

    @NotBlank(message = FIELD_CANNOT_BE_NULL)
    private String login;

    @NotBlank(message = FIELD_CANNOT_BE_NULL)
    private String secretWord;

    @NotBlank(message = FIELD_CANNOT_BE_NULL)
    private String newSecretWord;
}
