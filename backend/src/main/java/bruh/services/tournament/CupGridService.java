package bruh.services.tournament;

import bruh.repo.ITournamentParticipantRepo;
import bruh.repo.ITournamentRepo;
import bruh.repo.IUserRepo;

public class CupGridService {
    private final ITournamentRepo iTournamentRepo;
    private final IUserRepo iUserRepo;
    private final ITournamentParticipantRepo iTournamentParticipantRepo;

    public CupGridService(ITournamentRepo iTournamentRepo, IUserRepo iUserRepo,
                          ITournamentParticipantRepo iTournamentParticipantRepo) {
        this.iTournamentRepo = iTournamentRepo;
        this.iUserRepo = iUserRepo;
        this.iTournamentParticipantRepo = iTournamentParticipantRepo;
    }
//
//    public List<UserDto> getStarterCupGrid(String tournamentName) {
//        Tournament tournament = iTournamentRepo.findByName(tournamentName).orElseThrow()
//
//    }
}
