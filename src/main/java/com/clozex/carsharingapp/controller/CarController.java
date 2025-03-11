package com.clozex.carsharingapp.controller;

import com.clozex.carsharingapp.dto.car.CarCreateRequestDto;
import com.clozex.carsharingapp.dto.car.CarDetailedResponseDto;
import com.clozex.carsharingapp.dto.car.CarResponseDto;
import com.clozex.carsharingapp.dto.car.CarUpdateRequestDto;
import com.clozex.carsharingapp.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Car controller", description = "Endpoints for managing cars")
@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @Operation(summary = "Get all cars")
    @GetMapping
    public Page<CarResponseDto> getAllCars(@ParameterObject Pageable pageable) {
        return carService.getAllCars(pageable);
    }

    @Operation(summary = "Get details of car")
    @GetMapping("/{id}")
    public CarDetailedResponseDto getCarDetails(@PathVariable Long id) {
        return carService.getCarDetails(id);
    }

    @Operation(summary = "Create a new car")
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public CarDetailedResponseDto createCar(@RequestBody
                                                @Valid CarCreateRequestDto carCreateRequestDto) {
        return carService.createCar(carCreateRequestDto);
    }

    @Operation(summary = "Update a car")
    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public CarDetailedResponseDto updateCar(@PathVariable Long id,
                                            @RequestBody
                                            @Valid CarUpdateRequestDto carUpdateRequestDto) {
        return carService.updateCar(id, carUpdateRequestDto);
    }

    @Operation(summary = "Delete a car")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }
}
