package com.clozex.carsharingapp.dto.car;

import java.math.BigDecimal;

public record CarResponseDto(
        String model,
        String brand,
        String type,
        BigDecimal dailyFee
) {

}
