package bruh.controllers.tournament;

import bruh.model.TournamentDto;
import bruh.services.tournament.GetTournamentService;
import bruh.util.enums.TournamentStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import static bruh.util.constants.PostMapping.GET_TOURNAMENT_MAPPING;

@RestController
public class GetTournamentController {
    private final GetTournamentService getTournamentService;

    public GetTournamentController(GetTournamentService getTournamentService) {
        this.getTournamentService = getTournamentService;
    }

    @PostMapping(value = GET_TOURNAMENT_MAPPING)
    public List<TournamentDto> getTournaments(@RequestBody TournamentStatus tournamentStatus) {
        return getTournamentService.getTournaments(tournamentStatus);
    }
}
