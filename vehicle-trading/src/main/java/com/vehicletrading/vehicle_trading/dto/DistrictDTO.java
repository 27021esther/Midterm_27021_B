package com.vehicletrading.vehicle_trading.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DistrictDTO {
    private Long id;
    private String districtCode;
    private String districtName;
    private Long provinceId;
    private String provinceName;
}
