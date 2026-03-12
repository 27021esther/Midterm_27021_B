package com.vehicletrading.vehicle_trading.repository;

import com.vehicletrading.vehicle_trading.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /** Prevent duplicate email registrations */
    boolean existsByEmail(String email);

    /**
     * Retrieve customers by province CODE.
     * Navigation: customer → village → cell → sector → district → province → provinceCode
     */
    List<Customer> findByVillage_Cell_Sector_District_Province_ProvinceCode(String provinceCode);

    /**
     * Retrieve customers by province NAME (case-insensitive).
     * Navigation: customer → village → cell → sector → district → province → provinceName
     */
    @Query("SELECT c FROM Customer c " +
           "JOIN c.village v " +
           "JOIN v.cell cl " +
           "JOIN cl.sector s " +
           "JOIN s.district d " +
           "JOIN d.province p " +
           "WHERE LOWER(p.provinceName) = LOWER(:provinceName)")
    List<Customer> findByProvinceName(@Param("provinceName") String provinceName);

    /**
     * Retrieve customers by district NAME (case-insensitive).
     */
    @Query("SELECT c FROM Customer c " +
           "JOIN c.village v " +
           "JOIN v.cell cl " +
           "JOIN cl.sector s " +
           "JOIN s.district d " +
           "WHERE LOWER(d.districtName) = LOWER(:districtName)")
    List<Customer> findByDistrictName(@Param("districtName") String districtName);

    /**
     * Retrieve customers by sector NAME (case-insensitive).
     */
    @Query("SELECT c FROM Customer c " +
           "JOIN c.village v " +
           "JOIN v.cell cl " +
           "JOIN cl.sector s " +
           "WHERE LOWER(s.sectorName) = LOWER(:sectorName)")
    List<Customer> findBySectorName(@Param("sectorName") String sectorName);

    /**
     * Retrieve customers by village NAME (case-insensitive).
     */
    @Query("SELECT c FROM Customer c " +
           "JOIN c.village v " +
           "WHERE LOWER(v.villageName) = LOWER(:villageName)")
    List<Customer> findByVillageName(@Param("villageName") String villageName);
}
