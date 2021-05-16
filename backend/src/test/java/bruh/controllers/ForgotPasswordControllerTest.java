package bruh.controllers;

import bruh.entity.User;
import bruh.exceptions.InvalidCredentialsException;
import bruh.exceptions.advicecontrollers.UserAdviceController;
import bruh.model.UserDto;
import bruh.services.AuthorizationService;
import bruh.services.ForgotPasswordService;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

class ForgotPasswordControllerTest {


}