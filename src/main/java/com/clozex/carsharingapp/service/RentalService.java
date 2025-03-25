package com.clozex.carsharingapp.service;

import com.clozex.carsharingapp.dto.rental.RentalRequestDto;
import com.clozex.carsharingapp.dto.rental.RentalResponseDto;
import com.clozex.carsharingapp.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RentalService {
    RentalResponseDto createRental(RentalRequestDto rentalRequestDto,
                                   User user);

    Page<RentalResponseDto> getRentals(Long userId, User user, Pageable pageable, Boolean isActive);

    RentalResponseDto getRentalById(Long id);

    RentalResponseDto returnCar(Long id);

}
