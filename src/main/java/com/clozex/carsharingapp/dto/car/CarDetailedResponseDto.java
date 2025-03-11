package com.clozex.carsharingapp.dto.car;

import java.math.BigDecimal;

public record CarDetailedResponseDto(
        String model,
        String brand,
        String type,
        int inventory,
        BigDecimal dailyFee
) {
}
