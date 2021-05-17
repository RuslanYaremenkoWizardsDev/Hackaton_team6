package bruh.repo;

import bruh.entity.Tournament;
import bruh.util.enums.TournamentMode;
import bruh.util.enums.TournamentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ITournamentRepo extends JpaRepository<Tournament, Long> {
    List<Tournament> findTournamentsByTournamentStatus(TournamentStatus status);

    Optional<Tournament> findByName(String name);

    List<Tournament> findTournamentsByMode(TournamentMode tournamentMode);

    List<Tournament> findTournamentsByIdIn(List<Long> longs);

    boolean deleteByName(String name);

    Integer countTournamentByTournamentStatusAndMode(TournamentStatus tournamentStatus, TournamentMode tournamentMode);
}
