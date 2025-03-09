package com.clozex.carsharingapp.service;

import com.clozex.carsharingapp.dto.user.UserRegisterResponseDto;
import com.clozex.carsharingapp.dto.user.UserRegistrationRequestDto;
import com.clozex.carsharingapp.dto.user.UserResponseDto;
import com.clozex.carsharingapp.exception.RegistrationException;
import com.clozex.carsharingapp.model.User;
import jakarta.validation.Valid;

public interface UserService {
    UserRegisterResponseDto register(@Valid UserRegistrationRequestDto requestDto)
            throws RegistrationException;

    UserResponseDto getUserInfo(User user);

}
