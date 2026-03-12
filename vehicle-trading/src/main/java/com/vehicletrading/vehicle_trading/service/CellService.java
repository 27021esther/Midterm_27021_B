package com.vehicletrading.vehicle_trading.service;

import com.vehicletrading.vehicle_trading.entity.Cell;
import com.vehicletrading.vehicle_trading.entity.Sector;
import com.vehicletrading.vehicle_trading.repository.CellRepository;
import com.vehicletrading.vehicle_trading.repository.SectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CellService {

    private final CellRepository cellRepository;
    private final SectorRepository sectorRepository;

    public Cell save(Cell cell, Long sectorId) {
        Sector sector = sectorRepository.findById(sectorId)
            .orElseThrow(() -> new RuntimeException("Sector not found: " + sectorId));
        cell.setSector(sector);
        return cellRepository.save(cell);
    }

    public List<Cell> getAll() {
        return cellRepository.findAll();
    }

    public Cell getById(Long id) {
        return cellRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cell not found: " + id));
    }

    public List<Cell> getBySectorId(Long sectorId) {
        return cellRepository.findBySector_Id(sectorId);
    }
}
