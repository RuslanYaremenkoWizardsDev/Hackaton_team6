package bruh.controllers;

import bruh.controllers.authreg.RegistrationController;
import bruh.entity.User;
import bruh.exceptions.advicecontrollers.UserAdviceController;
import bruh.model.UserDto;
import bruh.services.authreg.RegistrationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import java.util.stream.Stream;
import static bruh.util.constants.ValidationMessages.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RegistrationControllerTest {
    private static final String USER = "user";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String URL_TEMPLATE = "/registration";
    private static final String APPLICATION_JSON_CHARSET_UTF_8 = "application/json; charset=utf-8";
    private static final String TEXT_PLAIN_CHARSET_ISO = "text/plain;charset=ISO-8859-1";
    private final RegistrationService registrationService = Mockito.mock(RegistrationService.class);
    private final BindingResult bindingResult = Mockito.mock(BindingResult.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        RegistrationController cut = new RegistrationController(registrationService);
        mockMvc = MockMvcBuilders
                .standaloneSetup(cut)
                .setControllerAdvice(new UserAdviceController())
                .build();
    }

    public String mapToJson(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    static Stream<Arguments> registerUserTestNominal() {
        return Stream.of(
                Arguments.arguments(new UserDto("trolan2", "1234567", USER)),
                Arguments.arguments(new UserDto("jeid1", "abcdef", USER)),
                Arguments.arguments(new UserDto("ufora1", "happy123", USER)),
                Arguments.arguments(new UserDto("antenna1", "asdcvbasd7", USER))

        );
    }

    @ParameterizedTest
    @MethodSource("registerUserTestNominal")
    void registerUserTest(UserDto userDTO) throws Exception {
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        Mockito.doNothing().when(registrationService).registerUser(new User(userDTO.getLogin(), userDTO.getPassword(),
                userDTO.getRole()));

        mockMvc.perform(post(URL_TEMPLATE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(userDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string(""))
                .andReturn();
    }

    static Stream<Arguments> registerUserExceptionTestNominal() {
        return Stream.of(
                Arguments.arguments(new UserDto(null, "asdasdasd", USER), FIELD_CANNOT_BE_NULL),
                Arguments.arguments(new UserDto("фывфы", "123456", USER), LOGIN_VALIDATE_MESSAGE),
                Arguments.arguments(new UserDto("asdВВВв", "123456asd", USER), LOGIN_VALIDATE_MESSAGE),
                Arguments.arguments(new UserDto("trolan", null, USER), FIELD_CANNOT_BE_NULL),
                Arguments.arguments(new UserDto("ufora", "ываыва", USER), PASSWORD_VALIDATE_MESSAGE),
                Arguments.arguments(new UserDto("jeid", "asdas", USER), PASSWORD_VALIDATE_MESSAGE)
        );
    }

    @ParameterizedTest
    @MethodSource("registerUserExceptionTestNominal")
    void registerUserExceptionTest(UserDto userDTO, String expected) throws Exception {
        Mockito.when(bindingResult.hasErrors()).thenReturn(true);

        mockMvc.perform(post(URL_TEMPLATE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(userDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(expected))
                .andExpect(header().stringValues(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8))
                .andReturn();
    }
}