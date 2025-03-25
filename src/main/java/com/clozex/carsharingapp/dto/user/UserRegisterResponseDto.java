package com.clozex.carsharingapp.dto.user;

public record UserRegisterResponseDto(
        Long id,
        String email,
        String firstName,
        String lastName
) {
}
