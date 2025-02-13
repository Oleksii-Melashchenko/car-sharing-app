package com.clozex.carsharingapp.dto.car;

import com.clozex.carsharingapp.model.Type;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarDto {
    Long id;

    String model;

    String brand;

    Type type;

    int inventory;

    BigDecimal dailyFee;
}
