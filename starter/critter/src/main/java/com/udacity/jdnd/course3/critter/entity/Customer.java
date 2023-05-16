package com.udacity.jdnd.course3.critter.entity;
import com.udacity.jdnd.course3.critter.entity.Pet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="customer")
public class Customer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Nationalized
    private String name;
    private String phoneNumber;
    private String notes;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Pet> pets;




}
