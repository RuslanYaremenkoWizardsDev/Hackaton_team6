package bruh.services.tournament;

import bruh.entity.Tournament;
import bruh.repo.ITournamentRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CreateTournamentService {
    private final ITournamentRepo tournamentRepo;

    @Autowired
    public CreateTournamentService(ITournamentRepo tournamentRepo) {
        this.tournamentRepo = tournamentRepo;
    }

    public void createTournament(Tournament tournament) {
        tournamentRepo.save(tournament);
        log.info("tournament {} was successfully created", tournament.getName());
    }
}
