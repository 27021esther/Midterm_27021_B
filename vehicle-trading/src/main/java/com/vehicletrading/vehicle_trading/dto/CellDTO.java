package com.vehicletrading.vehicle_trading.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CellDTO {
    private Long id;
    private String cellCode;
    private String cellName;
    private Long sectorId;
    private String sectorName;
}
