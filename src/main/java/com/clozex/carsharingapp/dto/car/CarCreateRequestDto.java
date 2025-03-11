package com.clozex.carsharingapp.dto.car;

import io.swagger.v3.oas.annotations.Parameter;
import java.math.BigDecimal;

public record CarCreateRequestDto(
        String model,
        String brand,
        @Parameter(description = "Car type", example = "SEDAN")
        String type,
        int inventory,
        BigDecimal dailyFee
) {
}
