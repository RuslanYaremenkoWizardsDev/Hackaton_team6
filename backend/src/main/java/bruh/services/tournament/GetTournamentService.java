package bruh.services.tournament;

import bruh.entity.Tournament;
import bruh.entity.TournamentParticipant;
import bruh.exceptions.InvalidCredentialsException;
import bruh.exceptions.TournamentNotFoundException;
import bruh.model.TournamentDto;
import bruh.repo.ITournamentParticipantRepo;
import bruh.repo.ITournamentRepo;
import bruh.util.builders.TournamentBuilder;
import bruh.util.enums.TournamentStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import static bruh.util.constants.LoggerMessages.TOURNAMENT_WAS_NOT_FOUND;

@Slf4j
@Service
public class GetTournamentService {
    private final ITournamentRepo iTournamentRepo;
    private final TournamentBuilder tournamentBuilder;

    public GetTournamentService(ITournamentRepo iTournamentRepo,
                                TournamentBuilder tournamentBuilder) {
        this.iTournamentRepo = iTournamentRepo;
        this.tournamentBuilder = tournamentBuilder;
    }

    public List<TournamentDto> getTournaments(TournamentStatus tournamentStatus) {
        List<Tournament> tournaments = iTournamentRepo.findTournamentsByTournamentStatus(tournamentStatus);
        List<TournamentDto> tournamentDtos = new ArrayList<>();

        for (Tournament tournament : tournaments) {
            tournamentDtos.add(tournamentBuilder.buildTournamentDtoByTournament(tournament));
        }
        return tournamentDtos;
    }
}
