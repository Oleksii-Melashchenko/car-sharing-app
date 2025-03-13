package com.clozex.carsharingapp.repository.rental;

import com.clozex.carsharingapp.model.Rental;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    @EntityGraph(value = "Rental.cars.users", type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Rental r WHERE (r.user.id = :userId) "
            + "AND (:isActive IS NULL OR "
            + "(:isActive = true AND r.actualReturnDate IS NULL) OR "
            + "(:isActive = false AND r.actualReturnDate IS NOT NULL))")
    Page<Rental> findAllWithFilters(@Param("userId") Long userId,
                                    @Param("isActive") Boolean isActive,
                                    Pageable pageable);

    @NotNull
    @EntityGraph(value = "Rental.cars.users", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Rental> findById(@NotNull Long id);
}
