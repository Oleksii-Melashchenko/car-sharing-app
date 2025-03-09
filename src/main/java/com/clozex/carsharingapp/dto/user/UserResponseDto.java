package com.clozex.carsharingapp.dto.user;

import java.util.Set;

public record UserResponseDto(
        String email,
        String firstName,
        String lastName,
        Set<String> roles
) {
}
