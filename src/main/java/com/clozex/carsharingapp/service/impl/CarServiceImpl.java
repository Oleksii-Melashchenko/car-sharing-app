package com.clozex.carsharingapp.service.impl;

import com.clozex.carsharingapp.dto.car.CarDto;
import com.clozex.carsharingapp.dto.car.CreateCarDto;
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
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Override
    public CarDto save(CreateCarDto requestDto) {
        Car car = carMapper.toModel(requestDto);
        return carMapper.toDto(carRepository.save(car));
    }

    @Override
    public Page<CarDto> findAll(Pageable pageable) {
        return carRepository.findAll(pageable)
                .map(carMapper::toDto);
    }

    @Override
    public CarDto getById(Long id) {
        Car car = carRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Car with id: " + id + " not found")
        );
        return carMapper.toDto(car);
    }

    @Override
    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public CarDto updateById(Long id, CreateCarDto requestDto) {
        Car car = carRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Car with id: " + id + " not found")
        );
        carMapper.updateCar(requestDto, car);
        return carMapper.toDto(carRepository.save(car));
    }

}
