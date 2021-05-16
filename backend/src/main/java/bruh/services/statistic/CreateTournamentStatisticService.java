package bruh.services.statistic;

import bruh.entity.Tournament;
import bruh.entity.TournamentStatistic;
import bruh.repo.ITournamentStatisticRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Service
public class CreateTournamentStatisticService {
    public ITournamentStatisticRepo iTournamentStatisticRepo;

    @Autowired
    public CreateTournamentStatisticService(ITournamentStatisticRepo iTournamentStatisticRepo) {
        this.iTournamentStatisticRepo = iTournamentStatisticRepo;
    }


    public void createTournamentStatistic(TournamentStatistic tournamentStatistic) {

    }
}
