package bruh.controllers;

import bruh.exceptions.IncorrectUserFieldsException;
import bruh.model.ChangeSecretWordDto;
import bruh.model.ForgotPasswordDto;
import bruh.services.ChangeSecretWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import static bruh.util.constants.PostMapping.CHANGE_SECRET_WORD_MAPPING;

@RestController
@CrossOrigin(value = "http://localhost:4080")
public class ChangeSecretWordController {

    private final ChangeSecretWordService changeSecretWordService;

    @Autowired
    public ChangeSecretWordController(ChangeSecretWordService changeSecretWordService) {
        this.changeSecretWordService = changeSecretWordService;
    }

    @PostMapping(value = CHANGE_SECRET_WORD_MAPPING)
    public void forgotPass(@Valid @RequestBody ChangeSecretWordDto changeSecretWordDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            throw new IncorrectUserFieldsException(errorMessage);
        }
        changeSecretWordService.restoreSecretWord(changeSecretWordDto.getLogin(), changeSecretWordDto.getSecretWord(), changeSecretWordDto.getNewSecretWord());
    }

}
