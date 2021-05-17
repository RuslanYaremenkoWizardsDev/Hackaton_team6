package bruh.controllers.authreg;

import bruh.entity.User;
import bruh.exceptions.IncorrectUserFieldsException;
import bruh.model.UserDto;
import bruh.services.authreg.AuthorizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import static bruh.util.constants.CrossOriginMapping.CROSS_ORIGIN_MAPPING;
import static bruh.util.constants.PostMapping.AUTHORIZATION_MAPPING;

@Slf4j
@RestController
@CrossOrigin(value = CROSS_ORIGIN_MAPPING)
public class AuthorizationController {
    private final AuthorizationService authorizationService;

    @Autowired
    public AuthorizationController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @PostMapping(value = AUTHORIZATION_MAPPING)
    public String authorizeUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            log.error(errorMessage);
            throw new IncorrectUserFieldsException(errorMessage);
        }

        return authorizationService.authorizeUser(new User(userDto.getLogin(), userDto.getPassword(), userDto.getRole()));
    }
}
