package com.vehicletrading.vehicle_trading.service;

import com.vehicletrading.vehicle_trading.entity.Province;
import com.vehicletrading.vehicle_trading.repository.ProvinceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProvinceService {

    private final ProvinceRepository provinceRepository;

    /**
     * Save a province after checking existence via existsByProvinceCode().
     * existsByProvinceCode() runs a COUNT query and returns boolean,
     * avoiding duplicate province codes.
     */
    public Province saveProvince(Province province) {
        if (provinceRepository.existsByProvinceCode(province.getProvinceCode())) {
            throw new IllegalArgumentException(
                "Province with code '" + province.getProvinceCode() + "' already exists.");
        }
        return provinceRepository.save(province);
    }

    public List<Province> getAllProvinces() {
        return provinceRepository.findAll();
    }

    public Province getById(Long id) {
        return provinceRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Province not found with id: " + id));
    }
}
