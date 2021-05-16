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
@Table(name = "tournament_statistic", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"}, name = "uk_name")})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TournamentStatistic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private TournamentMode mode = TournamentMode.CUP;
    private TournamentStatus status = TournamentStatus.IN_PROGRESS;
    private int numbersOfFinishedGames;
    private int numbersOfInProgressGames;
    private int numbersOfCanceledGames;

}

