package com.vehicletrading.vehicle_trading.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Location entity stores district and sector details.
 * Many Locations belong to one Province (Many-to-One).
 * One Location has One Customer (One-to-One).
 */
@Entity
@Table(name = "locations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String district;

    @Column(nullable = false, length = 100)
    private String sector;

    @Column(length = 200)
    private String street;

    /**
     * Many Locations belong to one Province.
     * The foreign key column "province_id" lives in the locations table.
     * FetchType.LAZY means province is loaded only when accessed.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "province_id", nullable = false)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Province province;

    /**
     * One Location has One Customer (One-to-One).
     * The "location_id" column in customers table holds the FK.
     * This is the inverse side - Customer owns the relationship.
     */
    @OneToOne(mappedBy = "location")
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Customer customer;

}
