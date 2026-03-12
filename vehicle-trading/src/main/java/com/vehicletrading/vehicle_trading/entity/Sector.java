package com.vehicletrading.vehicle_trading.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Sector entity – level 3 of Rwanda administrative hierarchy.
 * Many Sectors belong to one District.
 * One Sector contains many Cells.
 */
@Entity
@Table(name = "sectors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sector_code", unique = true, nullable = false, length = 20)
    private String sectorCode;

    @Column(name = "sector_name", nullable = false, length = 100)
    private String sectorName;

    /** Many Sectors belong to one District */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "district_id", nullable = false)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private District district;

    /** One Sector has many Cells */
    @OneToMany(mappedBy = "sector", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Cell> cells;
}
