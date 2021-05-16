package bruh.controllers.tournament;

import bruh.exceptions.IncorrectTournamentFieldsException;
import bruh.model.TournamentDto;
import bruh.services.tournament.CreateTournamentService;
import bruh.util.builders.TournamentBuilder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import static bruh.util.constants.PostMapping.CREATE_TOURNAMENT_MAPPING;

@RestController
public class CreateTournamentController {

    private final CreateTournamentService createTournamentService;
    private final TournamentBuilder tournamentBuilder;

    public CreateTournamentController(CreateTournamentService createTournamentService, TournamentBuilder tournamentBuilder) {
        this.createTournamentService = createTournamentService;
        this.tournamentBuilder = tournamentBuilder;
    }

    @PostMapping(value = CREATE_TOURNAMENT_MAPPING)
    public void createNewTournament(@Valid @RequestBody TournamentDto tournamentDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            throw new IncorrectTournamentFieldsException(errorMessage);
        }

        createTournamentService.createTournament(
               tournamentBuilder.buildTournamentByTournamentDto(tournamentDto));
    }
}
