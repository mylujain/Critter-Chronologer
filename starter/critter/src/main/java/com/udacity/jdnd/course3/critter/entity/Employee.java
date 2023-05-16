package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.supplier.EmployeeSkill;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.Set;

@Entity
@Table(name="employee")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Nationalized
    private String name;

    @ElementCollection
    private Set<EmployeeSkill> skills;

    @ElementCollection
    private Set<DayOfWeek> daysAvailable;
}