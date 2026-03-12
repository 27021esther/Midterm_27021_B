package com.vehicletrading.vehicle_trading.service;

import com.vehicletrading.vehicle_trading.entity.District;
import com.vehicletrading.vehicle_trading.entity.Sector;
import com.vehicletrading.vehicle_trading.repository.DistrictRepository;
import com.vehicletrading.vehicle_trading.repository.SectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SectorService {

    private final SectorRepository sectorRepository;
    private final DistrictRepository districtRepository;

    public Sector save(Sector sector, Long districtId) {
        District district = districtRepository.findById(districtId)
            .orElseThrow(() -> new RuntimeException("District not found: " + districtId));
        sector.setDistrict(district);
        return sectorRepository.save(sector);
    }

    public List<Sector> getAll() {
        return sectorRepository.findAll();
    }

    public Sector getById(Long id) {
        return sectorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Sector not found: " + id));
    }

    public List<Sector> getByDistrictId(Long districtId) {
        return sectorRepository.findByDistrict_Id(districtId);
    }
}
