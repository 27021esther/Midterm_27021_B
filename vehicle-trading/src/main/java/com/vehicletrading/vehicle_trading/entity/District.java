package com.vehicletrading.vehicle_trading.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * District entity – level 2 of Rwanda administrative hierarchy.
 * Many Districts belong to one Province.
 * One District contains many Sectors.
 */
@Entity
@Table(name = "districts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "district_code", unique = true, nullable = false, length = 20)
    private String districtCode;

    @Column(name = "district_name", nullable = false, length = 100)
    private String districtName;

    /** Many Districts belong to one Province */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "province_id", nullable = false)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Province province;

    /** One District has many Sectors */
    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Sector> sectors;
}
