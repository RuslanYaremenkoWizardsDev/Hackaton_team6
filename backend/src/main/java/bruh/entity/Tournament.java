package bruh.entity;

import bruh.util.enums.TournamentLevel;
import bruh.util.enums.TournamentMode;
import bruh.util.enums.TournamentScenario;
import bruh.util.enums.TournamentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "tournaments", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"}, name = "uk_name")})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private TournamentMode mode = TournamentMode.CUP;
    private String place;
    private long startDate;
    private long endDate;
    private TournamentLevel tournamentLevel = TournamentLevel.MIDDLE;
    private int participants = 32;
    private TournamentScenario scenario = TournamentScenario.ONE_MATCH;
    private TournamentStatus tournamentStatus = TournamentStatus.IN_PROGRESS;

    public Tournament(String name, String tournamentDescription, String place, long dateStartTournament,
                      long dateLastRegistrationOnTournament) {
        this.name = name;
        this.description = tournamentDescription;
        this.place = place;
        this.startDate = dateStartTournament;
        this.endDate = dateLastRegistrationOnTournament;
    }

}
