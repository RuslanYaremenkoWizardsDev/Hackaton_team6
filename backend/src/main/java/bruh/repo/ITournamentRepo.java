package bruh.repo;

import bruh.entity.Tournament;
import bruh.util.enums.TournamentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ITournamentRepo extends JpaRepository<Tournament, Long> {
    List<Tournament> findAllByTournamentStatus(TournamentStatus status);
    Optional<Tournament> findByName(String name);
    Optional<Tournament> findById(Long id);
}
