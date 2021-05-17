package bruh.model;

import bruh.entity.TournamentParticipant;
import bruh.util.enums.TournamentLevel;
import bruh.util.enums.TournamentMode;
import bruh.util.enums.TournamentScenario;
import bruh.util.enums.TournamentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import static bruh.util.constants.ValidationMessages.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TournamentDto {

    @NotBlank(message = FIELD_CANNOT_BE_NULL)
    @Size(max = 255, min = 1, message = TOURNAMENT_NAME_MAX_SYMBOLS)
    private String name;

    @Size(max = 10000, min = 1, message = TOURNAMENT_DESCRIPTION_MAX_SYMBOLS)
    private String description;

    private TournamentMode mode = TournamentMode.CUP;

    private String place;

    @NotNull(message = FIELD_CANNOT_BE_NULL)
    private long startDate;

    @NotNull(message = FIELD_CANNOT_BE_NULL)
    private long endDate;

    private TournamentLevel tournamentLevel = TournamentLevel.MIDDLE;

    @Max(value = 128, message = MAX_NUMBER_OF_PARTICIPANT)
    private int participants = 32;

    private TournamentScenario scenario = TournamentScenario.ONE_MATCH;

    private TournamentStatus status = TournamentStatus.ACTIVE;

    private List<TournamentParticipant> tournamentParticipants;

    private int currentParticipants = 0;
}
