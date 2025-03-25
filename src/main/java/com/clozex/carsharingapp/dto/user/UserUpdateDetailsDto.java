package com.clozex.carsharingapp.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserUpdateDetailsDto(
        String firstName,
        String lastName
) {
}
