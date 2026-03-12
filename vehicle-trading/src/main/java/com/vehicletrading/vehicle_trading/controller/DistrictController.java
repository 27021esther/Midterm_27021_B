package com.vehicletrading.vehicle_trading.controller;

import com.vehicletrading.vehicle_trading.entity.District;
import com.vehicletrading.vehicle_trading.service.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/districts")
@RequiredArgsConstructor
public class DistrictController {

    private final DistrictService districtService;

    // POST /api/districts?provinceId=1
    @PostMapping
    public ResponseEntity<District> create(
            @RequestBody District district,
            @RequestParam Long provinceId) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(districtService.save(district, provinceId));
    }

    // GET /api/districts
    @GetMapping
    public ResponseEntity<List<District>> getAll() {
        return ResponseEntity.ok(districtService.getAll());
    }

    // GET /api/districts/{id}
    @GetMapping("/{id}")
    public ResponseEntity<District> getById(@PathVariable Long id) {
        return ResponseEntity.ok(districtService.getById(id));
    }

    // GET /api/districts/by-province/{provinceId}
    @GetMapping("/by-province/{provinceId}")
    public ResponseEntity<List<District>> getByProvinceId(@PathVariable Long provinceId) {
        return ResponseEntity.ok(districtService.getByProvinceId(provinceId));
    }

    // GET /api/districts/by-province-code?code=KIG
    @GetMapping("/by-province-code")
    public ResponseEntity<List<District>> getByProvinceCode(@RequestParam String code) {
        return ResponseEntity.ok(districtService.getByProvinceCode(code));
    }
}
