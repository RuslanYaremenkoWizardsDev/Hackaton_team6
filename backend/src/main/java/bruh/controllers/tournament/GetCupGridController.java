package bruh.controllers.tournament;

import bruh.exceptions.IncorrectUserFieldsException;
import bruh.model.CupGridDto;
import bruh.model.UserDto;
import bruh.services.tournament.CupGridService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;
import static bruh.util.constants.PostMapping.GET_FINAL_GRID_MAPPING;
import static bruh.util.constants.PostMapping.GET_STARTER_GRID_MAPPING;

@Slf4j
@RestController
public class GetCupGridController {
    private final CupGridService cupGridService;

    public GetCupGridController(CupGridService cupGridService) {
        this.cupGridService = cupGridService;
    }

    @PostMapping(value = GET_STARTER_GRID_MAPPING)
    public List<UserDto> getStarterCupGrid(@Valid @RequestBody CupGridDto cupGridDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            log.error(errorMessage);
            throw new IncorrectUserFieldsException(errorMessage);
        }

        return cupGridService.getStarterCupGrid(cupGridDto.getTournamentName());
    }

    @PostMapping(value = GET_FINAL_GRID_MAPPING)
    public List<UserDto> getFinalCupGrid(@Valid @RequestBody CupGridDto cupGridDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            log.error(errorMessage);
            throw new IncorrectUserFieldsException(errorMessage);
        }

        return cupGridService.getFinalCupGrid(cupGridService.getStarterCupGrid(cupGridDto.getTournamentName()),
                cupGridDto.getTournamentName());
    }
}
