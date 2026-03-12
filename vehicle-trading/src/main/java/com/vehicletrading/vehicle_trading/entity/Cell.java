package com.vehicletrading.vehicle_trading.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Cell entity – level 4 of Rwanda administrative hierarchy.
 * Many Cells belong to one Sector.
 * One Cell contains many Villages.
 */
@Entity
@Table(name = "cells")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cell_code", unique = true, nullable = false, length = 20)
    private String cellCode;

    @Column(name = "cell_name", nullable = false, length = 100)
    private String cellName;

    /** Many Cells belong to one Sector */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sector_id", nullable = false)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Sector sector;

    /** One Cell has many Villages */
    @OneToMany(mappedBy = "cell", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Village> villages;
}
