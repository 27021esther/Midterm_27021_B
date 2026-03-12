package com.vehicletrading.vehicle_trading.service;

import com.vehicletrading.vehicle_trading.entity.Location;
import com.vehicletrading.vehicle_trading.entity.Province;
import com.vehicletrading.vehicle_trading.repository.LocationRepository;
import com.vehicletrading.vehicle_trading.repository.ProvinceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;
    private final ProvinceRepository provinceRepository;

    /**
     * Save a Location with its Province relationship.
     * Logic:
     *  1. Look up the Province by ID (Province must exist first).
     *  2. Attach the Province to the Location object.
     *  3. Persist the Location – Hibernate inserts a row in "locations"
     *     with "province_id" FK pointing to the relevant province row.
     */
    public Location saveLocation(Location location, Long provinceId) {
        Province province = provinceRepository.findById(provinceId)
            .orElseThrow(() -> new RuntimeException("Province not found: " + provinceId));
        location.setProvince(province);
        return locationRepository.save(location);
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Location getById(Long id) {
        return locationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Location not found: " + id));
    }

    public List<Location> getByProvinceId(Long provinceId) {
        return locationRepository.findByProvinceId(provinceId);
    }
}
