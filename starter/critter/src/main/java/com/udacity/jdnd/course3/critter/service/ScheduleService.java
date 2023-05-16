package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    PetRepository petRepository;

    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedules(){
        return scheduleRepository.findAll();
    }

    public List<Schedule> getAllSchedulesByPetId(Long id){
        return scheduleRepository.findAllByPetsContaining(petRepository.getOne(id));
    }

    public List<Schedule> getAllSchedulesByEmployeeId(Long id){
        return scheduleRepository.findAllByEmployeeContaining(employeeRepository.getOne(id));
    }

public List<Schedule> getAllSchedulesByCustomerId(Long id){
    Optional<Customer> customer = customerRepository.findById(id);
    if (customer.isPresent() && customer.get() != null) {
        List<Pet> pets = customer.get().getPets();
        List<Schedule> schedules = new ArrayList<>();
        for(Pet pet : pets){
            schedules.addAll(scheduleRepository.findAllByPetsContaining(pet));
        }
        return schedules;
    } else {
        return null;
    }
}

}
