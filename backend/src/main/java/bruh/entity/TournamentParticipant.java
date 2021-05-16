package bruh.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@Table(name = "participants_tournament")
@NoArgsConstructor
public class TournamentParticipant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long idTournament;

    private Long idUser;

    public TournamentParticipant(long idTournament, long idUser) {
        this.idTournament = idTournament;
        this.idUser = idUser;
    }
}
