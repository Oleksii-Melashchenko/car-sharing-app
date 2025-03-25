package com.clozex.carsharingapp.service;

import com.clozex.carsharingapp.dto.user.UserRegisterResponseDto;
import com.clozex.carsharingapp.dto.user.UserRegistrationRequestDto;
import com.clozex.carsharingapp.dto.user.UserResponseDto;
import com.clozex.carsharingapp.dto.user.UserUpdateDetailsDto;
import com.clozex.carsharingapp.exception.RegistrationException;
import com.clozex.carsharingapp.model.User;

public interface UserService {
    UserRegisterResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException;

    UserResponseDto getUserInfo(User user);

    UserResponseDto updateUserInfo(User user,UserUpdateDetailsDto userUpdateDetailsDto);

    UserResponseDto updateUserRole(Long userId, Long roleId);

}
