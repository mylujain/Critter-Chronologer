package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.supplier.PetType;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="pet")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private PetType type;
    @Nationalized
    private String name;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Customer customer;
    private LocalDate birthDate;
    private String notes;
}