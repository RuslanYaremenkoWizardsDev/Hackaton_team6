package bruh.controllers;

import bruh.exceptions.IncorrectUserFieldsException;
import bruh.model.ChangeLoginDto;
import bruh.services.ChangeLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static bruh.util.constants.PostMapping.CHANGE_LOGIN_MAPPING;

@RestController
@CrossOrigin(value = "http://localhost:4080")
public class ChangeLoginController {

    private final ChangeLoginService changeLoginService;

    @Autowired
    public ChangeLoginController(ChangeLoginService changeLoginService) {
        this.changeLoginService = changeLoginService;
    }

    @PostMapping(value = CHANGE_LOGIN_MAPPING)
    public void changeLog(@Valid @RequestBody ChangeLoginDto changeLoginDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            throw new IncorrectUserFieldsException(errorMessage);
        }
        changeLoginService.toChangeLogin(changeLoginDto.getLogin(), changeLoginDto.getNewLogin());
    }
}
