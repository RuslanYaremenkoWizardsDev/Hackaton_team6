package bruh.services.tournament;

import bruh.entity.Tournament;
import bruh.entity.TournamentParticipant;
import bruh.entity.User;
import bruh.exceptions.InvalidCredentialsException;
import bruh.exceptions.InvalidParticipantsNumberException;
import bruh.exceptions.TournamentNotFoundException;
import bruh.exceptions.UserIsAlreadyInTournamentException;
import bruh.repo.IUserRepo;
import bruh.repo.ITournamentParticipantRepo;
import bruh.repo.ITournamentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import static bruh.util.constants.LoggerMessages.*;

@Service
public class AddParticipantsService {
    private final ITournamentParticipantRepo iTournamentParticipantRepo;
    private final ITournamentRepo iTournamentRepo;
    private final IUserRepo iUserRepo;

    @Autowired
    public AddParticipantsService(ITournamentParticipantRepo iTournamentParticipantRepo, ITournamentRepo iTournamentRepo,
                                  IUserRepo iUserRepo) {
        this.iTournamentParticipantRepo = iTournamentParticipantRepo;
        this.iTournamentRepo = iTournamentRepo;
        this.iUserRepo = iUserRepo;
    }

    public void addNewParticipant(String tournamentName, String participantLogin) {
        Tournament tournament = iTournamentRepo.findByName(tournamentName).orElseThrow(()
                -> new TournamentNotFoundException(String.format(TOURNAMENT_WAS_NOT_FOUND, tournamentName)));
        int numberOfParticipants = iTournamentParticipantRepo.countTournamentParticipantByIdTournament(tournament.getId());
        if (numberOfParticipants == tournament.getParticipants()) {
            throw new InvalidParticipantsNumberException(NO_PLACE);
        }
        User user = iUserRepo.findUserByLogin(participantLogin).orElseThrow(()
                -> new InvalidCredentialsException(String.format(USER_WAS_NOT_FOUND, participantLogin)));
        isUserAlreadyInTournament(tournament, user);
        long userId = user.getId();
        long tournamentId = tournament.getId();
        iTournamentParticipantRepo.save(new TournamentParticipant(tournamentId, userId));
    }

    private void isUserAlreadyInTournament(Tournament tournament, User user) {
        long tournamentId = tournament.getId();
        List<TournamentParticipant> tournamentParticipantList = iTournamentParticipantRepo.findByIdTournament(tournamentId);
        for (TournamentParticipant tournamentParticipant : tournamentParticipantList) {
            if (tournamentParticipant.getIdUser() == (long) user.getId()) {
                throw new UserIsAlreadyInTournamentException(
                        String.format(USER_ALREADY_IN_TOURNAMENT, user.getLogin(), tournament.getName()));
            }
        }
    }
}
