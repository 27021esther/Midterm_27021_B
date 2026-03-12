package com.vehicletrading.vehicle_trading.service;

import com.vehicletrading.vehicle_trading.entity.Customer;
import com.vehicletrading.vehicle_trading.entity.Village;
import com.vehicletrading.vehicle_trading.repository.CustomerRepository;
import com.vehicletrading.vehicle_trading.repository.VillageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final VillageRepository villageRepository;

    /**
     * Save a customer linked to a Village.
     * Through Village → Cell → Sector → District → Province the full hierarchy is accessible.
     * existsByEmail() verifies no duplicate email before saving.
     */
    public Customer saveCustomer(Customer customer, Long villageId) {
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new IllegalArgumentException(
                "Customer with email '" + customer.getEmail() + "' already exists.");
        }
        Village village = villageRepository.findById(villageId)
            .orElseThrow(() -> new RuntimeException("Village not found: " + villageId));
        customer.setVillage(village);
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getById(Long id) {
        return customerRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Customer not found: " + id));
    }

    /**
     * Retrieve all customers from a given province by province CODE.
     * Traversal: customer → village → cell → sector → district → province → provinceCode
     */
    public List<Customer> getCustomersByProvinceCode(String provinceCode) {
        return customerRepository.findByVillage_Cell_Sector_District_Province_ProvinceCode(provinceCode);
    }

    /**
     * Retrieve all customers from a given province by province NAME.
     */
    public List<Customer> getCustomersByProvinceName(String provinceName) {
        return customerRepository.findByProvinceName(provinceName);
    }

    /**
     * Retrieve all customers from a given district by district NAME.
     */
    public List<Customer> getCustomersByDistrictName(String districtName) {
        return customerRepository.findByDistrictName(districtName);
    }

    /**
     * Retrieve all customers from a given sector by sector NAME.
     */
    public List<Customer> getCustomersBySectorName(String sectorName) {
        return customerRepository.findBySectorName(sectorName);
    }

    /**
     * Retrieve all customers from a given village by village NAME.
     */
    public List<Customer> getCustomersByVillageName(String villageName) {
        return customerRepository.findByVillageName(villageName);
    }
}
