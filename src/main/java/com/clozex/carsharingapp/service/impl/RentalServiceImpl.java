package com.clozex.carsharingapp.service.impl;

import com.clozex.carsharingapp.dto.rental.RentalRequestDto;
import com.clozex.carsharingapp.dto.rental.RentalResponseDto;
import com.clozex.carsharingapp.mapper.RentalMapper;
import com.clozex.carsharingapp.model.Car;
import com.clozex.carsharingapp.model.Rental;
import com.clozex.carsharingapp.model.User;
import com.clozex.carsharingapp.repository.car.CarRepository;
import com.clozex.carsharingapp.repository.rental.RentalRepository;
import com.clozex.carsharingapp.repository.user.UserRepository;
import com.clozex.carsharingapp.service.RentalService;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {
    private static final String RENTAL_NOT_FOUND = "Rental not found";
    private static final String USER_NOT_FOUND = "User not found";
    private static final String CAR_NOT_FOUND = "Car not found";
    private static final String CAR_NOT_AVAILABLE = "Car not available";
    private final RentalRepository rentalRepository;
    private final RentalMapper rentalMapper;
    private final UserRepository userRepository;
    private final CarRepository carRepository;

    @Override
    @Transactional
    public RentalResponseDto createRental(RentalRequestDto rentalRequestDto,
                                          User user) {
        User userDto = userRepository.findByEmail(user.getEmail()).orElseThrow(
                () -> new EntityNotFoundException(USER_NOT_FOUND)
        );
        Car car = carRepository.findById(rentalRequestDto.carId()).orElseThrow(
                () -> new EntityNotFoundException(CAR_NOT_FOUND)
        );
        if (car.getInventory() == 0) {
            throw new EntityNotFoundException(CAR_NOT_AVAILABLE);
        }
        car.setInventory(car.getInventory() - 1);
        Rental rental = rentalMapper.toEntity(rentalRequestDto, userDto, car);
        return rentalMapper.toDto(rentalRepository.save(rental));
    }

    @Override
    public Page<RentalResponseDto> getRentals(Long userId,
                                              User user,
                                              Pageable pageable,
                                              Boolean isActive) {
        boolean isAdmin = "ROLE_ADMIN".equals(user.getRoles().getName().toString());
        Long finalUserId = isAdmin ? userId : user.getId();
        Page<Rental> rentals = rentalRepository.findAllWithFilters(finalUserId, isActive, pageable);
        return rentals.map(rentalMapper::toDto);
    }

    @Override
    public RentalResponseDto getRentalById(Long id) {
        return rentalMapper.toDto(rentalRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(RENTAL_NOT_FOUND)
        ));
    }

    @Override
    @Transactional
    public RentalResponseDto returnCar(Long id) {
        Rental rental = rentalRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(RENTAL_NOT_FOUND)
        );
        if (rental.getActualReturnDate() != null) {
            throw new IllegalStateException("Car already returned");
        }
        Car car = rental.getCar();
        car.setInventory(car.getInventory() + 1);
        rental.setActualReturnDate(LocalDateTime.now());
        return rentalMapper.toDto(rentalRepository.save(rental));
    }

}
