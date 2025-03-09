package com.clozex.carsharingapp.controller;

import com.clozex.carsharingapp.dto.user.UserLoginRequestDto;
import com.clozex.carsharingapp.dto.user.UserLoginResponseDto;
import com.clozex.carsharingapp.dto.user.UserRegisterResponseDto;
import com.clozex.carsharingapp.dto.user.UserRegistrationRequestDto;
import com.clozex.carsharingapp.exception.RegistrationException;
import com.clozex.carsharingapp.security.AuthenticationService;
import com.clozex.carsharingapp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "AUTH controller", description = "Endpoints for authentication")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @Operation(summary = "Register a new user")
    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public UserRegisterResponseDto register(@RequestBody
                                                @Valid UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        return userService.register(requestDto);
    }

    @Operation(summary = "Login a user")
    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto request) {
        return authenticationService.authenticate(request);
    }
}
