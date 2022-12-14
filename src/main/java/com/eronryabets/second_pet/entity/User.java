package com.eronryabets.second_pet.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@ToString(exclude = {"carList"})
@Builder
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "surname")
    @Setter
    @NonNull
    @NotBlank(message = "Surname must be not empty!")
    private String surname;

    @Column(name = "name")
    @Setter
    @NonNull
    @NotBlank(message = "Name must be not empty!")
    private String name;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @Setter
    private List<Car> carList;

    public void addCar(Car car){
        if(carList == null){
            carList = new ArrayList<>();
        }
        carList.add(car);
        car.setUser(this);
    }

}
