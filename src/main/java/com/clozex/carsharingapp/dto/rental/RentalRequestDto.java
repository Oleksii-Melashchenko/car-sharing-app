package com.clozex.carsharingapp.dto.rental;

import java.time.LocalDateTime;

public record RentalRequestDto(
        Long carId,
        LocalDateTime returnDate
) {

}
