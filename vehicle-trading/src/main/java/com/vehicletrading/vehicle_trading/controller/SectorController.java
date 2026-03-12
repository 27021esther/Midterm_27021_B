package com.vehicletrading.vehicle_trading.controller;

import com.vehicletrading.vehicle_trading.entity.Sector;
import com.vehicletrading.vehicle_trading.service.SectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sectors")
@RequiredArgsConstructor
public class SectorController {

    private final SectorService sectorService;

    // POST /api/sectors?districtId=1
    @PostMapping
    public ResponseEntity<Sector> create(
            @RequestBody Sector sector,
            @RequestParam Long districtId) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(sectorService.save(sector, districtId));
    }

    // GET /api/sectors
    @GetMapping
    public ResponseEntity<List<Sector>> getAll() {
        return ResponseEntity.ok(sectorService.getAll());
    }

    // GET /api/sectors/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Sector> getById(@PathVariable Long id) {
        return ResponseEntity.ok(sectorService.getById(id));
    }

    // GET /api/sectors/by-district/{districtId}
    @GetMapping("/by-district/{districtId}")
    public ResponseEntity<List<Sector>> getByDistrictId(@PathVariable Long districtId) {
        return ResponseEntity.ok(sectorService.getByDistrictId(districtId));
    }
}
