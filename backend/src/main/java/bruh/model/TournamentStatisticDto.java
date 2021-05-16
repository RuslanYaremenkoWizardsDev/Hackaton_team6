package bruh.model;

import bruh.util.enums.TournamentMode;
import bruh.util.enums.TournamentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

import static bruh.util.constants.ValidationMessages.FIELD_CANNOT_BE_NULL;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TournamentStatisticDto {
    @NotBlank(message = FIELD_CANNOT_BE_NULL)
    @Max(value = 255, message = "Max name  symbols 255")
    private String name;

    private TournamentMode mode = TournamentMode.CUP;

    private TournamentStatus status = TournamentStatus.IN_PROGRESS;

    private int numbersOfFinishedGames;

    private int numbersOfInProgressGames;

    private int numbersOfCanceledGames;

}
