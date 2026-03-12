package com.vehicletrading.vehicle_trading.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SectorDTO {
    private Long id;
    private String sectorCode;
    private String sectorName;
    private Long districtId;
    private String districtName;
}
