package com.clozex.carsharingapp.dto.rental;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record RentalResponseDto(
        String carModel,
        String carBrand,
        BigDecimal dailyFee,
        String userFirstName,
        String userLastName,
        LocalDateTime actualReturnDate,
        LocalDateTime rentalDate,
        LocalDateTime returnDate
) {
}
