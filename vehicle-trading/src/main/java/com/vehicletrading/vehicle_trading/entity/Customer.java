package com.vehicletrading.vehicle_trading.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


import java.util.List;

/**
 * Customer entity stores buyer/seller information.
 * One-to-One with Location (owning side: Customer holds the FK).
 * One-to-Many with Transaction (one customer can have many transactions).
 */
@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    /** Email must be unique – used with existsByEmail() */
    @Column(unique = true, nullable = false, length = 150)
    private String email;

    @Column(length = 20)
    private String phone;

    /**
     * Many-to-One: a Customer belongs to one Village.
     * Through Village → Cell → Sector → District → Province the full address is traversable.
     * The foreign key "village_id" is stored in the customers table.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "village_id", nullable = false)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Village village;

    /**
     * One-to-One: a Customer can have one Location (alternative address).
     * The foreign key "location_id" is stored in the customers table.
     * This demonstrates One-to-One relationship with Location entity.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", unique = true)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Location location;

    /**
     * One Customer → Many Transactions.
     * mappedBy = "customer" means Transaction owns the FK.
     */
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Transaction> transactions;
}
