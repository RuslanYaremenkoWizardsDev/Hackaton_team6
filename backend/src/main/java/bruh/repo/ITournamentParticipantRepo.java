package bruh.repo;

import bruh.entity.TournamentParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ITournamentParticipantRepo extends JpaRepository<TournamentParticipant, Long> {
    List<TournamentParticipant> findByIdTournament(long idTournament);
    List<Long> findByIdUser(TournamentParticipant tournamentParticipant);
}
