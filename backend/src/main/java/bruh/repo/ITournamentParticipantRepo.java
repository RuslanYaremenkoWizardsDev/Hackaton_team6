package bruh.repo;

import bruh.entity.TournamentParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ITournamentParticipantRepo extends JpaRepository<TournamentParticipant, Long> {

    List<Long> findIdByIdUser(TournamentParticipant tournamentParticipant);

    List<TournamentParticipant> findByIdTournament(long idTournament);

    List<TournamentParticipant> findByIdUser(long idUser);

    Integer countTournamentParticipantByIdTournament(long idTournament);
}
