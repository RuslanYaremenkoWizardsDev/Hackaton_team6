package bruh.services.statistic;

import bruh.entity.Tournament;
import bruh.entity.TournamentParticipant;
import bruh.entity.TournamentStatistic;
import bruh.exceptions.TournamentNotFoundException;
import bruh.model.TournamentDto;
import bruh.model.TournamentStatisticDto;
import bruh.repo.ITournamentStatisticRepo;
import bruh.util.enums.TournamentStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static bruh.util.constants.LoggerMessages.TOURNAMENT_WAS_NOT_FOUND;

@Service
public class GetTournamentStatisticService {
    private final ITournamentStatisticRepo iTournamentStatisticRepo;


    public GetTournamentStatisticService(ITournamentStatisticRepo iTournamentStatisticRepo) {
        this.iTournamentStatisticRepo = iTournamentStatisticRepo;
    }

  /*  public List<TournamentStatisticDto> getTournamentStatistics(TournamentStatistic tournamentStatistic) {

    }*/
}
