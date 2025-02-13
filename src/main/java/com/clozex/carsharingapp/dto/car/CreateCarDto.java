package com.clozex.carsharingapp.dto.car;

import com.clozex.carsharingapp.model.Type;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateCarDto {
    @NotBlank(message = "Model is required")
    @Size(max = 255)
    String model;

    @NotBlank(message = "Brand is required")
    @Size(max = 255)
    String brand;

    @NotBlank(message = "Type is required")
    @Size(max = 255)
    Type type;

    @NotBlank(message = "Inventory is required")
    int inventory;

    @NotBlank(message = "Daily fee is required")
    BigDecimal dailyFee;

}
