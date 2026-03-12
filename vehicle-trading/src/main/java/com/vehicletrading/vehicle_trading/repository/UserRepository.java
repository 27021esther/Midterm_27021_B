package com.vehicletrading.vehicle_trading.repository;

import com.vehicletrading.vehicle_trading.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Check if a user with the given email exists.
     */
    boolean existsByEmail(String email);

    /**
     * Find all users with pagination support.
     */
    Page<User> findAll(Pageable pageable);

    /**
     * Find users by province code.
     * Uses the relationship path: user -> village -> cell -> sector -> district -> province
     */
    List<User> findByVillageCellSectorDistrictProvinceProvinceCode(String provinceCode);

    /**
     * Find users by province name.
     * Uses the relationship path: user -> village -> cell -> sector -> district -> province
     */
    List<User> findByVillageCellSectorDistrictProvinceProvinceName(String provinceName);

    /**
     * Find a user by email.
     */
    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(@Param("email") String email);
}