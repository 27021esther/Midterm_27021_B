package com.vehicletrading.vehicle_trading.repository;

import com.vehicletrading.vehicle_trading.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
    Optional<District> findByDistrictCode(String districtCode);
    List<District> findByProvince_Id(Long provinceId);
    List<District> findByProvince_ProvinceCode(String provinceCode);
}
