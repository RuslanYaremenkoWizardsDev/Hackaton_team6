package bruh.controllers;

import bruh.controllers.authreg.AuthorizationController;
import bruh.entity.User;
import bruh.exceptions.InvalidCredentialsException;
import bruh.exceptions.advicecontrollers.UserAdviceController;
import bruh.model.UserDto;
import bruh.services.authreg.AuthorizationService;
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
import static bruh.util.constants.LoggerMessages.USERNAME_IS_BUSY;
import static bruh.util.constants.LoggerMessages.USER_WAS_NOT_FOUND;
import static bruh.util.constants.ValidationMessages.FIELD_CANNOT_BE_NULL;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

class AuthorizationControllerTest {

    private static final String URL_TEMPLATE = "/authorization";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON_CHARSET_UTF_8 = "application/json; charset=utf-8";
    private static final String TEXT_PLAIN_CHARSET_ISO = "text/plain;charset=ISO-8859-1";
    private final BindingResult bindingResult = Mockito.mock(BindingResult.class);
    private final AuthorizationService authorizationService = Mockito.mock(AuthorizationService.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private MockMvc mockMvc;


    @BeforeEach
    public void setUp() {
        AuthorizationController cut = new AuthorizationController(authorizationService);
        mockMvc = MockMvcBuilders
                .standaloneSetup(cut)
                .setControllerAdvice(new UserAdviceController())
                .build();
    }

    public String mapToJson(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    private static Stream<Arguments> authorizeUserTestNominal() {
        return Stream.of(
                Arguments.arguments(new UserDto("jeid", "qwerty", "user")),
                Arguments.arguments(new UserDto("ufora", "asdfgh", "user")),
                Arguments.arguments(new UserDto("trolan1", "123456", "user"))
        );
    }

    @ParameterizedTest
    @MethodSource("authorizeUserTestNominal")
    void authorizeUserNotFoundTest(UserDto user) throws Exception {
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        Mockito.doThrow(new InvalidCredentialsException(String.format(USER_WAS_NOT_FOUND, user.getLogin())))
                .when(authorizationService).authorizeUser(new User(user.getLogin(), user.getPassword(), user.getRole()));

        mockMvc.perform(post(URL_TEMPLATE)
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .content(mapToJson(user)))
                .andExpect(status().isUnauthorized())
                .andExpect(header().stringValues(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8))
                .andExpect(content().string(String.format("User %s was not found", user.getLogin())))
                .andReturn();
    }

    @ParameterizedTest
    @MethodSource("authorizeUserTestNominal")
    void authorizeUserIsAuthorizedTest(UserDto user) throws Exception {
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        Mockito.when(authorizationService.authorizeUser(new User(user.getLogin(), user.getPassword(), user.getRole()))).thenReturn(user.getLogin());

        mockMvc.perform(post(URL_TEMPLATE)
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .content(mapToJson(user)))
                .andExpect(status().isOk())
                .andExpect(header().stringValues(CONTENT_TYPE, TEXT_PLAIN_CHARSET_ISO))
                .andExpect(content().string(user.getLogin()))
                .andReturn();
    }

    @ParameterizedTest
    @MethodSource("authorizeUserTestNominal")
    void authorizeUserIncorrectCredentialsTest(UserDto user) throws Exception {
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        Mockito.doThrow(new InvalidCredentialsException(USERNAME_IS_BUSY))
                .when(authorizationService).authorizeUser(new User(user.getLogin(), user.getPassword(), user.getRole()));

        mockMvc.perform(post(URL_TEMPLATE)
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .content(mapToJson(user)))
                .andExpect(status().isUnauthorized())
                .andExpect(header().stringValues(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8))
                .andExpect(content().string(USERNAME_IS_BUSY))
                .andReturn();
    }

    static Stream<Arguments> authorizeUserExceptionTestNominal() {
        String errMsg = FIELD_CANNOT_BE_NULL;
        return Stream.of(
                Arguments.arguments(new UserDto(null, "asdasdasd", "user"), errMsg),
                Arguments.arguments(new UserDto("trolan", null, "user"), errMsg)
        );
    }

    @ParameterizedTest
    @MethodSource("authorizeUserExceptionTestNominal")
    void authorizeUserExceptionTest(UserDto userDTO, String expected) throws Exception {
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