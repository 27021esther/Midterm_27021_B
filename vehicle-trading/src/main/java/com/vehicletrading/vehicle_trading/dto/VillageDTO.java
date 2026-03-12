package com.vehicletrading.vehicle_trading.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VillageDTO {
    private Long id;
    private String villageCode;
    private String villageName;
    private Long cellId;
    private String cellName;
    private String sectorName;
    private String districtName;
    private String provinceName;
}
