package com.vehicletrading.vehicle_trading.service;

import com.vehicletrading.vehicle_trading.entity.District;
import com.vehicletrading.vehicle_trading.entity.Province;
import com.vehicletrading.vehicle_trading.repository.DistrictRepository;
import com.vehicletrading.vehicle_trading.repository.ProvinceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DistrictService {

    private final DistrictRepository districtRepository;
    private final ProvinceRepository provinceRepository;

    public District save(District district, Long provinceId) {
        Province province = provinceRepository.findById(provinceId)
            .orElseThrow(() -> new RuntimeException("Province not found: " + provinceId));
        district.setProvince(province);
        return districtRepository.save(district);
    }

    public List<District> getAll() {
        return districtRepository.findAll();
    }

    public District getById(Long id) {
        return districtRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("District not found: " + id));
    }

    public List<District> getByProvinceId(Long provinceId) {
        return districtRepository.findByProvince_Id(provinceId);
    }

    public List<District> getByProvinceCode(String provinceCode) {
        return districtRepository.findByProvince_ProvinceCode(provinceCode);
    }
}
