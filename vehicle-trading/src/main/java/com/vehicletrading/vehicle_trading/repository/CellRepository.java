package com.vehicletrading.vehicle_trading.repository;

import com.vehicletrading.vehicle_trading.entity.Cell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CellRepository extends JpaRepository<Cell, Long> {
    Optional<Cell> findByCellCode(String cellCode);
    List<Cell> findBySector_Id(Long sectorId);
}
