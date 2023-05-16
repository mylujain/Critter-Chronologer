package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByPetsContaining(Pet pet);
    List<Schedule> findAllByEmployeeContaining(Employee employee);



}