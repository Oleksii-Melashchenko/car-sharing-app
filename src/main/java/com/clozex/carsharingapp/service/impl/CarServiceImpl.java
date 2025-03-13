package com.clozex.carsharingapp.service.impl;

import com.clozex.carsharingapp.dto.car.CarCreateRequestDto;
import com.clozex.carsharingapp.dto.car.CarDetailedResponseDto;
import com.clozex.carsharingapp.dto.car.CarResponseDto;
import com.clozex.carsharingapp.dto.car.CarUpdateRequestDto;
import com.clozex.carsharingapp.mapper.CarMapper;
import com.clozex.carsharingapp.model.Car;
import com.clozex.carsharingapp.repository.car.CarRepository;
import com.clozex.carsharingapp.service.CarService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private static final String CAR_NOT_FOUND = "Car not found";
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Override
    public Page<CarResponseDto> getAllCars(Pageable pageable) {
        return carRepository.findAll(pageable)
                .map(carMapper::toDto);
    }

    @Override
    public CarDetailedResponseDto getCarDetails(Long id) {
        return carMapper.toDetailedDto(carRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(CAR_NOT_FOUND)
        ));
    }

    @Override
    public CarDetailedResponseDto createCar(CarCreateRequestDto carCreateRequestDto) {
        Car car = carMapper.toEntity(carCreateRequestDto);
        return carMapper.toDetailedDto(carRepository.save(car));
    }

    @Override
    public CarDetailedResponseDto updateCar(Long id, CarUpdateRequestDto carUpdateRequestDto) {
        Car car = carRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(CAR_NOT_FOUND)
        );
        carMapper.updateCarFromDto(carUpdateRequestDto, car);
        return carMapper.toDetailedDto(carRepository.save(car));
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

}
