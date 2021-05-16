package bruh.controllers.statistic;

import bruh.entity.TournamentStatistic;
import bruh.exceptions.IncorrectTournamentFieldsException;
import bruh.services.statistic.CreateTournamentStatisticService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static bruh.util.constants.PostMapping.CREATE_TOURNAMENT_STATISTIC_MAPPING;

@RestController
public class CreateTournamentStatisticController {

    private final CreateTournamentStatisticService createTournamentStatisticService;

    public CreateTournamentStatisticController(CreateTournamentStatisticService createTournamentStatisticService) {
        this.createTournamentStatisticService = createTournamentStatisticService;
    }


    @PostMapping(value = CREATE_TOURNAMENT_STATISTIC_MAPPING)
    public void createTournamentStatisticController(@Valid @RequestBody TournamentStatistic tournamentStatistic, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            throw new IncorrectTournamentFieldsException(errorMessage);
        }
        createTournamentStatisticService.createTournamentStatistic(tournamentStatistic);
    }
}
