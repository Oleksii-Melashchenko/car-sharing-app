package com.clozex.carsharingapp.service;

import com.clozex.carsharingapp.dto.car.CarDto;
import com.clozex.carsharingapp.dto.car.CreateCarDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CarService {
    CarDto save(CreateCarDto requestDto);

    Page<CarDto> findAll(Pageable pageable);

    CarDto getById(Long id);

    void deleteById(Long id);

    CarDto updateById(Long id, CreateCarDto requestDto);
}
