package bruh.controllers.myaccount;

import bruh.exceptions.IncorrectUserFieldsException;
import bruh.model.ChangePasswordDto;
import bruh.services.myaccount.ChangePasswordService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import static bruh.util.constants.CrossOriginMapping.CROSS_ORIGIN_MAPPING;
import static bruh.util.constants.PostMapping.CHANGE_PASSWORD_MAPPING;


@RestController
@CrossOrigin(value = CROSS_ORIGIN_MAPPING)
public class ChangePasswordController {

    private final ChangePasswordService changePasswordService;

    public ChangePasswordController(ChangePasswordService changePasswordService) {
        this.changePasswordService = changePasswordService;
    }

    @PostMapping(value = CHANGE_PASSWORD_MAPPING)
    public void changePass(@Valid @RequestBody ChangePasswordDto changePasswordDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            throw new IncorrectUserFieldsException(errorMessage);
        }
        changePasswordService.changePass(changePasswordDto.getLogin(), changePasswordDto.getPassword(), changePasswordDto.getNewPassword());
    }

}
