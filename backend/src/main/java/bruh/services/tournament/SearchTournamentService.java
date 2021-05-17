package bruh.services.tournament;

import bruh.entity.Tournament;
import bruh.entity.TournamentParticipant;
import bruh.entity.User;
import bruh.exceptions.InvalidCredentialsException;
import bruh.model.TournamentDto;
import bruh.repo.ITournamentParticipantRepo;
import bruh.repo.ITournamentRepo;
import bruh.repo.IUserRepo;
import bruh.util.builders.TournamentBuilder;
import bruh.util.enums.TournamentMode;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static bruh.util.constants.LoggerMessages.USER_WAS_NOT_FOUND;

@Service
public class SearchTournamentService {
    private final ITournamentRepo tournamentRepo;
    private final IUserRepo userRepo;
    private final ITournamentParticipantRepo iTournamentParticipantRepo;
    private final TournamentBuilder tournamentBuilder;

    public SearchTournamentService(ITournamentRepo tournamentRepo, IUserRepo userRepo,
                                   ITournamentParticipantRepo iTournamentParticipantRepo, TournamentBuilder tournamentBuilder) {
        this.tournamentRepo = tournamentRepo;
        this.userRepo = userRepo;
        this.iTournamentParticipantRepo = iTournamentParticipantRepo;
        this.tournamentBuilder = tournamentBuilder;
    }

    public List<TournamentDto> getTournamentsByDate(long date) {
        List<Tournament> tournamentList = tournamentRepo.findAll();
        List<TournamentDto> tournamentDtos = new ArrayList<>();
        for (Tournament tournament : tournamentList) {
            if (tournament.getEndDate() < date) {
                tournamentDtos.add(tournamentBuilder.buildTournamentDtoByTournament(tournament));
            }
        }
        return tournamentDtos;
    }

    public List<TournamentDto> getTournamentsByType(TournamentMode tournamentMode) {
        return tournamentRepo
                .findTournamentsByMode(tournamentMode)
                .stream()
                .map(tournamentBuilder::buildTournamentDtoByTournament)
                .collect(Collectors.toList());
    }

    public List<TournamentDto> getTournamentsByUsername(String login) {
        User user = userRepo.findUserByLogin(login).orElseThrow(()
                -> new InvalidCredentialsException(String.format(USER_WAS_NOT_FOUND, login)));
        List<TournamentParticipant> tournamentParticipant = iTournamentParticipantRepo.findByIdUser(user.getId());
        List<Long> tournamentIds =
                tournamentParticipant
                        .stream()
                        .map(TournamentParticipant::getIdTournament)
                        .collect(Collectors.toList());
        return tournamentRepo
                .findTournamentsByIdIn(tournamentIds)
                .stream()
                .map(tournamentBuilder::buildTournamentDtoByTournament)
                .collect(Collectors.toList());
    }
}
