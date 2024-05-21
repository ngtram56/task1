package com.example.task.repository;

import com.example.task.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query("SELECT v FROM Vehicle v " +
            "WHERE (:brandId IS NULL OR v.brand.id = :brandId) " +
            "AND (:year IS NULL OR v.year = :year) " +
            "AND (:price IS NULL OR v.price = :price) " +
            "AND (:owner IS NULL OR v.owner LIKE %:owner%)")
    List<Vehicle> searchVehicles(Long brandId, Integer year, BigDecimal price, String owner);

    @Query("SELECT v FROM Vehicle v " +
            "JOIN v.brand b " +
            "WHERE (v.vehicleName LIKE 'S%' AND v.price > 10000000) " +
            "OR (v.vehicleName LIKE 'S%' AND b.type = 'bus' AND v.price <= 10000000)")
    List<Vehicle> findVehiclesWithConditions();
}
