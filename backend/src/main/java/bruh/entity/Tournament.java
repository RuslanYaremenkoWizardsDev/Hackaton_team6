package bruh.entity;

import bruh.util.enums.TournamentLevel;
import bruh.util.enums.TournamentScenario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "")
    @Max(value = 255, message = "")
    private String name;

    @Max(value = 10_000)
    private String description;

    @NotBlank(message = "")
    private String mode;

    private String place;

    @NotNull
    private long startDate;

    @NotNull
    private long endDate;

    private TournamentLevel tournamentLevel = TournamentLevel.MIDDLE;

    private int participants = 32;
    ////"Fields to create:
////- Name of tournament (max length 255 symbols) - required
////- Tournament description (max length 10000 symbols)
////- Mode: cup - required
////- Place (name of place)
////- Datepicker (start date - only future date) - required
////- Datepicker (last registration date - only future date, less than start date) - required
////- Level of tournament (Available values : Advanced, Middle, Begginer) - default value Middle
////- Number of participants ( for cup available values: 128, 64, 32, 16, 8, 4 ) - default value - 32
////- Scenario for tournament (Available values: 1) one-match confrontation, 2) two-match confrontation, 3) to three wins) - default value-one-match confrontation
////- Invited players - dropdown with users (multiple, should be able to add any number of players, less than number of participants)
////Also you should create cup grid"
    private TournamentScenario scenario = TournamentScenario.ONE_MATCH_CONFRONTATION;
//проработать
    @OneToMany
    private List<User> users;


}
