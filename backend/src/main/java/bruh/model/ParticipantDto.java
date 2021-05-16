package bruh.model;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import static bruh.util.constants.ValidationMessages.FIELD_CANNOT_BE_NULL;

@Data
public class ParticipantDto {
    @NotBlank(message = FIELD_CANNOT_BE_NULL)
    private String login;
    @NotBlank(message = FIELD_CANNOT_BE_NULL)
    private String nameTournament;
}
