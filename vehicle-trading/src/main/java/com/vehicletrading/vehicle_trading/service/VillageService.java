package com.vehicletrading.vehicle_trading.service;

import com.vehicletrading.vehicle_trading.entity.Cell;
import com.vehicletrading.vehicle_trading.entity.Village;
import com.vehicletrading.vehicle_trading.repository.CellRepository;
import com.vehicletrading.vehicle_trading.repository.VillageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VillageService {

    private final VillageRepository villageRepository;
    private final CellRepository cellRepository;

    public Village save(Village village, Long cellId) {
        Cell cell = cellRepository.findById(cellId)
            .orElseThrow(() -> new RuntimeException("Cell not found: " + cellId));
        village.setCell(cell);
        return villageRepository.save(village);
    }

    public List<Village> getAll() {
        return villageRepository.findAll();
    }

    public Village getById(Long id) {
        return villageRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Village not found: " + id));
    }

    public Village getByCode(String villageCode) {
        return villageRepository.findByVillageCode(villageCode)
            .orElseThrow(() -> new RuntimeException("Village not found with code: " + villageCode));
    }

    public List<Village> getByCellId(Long cellId) {
        return villageRepository.findByCell_Id(cellId);
    }
}
