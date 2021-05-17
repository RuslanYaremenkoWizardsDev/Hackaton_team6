package bruh.services.tournament;

import bruh.entity.User;
import bruh.model.TournamentStatisticDto;
import bruh.model.UserStatisticDto;
import bruh.repo.ITournamentRepo;
import bruh.repo.IUserRepo;
import bruh.util.enums.TournamentMode;
import bruh.util.enums.TournamentStatus;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticsService {
    private final ITournamentRepo tournamentRepo;
    private final IUserRepo userRepo;

    public StatisticsService(ITournamentRepo tournamentRepo, IUserRepo userRepo) {
        this.tournamentRepo = tournamentRepo;
        this.userRepo = userRepo;
    }

    public List<TournamentStatisticDto> createAndGetTournamentStatisticDtoList() {
        List<TournamentStatisticDto> tournamentStatisticDtos = new ArrayList<>();
        int finished = tournamentRepo.countTournamentByTournamentStatusAndMode(TournamentStatus.FINISHED, TournamentMode.CUP);
        int inProgress =
                tournamentRepo.countTournamentByTournamentStatusAndMode(TournamentStatus.IN_PROGRESS, TournamentMode.CUP);
        int initialized = tournamentRepo.countTournamentByTournamentStatusAndMode(TournamentStatus.ACTIVE, TournamentMode.CUP);
        tournamentStatisticDtos.add(new TournamentStatisticDto(TournamentMode.CUP, finished, inProgress, initialized));
        return tournamentStatisticDtos;
    }

    public List<UserStatisticDto> createAndGetUserStatisticsDtoList() {
        List<User> userList = userRepo.findAll();
        return userList
                .stream()
                .map(user -> new UserStatisticDto(user.getLogin(), user.getGames(), user.getDraws(), user.getLoses(),
                        user.getWins(), user.getPower()))
                .collect(Collectors.toList());
    }
}
