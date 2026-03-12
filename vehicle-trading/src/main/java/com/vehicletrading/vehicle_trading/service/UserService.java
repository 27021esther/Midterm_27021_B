package com.vehicletrading.vehicle_trading.service;

import com.vehicletrading.vehicle_trading.entity.Role;
import com.vehicletrading.vehicle_trading.entity.User;
import com.vehicletrading.vehicle_trading.entity.UserProfile;
import com.vehicletrading.vehicle_trading.entity.Village;
import com.vehicletrading.vehicle_trading.repository.RoleRepository;
import com.vehicletrading.vehicle_trading.repository.UserRepository;
import com.vehicletrading.vehicle_trading.repository.VillageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final VillageRepository villageRepository;
    private final RoleRepository roleRepository;

    /**
     * Save a user with village (by villageCode) and roles (by roleNames).
     * The user also has an optional profile.
     */
    @Transactional
    public User saveUser(User user, String villageCode, List<String> roleNames, UserProfile profile) {
        // Check if email already exists
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException(
                "User with email '" + user.getEmail() + "' already exists.");
        }

        // Find village by code
        Village village = villageRepository.findByVillageCode(villageCode)
            .orElseThrow(() -> new RuntimeException("Village not found with code: " + villageCode));
        user.setVillage(village);

        // Set profile if provided
        if (profile != null) {
            profile.setUser(user);
            user.setUserProfile(profile);
        }

        // Set roles if provided
        if (roleNames != null && !roleNames.isEmpty()) {
            Set<Role> roles = new HashSet<>();
            for (String roleName : roleNames) {
                Role role = roleRepository.findByRoleName(roleName)
                    .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
                roles.add(role);
            }
            user.setRoles(roles);
        }

        return userRepository.save(user);
    }

    /**
     * Get all users with pagination.
     */
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    /**
     * Get a user by ID.
     */
    public User getById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    /**
     * Get users by province code.
     */
    public List<User> getUsersByProvinceCode(String provinceCode) {
        return userRepository.findByVillageCellSectorDistrictProvinceProvinceCode(provinceCode);
    }

    /**
     * Get users by province name.
     */
    public List<User> getUsersByProvinceName(String provinceName) {
        return userRepository.findByVillageCellSectorDistrictProvinceProvinceName(provinceName);
    }

    /**
     * Check if a user exists by email.
     */
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    /**
     * Find a user by email.
     */
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}