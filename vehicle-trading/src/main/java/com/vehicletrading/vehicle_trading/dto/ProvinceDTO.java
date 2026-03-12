package com.vehicletrading.vehicle_trading.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for Province.
 * Used to transfer province data between layers without exposing the entity directly.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProvinceDTO {

    private Long id;
    private String provinceCode;
    private String provinceName;
}
