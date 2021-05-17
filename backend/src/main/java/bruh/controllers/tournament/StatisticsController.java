package bruh.controllers.tournament;

import bruh.exceptions.IncorrectTournamentFieldsException;
import bruh.exceptions.IncorrectUserFieldsException;
import bruh.model.DeleteTournamentDto;
import bruh.model.SearchTournamentDto;
import bruh.model.TournamentStatisticDto;
import bruh.model.UserStatisticDto;
import bruh.services.tournament.StatisticsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;
import static bruh.util.constants.PostMapping.*;

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
