package com.vehicletrading.vehicle_trading.repository;

import com.vehicletrading.vehicle_trading.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    /** Retrieve all locations in a province by its ID */
    List<Location> findByProvinceId(Long provinceId);

    /** Check whether a location with a given district and sector already exists */
    boolean existsByDistrictAndSector(String district, String sector);
}
