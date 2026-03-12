package com.vehicletrading.vehicle_trading.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Village entity – level 5 (bottom) of Rwanda administrative hierarchy.
 * Many Villages belong to one Cell.
 * A Customer is linked here; through relationships it connects up to Province.
 */
@Entity
@Table(name = "villages")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Village {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "village_code", unique = true, nullable = false, length = 20)
    private String villageCode;

    @Column(name = "village_name", nullable = false, length = 100)
    private String villageName;

    /** Many Villages belong to one Cell */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cell_id", nullable = false)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Cell cell;

    /** One Village can have many Customers */
    @OneToMany(mappedBy = "village", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Customer> customers;
}
