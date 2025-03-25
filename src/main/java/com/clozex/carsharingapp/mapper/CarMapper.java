package com.clozex.carsharingapp.mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

import com.clozex.carsharingapp.configuration.MapperConfig;
import com.clozex.carsharingapp.dto.car.CarCreateRequestDto;
import com.clozex.carsharingapp.dto.car.CarDetailedResponseDto;
import com.clozex.carsharingapp.dto.car.CarResponseDto;
import com.clozex.carsharingapp.dto.car.CarUpdateRequestDto;
import com.clozex.carsharingapp.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(config = MapperConfig.class, unmappedTargetPolicy = IGNORE)
public interface CarMapper {
    CarResponseDto toDto(Car car);

    CarDetailedResponseDto toDetailedDto(Car car);

    Car toEntity(CarCreateRequestDto carCreateRequestDto);

    @Mapping(target = "model",
            source = "updateDetailsDto.model",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "brand",
            source = "updateDetailsDto.brand",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "type",
            expression = "java(updateDetailsDto.type() != null ? Car.CarType"
                    + ".valueOf(updateDetailsDto.type()) : existingCar.getType())")
    @Mapping(target = "inventory",
            source = "updateDetailsDto.inventory",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "dailyFee",
            source = "updateDetailsDto.dailyFee",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCarFromDto(CarUpdateRequestDto updateDetailsDto, @MappingTarget Car existingCar);
}
