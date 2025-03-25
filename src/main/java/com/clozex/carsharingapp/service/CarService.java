package com.clozex.carsharingapp.service;

import com.clozex.carsharingapp.dto.car.CarCreateRequestDto;
import com.clozex.carsharingapp.dto.car.CarDetailedResponseDto;
import com.clozex.carsharingapp.dto.car.CarResponseDto;
import com.clozex.carsharingapp.dto.car.CarUpdateRequestDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CarService {
    Page<CarResponseDto> getAllCars(Pageable pageable);

    CarDetailedResponseDto getCarDetails(Long id);

    CarDetailedResponseDto createCar(CarCreateRequestDto carCreateRequestDto);

    CarDetailedResponseDto updateCar(Long id, @Valid CarUpdateRequestDto carUpdateRequestDto);

    void deleteCar(Long id);

}
