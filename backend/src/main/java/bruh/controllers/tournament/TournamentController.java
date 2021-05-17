package bruh.controllers.tournament;

import bruh.exceptions.IncorrectTournamentFieldsException;
import bruh.model.DeleteTournamentDto;
import bruh.model.TournamentDto;
import bruh.services.tournament.TournamentService;
import bruh.util.builders.TournamentBuilder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import static bruh.util.constants.PostMapping.CREATE_TOURNAMENT_MAPPING;
import static bruh.util.constants.PostMapping.DELETE_TOURNAMENT_MAPPING;

@RestController
public class TournamentController {

    private final TournamentService tournamentService;
    private final TournamentBuilder tournamentBuilder;

    public TournamentController(TournamentService tournamentService, TournamentBuilder tournamentBuilder) {
        this.tournamentService = tournamentService;
        this.tournamentBuilder = tournamentBuilder;
    }

    @PostMapping(value = CREATE_TOURNAMENT_MAPPING)
    public void createNewTournament(@Valid @RequestBody TournamentDto tournamentDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            throw new IncorrectTournamentFieldsException(errorMessage);
        }

        tournamentService.createTournament(
                tournamentBuilder.buildTournamentByTournamentDto(tournamentDto));
    }

    @PostMapping(value = DELETE_TOURNAMENT_MAPPING)
    public void deleteTournament(@Valid @RequestBody DeleteTournamentDto tournamentDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            throw new IncorrectTournamentFieldsException(errorMessage);
        }

        tournamentService.deleteTournament(tournamentDto.getTournamentName(), tournamentDto.getRole());
    }
}
