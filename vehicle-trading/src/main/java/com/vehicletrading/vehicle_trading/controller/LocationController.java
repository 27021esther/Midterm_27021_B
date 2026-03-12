package com.vehicletrading.vehicle_trading.controller;

import com.vehicletrading.vehicle_trading.entity.Location;
import com.vehicletrading.vehicle_trading.entity.Province;
import com.vehicletrading.vehicle_trading.service.LocationService;
import com.vehicletrading.vehicle_trading.service.ProvinceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;
    private final ProvinceService provinceService;

    /**
     * POST /api/locations?provinceId=1
     * Saves a Location linked to a Province (Many-to-One relationship).
     * The provinceId query param identifies which province this location belongs to.
     */
    @PostMapping
    public ResponseEntity<Location> create(
            @RequestBody Location location,
            @RequestParam Long provinceId) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(locationService.saveLocation(location, provinceId));
    }

    // GET /api/locations
    @GetMapping
    public ResponseEntity<List<Location>> getAll() {
        return ResponseEntity.ok(locationService.getAllLocations());
    }

    // GET /api/locations/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Location> getById(@PathVariable Long id) {
        return ResponseEntity.ok(locationService.getById(id));
    }

    // GET /api/locations/by-province/{provinceId}
    @GetMapping("/by-province/{provinceId}")
    public ResponseEntity<List<Location>> getByProvince(@PathVariable Long provinceId) {
        return ResponseEntity.ok(locationService.getByProvinceId(provinceId));
    }

    // GET /api/locations/provinces - Get all provinces
    @GetMapping("/provinces")
    public ResponseEntity<List<Province>> getAllProvinces() {
        return ResponseEntity.ok(provinceService.getAllProvinces());
    }
}
