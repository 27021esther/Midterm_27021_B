package com.vehicletrading.vehicle_trading.repository;

import com.vehicletrading.vehicle_trading.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {

    /** Find by code, e.g., "KIG" */
    Optional<Province> findByProvinceCode(String provinceCode);

    /** Find by full name, e.g., "Kigali City" */
    Optional<Province> findByProvinceNameIgnoreCase(String provinceName);

    /** existsBy example: check if a province code already exists */
    boolean existsByProvinceCode(String provinceCode);
}
