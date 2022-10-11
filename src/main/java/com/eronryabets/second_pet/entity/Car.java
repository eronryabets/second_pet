package com.eronryabets.second_pet.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cars")
@Getter
@Setter
@Builder
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long id;

    @Column(name = "car_brand")
    @NonNull
    @NotBlank(message = "Car BRAND must be not empty!")
    private String carBrand;


    @Column(name = "model")
    @NonNull
    @NotBlank(message = "Car MODEL must be not empty!")
    private String model;

    @Column(name = "year")
    @NonNull
    @NotNull(message = "Car YEAR must be not empty!")
    private int year;

    @Column(name = "car_number")
    @NonNull
    @NotBlank(message = "Car NUMBER must be not empty!")
    private String carNumber;

    @ManyToOne(
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.REFRESH,
                    CascadeType.MERGE,
            },
            fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User user;

}
