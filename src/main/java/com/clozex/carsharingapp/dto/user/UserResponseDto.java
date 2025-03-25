package com.clozex.carsharingapp.dto.user;

public record UserResponseDto(
        String email,
        String firstName,
        String lastName,
        String role
) {
}
