package bruh.controllers.tournament;

import bruh.model.TournamentStatisticDto;
import bruh.model.UserStatisticDto;
import bruh.services.tournament.StatisticsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import static bruh.util.constants.PostMapping.TOURNAMENT_STATISTIC_MAPPING;
import static bruh.util.constants.PostMapping.USER_STATISTIC_MAPPING;

@RestController
public class StatisticsController {

    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @PostMapping(value = TOURNAMENT_STATISTIC_MAPPING)
    public List<TournamentStatisticDto> getTournamentStatistic() {
        return statisticsService.createAndGetTournamentStatisticDtoList();
    }

    @PostMapping(value = USER_STATISTIC_MAPPING)
    public List<UserStatisticDto> getUserStatistic() {
        return statisticsService.createAndGetUserStatisticsDtoList();
    }

}
