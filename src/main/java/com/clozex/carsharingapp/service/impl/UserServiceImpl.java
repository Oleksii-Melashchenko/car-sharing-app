package com.clozex.carsharingapp.service.impl;

import com.clozex.carsharingapp.dto.user.UserRegisterResponseDto;
import com.clozex.carsharingapp.dto.user.UserRegistrationRequestDto;
import com.clozex.carsharingapp.dto.user.UserResponseDto;
import com.clozex.carsharingapp.exception.RegistrationException;
import com.clozex.carsharingapp.mapper.UserMapper;
import com.clozex.carsharingapp.model.Role;
import com.clozex.carsharingapp.model.User;
import com.clozex.carsharingapp.repository.user.UserRepository;
import com.clozex.carsharingapp.service.RoleService;
import com.clozex.carsharingapp.service.UserService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleService roleService;
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
        user.setRoles(Set.of(roleService.findByName(Role.RoleName.ROLE_USER)));
        return userMapper.toRegisterDto(userRepository.save(user));
    }

    @Override
    public UserResponseDto getUserInfo(User user) {
        User userDto = userRepository.findByEmail(user.getEmail()).orElseThrow();
        return userMapper.toDto(userDto);
    }

}
