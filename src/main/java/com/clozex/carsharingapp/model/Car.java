package com.clozex.carsharingapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@SQLDelete(sql = "UPDATE cars SET isDeleted = true WHERE id = ?")
@SQLRestriction(value = "is_deleted = FALSE")
@Entity
@Getter
@Setter
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String model;

    @Column(nullable = false)
    String brand;

    @Enumerated(EnumType.STRING)
    CarType type;

    int inventory;

    @Column(nullable = false)
    BigDecimal dailyFee;

    @Column(nullable = false)
    boolean isDeleted = false;

    public enum CarType {
        SEDAN, SUV, HATCHBACK, UNIVERSAL
    }
}

