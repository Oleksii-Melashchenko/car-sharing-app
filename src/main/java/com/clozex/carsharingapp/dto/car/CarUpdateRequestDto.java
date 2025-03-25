package com.clozex.carsharingapp.dto.car;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CarUpdateRequestDto(
        String model,
        String brand,
        String type,
        Integer inventory,
        BigDecimal dailyFee
) {

}
