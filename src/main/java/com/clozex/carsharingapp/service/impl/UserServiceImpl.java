package com.clozex.carsharingapp.service.impl;

import com.clozex.carsharingapp.dto.user.UserRegisterResponseDto;
import com.clozex.carsharingapp.dto.user.UserRegistrationRequestDto;
import com.clozex.carsharingapp.dto.user.UserResponseDto;
import com.clozex.carsharingapp.dto.user.UserUpdateDetailsDto;
import com.clozex.carsharingapp.exception.RegistrationException;
import com.clozex.carsharingapp.mapper.UserMapper;
import com.clozex.carsharingapp.model.Role;
import com.clozex.carsharingapp.model.User;
import com.clozex.carsharingapp.repository.user.RoleRepository;
import com.clozex.carsharingapp.repository.user.UserRepository;
import com.clozex.carsharingapp.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private static final String USER_NOT_FOUND = "User not found";
    private static final String ROLE_NOT_FOUND = "Role not found";
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserRegisterResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        String email = requestDto.email();
        if (userRepository.existsByEmail(email)) {
            throw new RegistrationException("User with email: " + email + " already exists");
        }
        User user = userMapper.toModel(requestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findByName(Role.RoleName.ROLE_USER).orElseThrow(
                () -> new EntityNotFoundException(ROLE_NOT_FOUND)
        ));
        return userMapper.toRegisterDto(userRepository.save(user));
    }

    @Override
    public UserResponseDto getUserInfo(User user) {
        User userDto = userRepository.findByEmail(user.getEmail()).orElseThrow(
                () -> new EntityNotFoundException(USER_NOT_FOUND)
        );
        return userMapper.toDto(userDto);
    }

    @Override
    public UserResponseDto updateUserInfo(User user, UserUpdateDetailsDto userUpdateDetailsDto) {
        User userDto = userRepository.findByEmail(user.getEmail()).orElseThrow(
                () -> new EntityNotFoundException(USER_NOT_FOUND)
        );
        userMapper.updateUserFromDto(userUpdateDetailsDto,userDto);
        return userMapper.toDto(userRepository.save(userDto));
    }

    @Override
    public UserResponseDto updateUserRole(Long userId, Long roleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND));
        Role newRole = roleRepository.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException(ROLE_NOT_FOUND));
        user.setRoles(newRole);
        return userMapper.toDto(userRepository.save(user));
    }

}
