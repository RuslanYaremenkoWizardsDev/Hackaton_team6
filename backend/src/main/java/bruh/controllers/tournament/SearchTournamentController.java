package bruh.controllers.tournament;

import bruh.model.SearchTournamentDto;
import bruh.model.TournamentDto;
import bruh.services.tournament.SearchTournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import static bruh.util.constants.PostMapping.*;

@RestController
public class SearchTournamentController {
    private final SearchTournamentService searchTournamentService;

    @Autowired
    public SearchTournamentController(SearchTournamentService searchTournamentService) {
        this.searchTournamentService = searchTournamentService;
    }

    @PostMapping(value = SEARCH_BY_DATE_MAPPING)
    public List<TournamentDto> searchTournamentByDate(@RequestBody SearchTournamentDto searchTournamentDto) {
        return searchTournamentService.getTournamentsByDate(searchTournamentDto.getDateFrom());
    }

    @PostMapping(value = SEARCH_BY_TYPE_MAPPING)
    public List<TournamentDto> searchTournamentByType(@RequestBody SearchTournamentDto searchTournamentDto) {
        return searchTournamentService.getTournamentsByType(searchTournamentDto.getTournamentMode());
    }

    @PostMapping(value = SEARCH_BY_USERNAME_MAPPING)
    public List<TournamentDto> searchTournamentByUsername(@RequestBody SearchTournamentDto searchTournamentDto) {
        return searchTournamentService.getTournamentsByUsername(searchTournamentDto.getUsername());
    }
}
