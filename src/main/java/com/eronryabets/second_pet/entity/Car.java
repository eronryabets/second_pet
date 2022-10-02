package com.eronryabets.second_pet.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "cars")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Builder
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long id;

    @Column(name = "car_brand")
    @NonNull
    private String carBrand;


    @Column(name = "model")
    @NonNull
    private String model;

    @Column(name = "year")
    @NonNull
    private int year;

    @Column(name = "car_number")
    @NonNull
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
