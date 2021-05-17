package bruh.model;

import bruh.util.enums.TournamentMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchTournamentDto {

    private long dateFrom;
    private TournamentMode tournamentMode;
    private String username;

}
