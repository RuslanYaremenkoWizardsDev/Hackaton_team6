package bruh.services.tournament;

import bruh.entity.Tournament;
import bruh.exceptions.IllegalAccessOperationException;
import bruh.exceptions.TournamentNotFoundException;
import bruh.repo.ITournamentRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static bruh.util.constants.LoggerMessages.*;

@Slf4j
@Service
public class TournamentService {
    private final ITournamentRepo tournamentRepo;

    @Autowired
    public TournamentService(ITournamentRepo tournamentRepo) {
        this.tournamentRepo = tournamentRepo;
    }

    public void createTournament(Tournament tournament) {
        tournamentRepo.save(tournament);
        log.info(TOURNAMENT_WAS_SUCCESSFULLY_CREATED, tournament.getName());
    }

    public void deleteTournament(String tournamentName, String role) {
        if (role.equals("admin")) {
            if (!tournamentRepo.deleteByName(tournamentName)) {
                throw new TournamentNotFoundException(String.format(TOURNAMENT_WAS_NOT_FOUND, tournamentName));
            }
        }
        throw new IllegalAccessOperationException(ILLEGAL_ACCESS);
    }
}
