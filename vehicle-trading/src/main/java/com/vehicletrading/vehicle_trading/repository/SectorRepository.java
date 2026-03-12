package com.vehicletrading.vehicle_trading.repository;

import com.vehicletrading.vehicle_trading.entity.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long> {
    Optional<Sector> findBySectorCode(String sectorCode);
    List<Sector> findByDistrict_Id(Long districtId);
}
