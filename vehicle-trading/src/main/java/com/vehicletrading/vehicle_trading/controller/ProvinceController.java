package com.vehicletrading.vehicle_trading.controller;

import com.vehicletrading.vehicle_trading.entity.Province;
import com.vehicletrading.vehicle_trading.service.ProvinceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/provinces")
@RequiredArgsConstructor
public class ProvinceController {

    private final ProvinceService provinceService;

    // POST /api/provinces
    @PostMapping
    public ResponseEntity<Province> create(@RequestBody Province province) {
        return ResponseEntity.status(HttpStatus.CREATED).body(provinceService.saveProvince(province));
    }

    // GET /api/provinces
    @GetMapping
    public ResponseEntity<List<Province>> getAll() {
        return ResponseEntity.ok(provinceService.getAllProvinces());
    }

    // GET /api/provinces/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Province> getById(@PathVariable Long id) {
        return ResponseEntity.ok(provinceService.getById(id));
    }
}
