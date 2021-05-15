package bruh.controllers;

import bruh.model.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import static bruh.util.constants.PostMapping.AUTHORIZATION_MAPPING;

@Slf4j
@RestController
public class AuthorizationController {

    @PostMapping(value = AUTHORIZATION_MAPPING)
    public String authorizeUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {


    }
}
