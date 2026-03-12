package com.vehicletrading.vehicle_trading.service;

import com.vehicletrading.vehicle_trading.entity.Role;
import com.vehicletrading.vehicle_trading.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    /**
     * Save a role after checking if it already exists by roleName.
     */
    public Role saveRole(Role role) {
        if (roleRepository.existsByRoleName(role.getRoleName())) {
            throw new IllegalArgumentException(
                "Role with name '" + role.getRoleName() + "' already exists.");
        }
        return roleRepository.save(role);
    }

    /**
     * Get all roles.
     */
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    /**
     * Get a role by ID.
     */
    public Role getById(Long id) {
        return roleRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
    }

    /**
     * Find a role by roleName.
     */
    public Role findByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName)
            .orElseThrow(() -> new RuntimeException("Role not found with name: " + roleName));
    }
}