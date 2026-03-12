package com.vehicletrading.vehicle_trading.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Province entity represents a geographical province.
 * One Province has many Locations (One-to-Many).
 */
@Entity
@Table(name = "provinces")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Province {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Unique province code, e.g. "KIG", "S", "N", "E", "W" */
    @Column(name = "province_code", unique = true, nullable = false, length = 10)
    private String provinceCode;

    /** Full province name, e.g. "Kigali City", "Southern Province" */
    @Column(name = "province_name", unique = true, nullable = false, length = 100)
    private String provinceName;

    /**
     * One province contains many locations (legacy – kept for backward compatibility).
     * mappedBy = "province" refers to the field in Location entity.
     */
    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Location> locations;

    /**
     * One Province has many Districts (Rwanda administrative hierarchy level 2).
     */
    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<District> districts;
}
