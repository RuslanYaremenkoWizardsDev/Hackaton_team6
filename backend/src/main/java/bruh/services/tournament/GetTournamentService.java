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
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import static bruh.util.constants.LoggerMessages.TOURNAMENT_WAS_NOT_FOUND;

@Service
public class GetTournamentService {
    private final ITournamentRepo iTournamentRepo;
    private final ITournamentParticipantRepo iTournamentParticipantRepo;
    private final TournamentBuilder tournamentBuilder;

    public GetTournamentService(ITournamentRepo iTournamentRepo, ITournamentParticipantRepo iTournamentParticipantRepo,
                                TournamentBuilder tournamentBuilder) {
        this.iTournamentRepo = iTournamentRepo;
        this.iTournamentParticipantRepo = iTournamentParticipantRepo;
        this.tournamentBuilder = tournamentBuilder;
    }

    public List<TournamentDto> getTournaments(TournamentStatus tournamentStatus) {
        List<Tournament> tournamentDtos = iTournamentRepo.findAllByTournamentStatus(tournamentStatus);
        List<TournamentDto> tournamentDtos1 = new ArrayList<>();
        for (Tournament tournament : tournamentDtos) {
            String tournamentName = tournament.getName();
            Tournament dbTournament = iTournamentRepo.findByName(tournamentName).orElseThrow(()
                    -> new TournamentNotFoundException(String.format(TOURNAMENT_WAS_NOT_FOUND, tournament.getName())));
            long tournamentId = dbTournament.getId();
            List<TournamentParticipant> tournamentParticipants = iTournamentParticipantRepo.findByIdTournament(tournamentId);
            TournamentDto tournamentDto = tournamentBuilder.buildTournamentDtoByTournament(dbTournament);
            tournamentDto.setTournamentParticipants(tournamentParticipants);
            tournamentDtos1.add(tournamentDto);
        }
        return tournamentDtos1;
    }
}
