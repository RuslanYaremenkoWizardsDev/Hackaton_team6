package bruh.model;

import bruh.util.enums.TournamentMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TournamentStatisticDto {

    private TournamentMode tournamentMode;
    private int finished;
    private int inProgress;
    private int initialized;
}
