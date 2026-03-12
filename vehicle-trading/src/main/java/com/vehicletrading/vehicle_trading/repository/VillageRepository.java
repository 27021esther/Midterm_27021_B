package com.vehicletrading.vehicle_trading.repository;

import com.vehicletrading.vehicle_trading.entity.Village;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VillageRepository extends JpaRepository<Village, Long> {
    Optional<Village> findByVillageCode(String villageCode);
    Optional<Village> findByVillageName(String villageName);
    List<Village> findByCell_Id(Long cellId);
}
