package com.vehicletrading.vehicle_trading.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for Location.
 * Uses provinceId as a reference instead of embedding the full Province object.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationDTO {

    private Long id;
    private String district;
    private String sector;
    private String street;

    /** ID of the province this location belongs to */
    private Long provinceId;

    /** Province name for display purposes */
    private String provinceName;
}
