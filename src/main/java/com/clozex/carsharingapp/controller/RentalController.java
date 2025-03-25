package com.clozex.carsharingapp.controller;

import com.clozex.carsharingapp.dto.rental.RentalRequestDto;
import com.clozex.carsharingapp.dto.rental.RentalResponseDto;
import com.clozex.carsharingapp.model.User;
import com.clozex.carsharingapp.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Rental Controller", description = "Controller for managing rentals")
@RestController
@RequestMapping("/rentals")
@RequiredArgsConstructor
public class RentalController {
    private final RentalService rentalService;

    @Operation(summary = "Create a new rental")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public RentalResponseDto createRental(@RequestBody @Valid RentalRequestDto rentalRequestDto,
                                          Authentication authentication) {
        return rentalService.createRental(rentalRequestDto, (User) authentication.getPrincipal());
    }

    @Operation(summary = "Get all rentals with filters")
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public Page<RentalResponseDto> getRental(@RequestParam(required = false) Boolean isActive,
                                             Authentication authentication,
                                             @ParameterObject Pageable pageable,
                                             @RequestParam(required = false) Long userId) {
        return rentalService.getRentals(userId,(User) authentication.getPrincipal(),
                pageable, isActive);
    }

    @Operation(summary = "Get rental by id")
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public RentalResponseDto getRentalById(@PathVariable Long id) {
        return rentalService.getRentalById(id);
    }

    @Operation(summary = "Setting actual return date")
    @PostMapping("/{id}/return")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public RentalResponseDto returnCar(@PathVariable Long id) {
        return rentalService.returnCar(id);
    }
}
