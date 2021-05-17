package bruh.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import static bruh.util.constants.ValidationMessages.FIELD_CANNOT_BE_NULL;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteTournamentDto {
    @NotBlank(message = FIELD_CANNOT_BE_NULL)
    String tournamentName;

    @NotBlank(message = FIELD_CANNOT_BE_NULL)
    String role;
}
