package com.clozex.carsharingapp.mapper;

import com.clozex.carsharingapp.configuration.MapperConfig;
import com.clozex.carsharingapp.dto.car.CarDto;
import com.clozex.carsharingapp.dto.car.CreateCarDto;
import com.clozex.carsharingapp.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(config = MapperConfig.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarMapper {
    Car toModel(CreateCarDto requestDto);

    CarDto toDto(Car car);

    void updateCar(CreateCarDto requestDto, @MappingTarget Car car);
}
