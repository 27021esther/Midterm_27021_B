package com.vehicletrading.vehicle_trading.controller;

import com.vehicletrading.vehicle_trading.entity.Customer;
import com.vehicletrading.vehicle_trading.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    /**
     * POST /api/customers?villageId=1
     * Creates a customer and links them to the given village.
     * Automatically connected to Cell → Sector → District → Province via relationships.
     */
    @PostMapping
    public ResponseEntity<Customer> create(
            @RequestBody Customer customer,
            @RequestParam Long villageId) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(customerService.saveCustomer(customer, villageId));
    }

    // GET /api/customers
    @GetMapping
    public ResponseEntity<List<Customer>> getAll() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    // GET /api/customers/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getById(id));
    }

    /**
     * GET /api/customers/by-province?code=KIG
     * Retrieves all customers from a province using province CODE.
     * Traversal: customer.village.cell.sector.district.province.provinceCode
     */
    @GetMapping("/by-province")
    public ResponseEntity<List<Customer>> getByProvinceCode(@RequestParam String code) {
        return ResponseEntity.ok(customerService.getCustomersByProvinceCode(code));
    }

    /**
     * GET /api/customers/by-province-name?name=Kigali City
     * Retrieves all customers from a province by province NAME.
     */
    @GetMapping("/by-province-name")
    public ResponseEntity<List<Customer>> getByProvinceName(@RequestParam String name) {
        return ResponseEntity.ok(customerService.getCustomersByProvinceName(name));
    }

    /**
     * GET /api/customers/by-district?name=Gasabo
     * Retrieves all customers from a district by district NAME.
     */
    @GetMapping("/by-district")
    public ResponseEntity<List<Customer>> getByDistrictName(@RequestParam String name) {
        return ResponseEntity.ok(customerService.getCustomersByDistrictName(name));
    }

    /**
     * GET /api/customers/by-sector?name=Kimironko
     * Retrieves all customers from a sector by sector NAME.
     */
    @GetMapping("/by-sector")
    public ResponseEntity<List<Customer>> getBySectorName(@RequestParam String name) {
        return ResponseEntity.ok(customerService.getCustomersBySectorName(name));
    }

    /**
     * GET /api/customers/by-village?name=Bibare
     * Retrieves all customers from a village by village NAME.
     */
    @GetMapping("/by-village")
    public ResponseEntity<List<Customer>> getByVillageName(@RequestParam String name) {
        return ResponseEntity.ok(customerService.getCustomersByVillageName(name));
    }
}
