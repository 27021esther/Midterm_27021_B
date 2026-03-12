package com.vehicletrading.vehicle_trading.controller;

import com.vehicletrading.vehicle_trading.entity.Role;
import com.vehicletrading.vehicle_trading.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    /**
     * POST /api/roles
     * Create a new role.
     */
    @PostMapping
    public ResponseEntity<Role> create(@RequestBody Role role) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.saveRole(role));
    }

    /**
     * GET /api/roles
     * Get all roles.
     */
    @GetMapping
    public ResponseEntity<List<Role>> getAll() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    /**
     * GET /api/roles/{id}
     * Get a role by ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Role> getById(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.getById(id));
    }
}