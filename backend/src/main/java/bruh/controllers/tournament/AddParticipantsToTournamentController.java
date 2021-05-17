package bruh.controllers.tournament;

import bruh.exceptions.IncorrectTournamentFieldsException;
import bruh.model.ParticipantDto;
import bruh.services.tournament.AddParticipantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import static bruh.util.constants.PostMapping.ADD_PARTICIPANT_MAPPING;

@RestController
public class AddParticipantsToTournamentController {
    private final AddParticipantsService addParticipantsService;

    @Autowired
    public AddParticipantsToTournamentController(AddParticipantsService addParticipantsService) {
        this.addParticipantsService = addParticipantsService;
    }

    @PostMapping(value = ADD_PARTICIPANT_MAPPING)
    public void addParticipants(@Valid @RequestBody ParticipantDto participantDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            throw new IncorrectTournamentFieldsException(errorMessage);
        }

        addParticipantsService.addNewParticipant(participantDto.getNameTournament(), participantDto.getLogin());
    }
}
