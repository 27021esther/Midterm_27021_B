package com.vehicletrading.vehicle_trading.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

/**
 * User entity for authentication and authorization.
 * Many-to-One with Village.
 * Many-to-Many with Role through join table "user_roles".
 * One-to-One with UserProfile.
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(unique = true, nullable = false, length = 150)
    private String email;

    @Column(unique = true, nullable = false, length = 50)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    /**
     * Many-to-One: a User belongs to one Village.
     * Through Village → Cell → Sector → District → Province the full address is traversable.
     * The foreign key "village_id" is stored in the users table.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "village_id", nullable = false)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Village village;

    /**
     * Many-to-Many: a User can have many Roles,
     * and a Role can have many Users.
     * The join table "user_roles" holds the foreign keys.
     */
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Role> roles;

    /**
     * One-to-One: a User has one UserProfile.
     * The foreign key "user_profile_id" is stored in the users table.
     * cascade = CascadeType.ALL ensures profile is saved/updated with user.
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id", unique = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private UserProfile userProfile;
}