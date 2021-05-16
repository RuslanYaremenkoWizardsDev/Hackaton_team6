package bruh.controllers;

import bruh.entity.User;
import bruh.exceptions.IncorrectUserFieldsException;
import bruh.model.ForgotPasswordDto;
import bruh.services.ForgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import static bruh.util.constants.PostMapping.FORGOT_PASSWORD_MAPPING;

@RestController
@CrossOrigin(value = "http://localhost:4080")
public class ForgotPasswordController {

    private final ForgotPasswordService forgotPasswordService;

    @Autowired
    public ForgotPasswordController(ForgotPasswordService forgotPasswordService) {
        this.forgotPasswordService = forgotPasswordService;
    }

    @PostMapping(value = FORGOT_PASSWORD_MAPPING)
    public void forgotPass(@Valid @RequestBody ForgotPasswordDto forgotPasswordDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            throw new IncorrectUserFieldsException(errorMessage);
        }

        forgotPasswordService.restorePass(new User(forgotPasswordDto.getLogin(), forgotPasswordDto.getPassword(), forgotPasswordDto.getSecretWord()));
    }
}
