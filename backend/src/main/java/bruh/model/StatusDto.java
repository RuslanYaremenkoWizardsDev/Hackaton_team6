package bruh.model;

import bruh.util.enums.TournamentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusDto {

    private TournamentStatus tournamentStatus = TournamentStatus.ACTIVE;
}
