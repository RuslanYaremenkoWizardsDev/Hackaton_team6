package bruh.controllers.authreg;

import bruh.entity.User;
import bruh.exceptions.IncorrectUserFieldsException;
import bruh.model.UserDto;
import bruh.services.authreg.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import static bruh.util.constants.PostMapping.REGISTRATION_MAPPING;

@RestController
@CrossOrigin(value = "http://localhost:4080")
public class RegistrationController {
    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping(value = REGISTRATION_MAPPING)
    public void registryUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            throw new IncorrectUserFieldsException(errorMessage);
        }

        registrationService.registerUser(new User(userDto.getLogin(), userDto.getPassword(), userDto.getRole()));
    }
}
