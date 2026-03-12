package com.vehicletrading.vehicle_trading.controller;

import com.vehicletrading.vehicle_trading.entity.Village;
import com.vehicletrading.vehicle_trading.service.VillageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/villages")
@RequiredArgsConstructor
public class VillageController {

    private final VillageService villageService;

    // POST /api/villages?cellId=1
    @PostMapping
    public ResponseEntity<Village> create(
            @RequestBody Village village,
            @RequestParam Long cellId) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(villageService.save(village, cellId));
    }

    // GET /api/villages
    @GetMapping
    public ResponseEntity<List<Village>> getAll() {
        return ResponseEntity.ok(villageService.getAll());
    }

    // GET /api/villages/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Village> getById(@PathVariable Long id) {
        return ResponseEntity.ok(villageService.getById(id));
    }

    // GET /api/villages/by-code?code=VIL001
    @GetMapping("/by-code")
    public ResponseEntity<Village> getByCode(@RequestParam String code) {
        return ResponseEntity.ok(villageService.getByCode(code));
    }

    // GET /api/villages/by-cell/{cellId}
    @GetMapping("/by-cell/{cellId}")
    public ResponseEntity<List<Village>> getByCellId(@PathVariable Long cellId) {
        return ResponseEntity.ok(villageService.getByCellId(cellId));
    }
}
