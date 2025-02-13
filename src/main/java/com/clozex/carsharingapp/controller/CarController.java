package com.clozex.carsharingapp.controller;

import com.clozex.carsharingapp.dto.car.CarDto;
import com.clozex.carsharingapp.dto.car.CreateCarDto;
import com.clozex.carsharingapp.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;

    @GetMapping
    public Page<CarDto> getCars(Pageable pageable) {
        return carService.findAll(pageable);
    }

    @GetMapping("/cars/{id}")
    public CarDto getCarById(@PathVariable Long id) {
        return carService.getById(id);
    }

    @PostMapping
    public CarDto saveCar(@RequestBody CreateCarDto requestDto) {
        return carService.save(requestDto);
    }

    @DeleteMapping("/cars/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteById(id);
    }

    @PutMapping("/cars/{id}")
    public CarDto updateCar(@PathVariable Long id, @RequestBody CreateCarDto requestDto) {
        return carService.updateById(id, requestDto);
    }
}

