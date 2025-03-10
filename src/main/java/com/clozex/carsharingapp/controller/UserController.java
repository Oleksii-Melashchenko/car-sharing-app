package com.clozex.carsharingapp.controller;

import com.clozex.carsharingapp.dto.user.UserResponseDto;
import com.clozex.carsharingapp.dto.user.UserUpdateDetailsDto;
import com.clozex.carsharingapp.model.User;
import com.clozex.carsharingapp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User controller", description = "Endpoints for managing users")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "Getting information about the current user")
    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto getUserInfo(Authentication authentication) {
        return userService.getUserInfo((User) authentication.getPrincipal());
    }

    @Operation(summary = "Updating information about the current user")
    @PatchMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto updateUserInfo(Authentication authentication,
                                          @Valid
                                          @RequestBody UserUpdateDetailsDto userUpdateDetailsDto) {
        return userService.updateUserInfo((User) authentication.getPrincipal(),
                userUpdateDetailsDto);
    }

    @Operation(summary = "Updating user role")
    @PutMapping("/{id}/role")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public UserResponseDto updateUserRole(@PathVariable Long id,
                                          @Valid @RequestBody Long roleId) {
        return userService.updateUserRole(id, roleId);
    }
}
