package com.clozex.carsharingapp.mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

import com.clozex.carsharingapp.configuration.MapperConfig;
import com.clozex.carsharingapp.dto.rental.RentalRequestDto;
import com.clozex.carsharingapp.dto.rental.RentalResponseDto;
import com.clozex.carsharingapp.model.Car;
import com.clozex.carsharingapp.model.Rental;
import com.clozex.carsharingapp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, unmappedTargetPolicy = IGNORE)

public interface RentalMapper {
    @Mapping(target = "carModel", source = "car.model")
    @Mapping(target = "carBrand", source = "car.brand")
    @Mapping(target = "dailyFee", source = "car.dailyFee")
    @Mapping(target = "userFirstName", source = "user.firstName")
    @Mapping(target = "userLastName", source = "user.lastName")
    RentalResponseDto toDto(Rental rental);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "user", source = "user")
    @Mapping(target = "car", source = "car")
    Rental toEntity(RentalRequestDto rentalRequestDto, User user, Car car);
}
