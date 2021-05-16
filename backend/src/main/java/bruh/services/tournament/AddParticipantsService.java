package bruh.services.tournament;

import bruh.entity.Tournament;
import bruh.entity.TournamentParticipant;
import bruh.entity.User;
import bruh.exceptions.InvalidCredentialsException;
import bruh.exceptions.TournamentNotFoundException;
import bruh.repo.IUserRepo;
import bruh.repo.ITournamentParticipantRepo;
import bruh.repo.ITournamentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static bruh.util.constants.LoggerMessages.TOURNAMENT_WAS_NOT_FOUND;
import static bruh.util.constants.LoggerMessages.USER_WAS_NOT_FOUND;

@Service
public class AddParticipantsService {
    private final ITournamentParticipantRepo iTournamentParticipantRepo;
    private final ITournamentRepo iTournamentRepo;
    private final IUserRepo iUserRepo;

    @Autowired
    public AddParticipantsService(ITournamentParticipantRepo iTournamentParticipantRepo, ITournamentRepo iTournamentRepo, IUserRepo iUserRepo) {
        this.iTournamentParticipantRepo = iTournamentParticipantRepo;
        this.iTournamentRepo = iTournamentRepo;
        this.iUserRepo = iUserRepo;
    }

    public void addNewParticipant(String tournamentName, String participantLogin) {
        Tournament tournament = iTournamentRepo.findByName(tournamentName).orElseThrow(()
                -> new TournamentNotFoundException(String.format(TOURNAMENT_WAS_NOT_FOUND, tournamentName)));
        int numberOfParticipants = iTournamentParticipantRepo.countTournamentParticipantByIdTournament(tournament.getId());
        if (numberOfParticipants == tournament.getParticipants()) {

        }
        User user = iUserRepo.findUserByLogin(participantLogin).orElseThrow(()
                -> new InvalidCredentialsException(String.format(USER_WAS_NOT_FOUND, participantLogin)));
        long userId = user.getId();
        long tournamentId = tournament.getId();
        iTournamentParticipantRepo.save(new TournamentParticipant(tournamentId, userId));
    }
}
