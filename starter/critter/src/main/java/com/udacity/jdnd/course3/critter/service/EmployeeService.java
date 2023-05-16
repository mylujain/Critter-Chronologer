package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.supplier.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long id)
    {
        return employeeRepository.getOne(id);
    }

    public void setsAvailableDays(Set<DayOfWeek> availableDays, long id)
    {
        Employee employee = employeeRepository.getOne(id);
        employee.setDaysAvailable(availableDays);
        employeeRepository.save(employee);
    }

public List<Employee> getEmployeesForService(LocalDate date, Set<EmployeeSkill> skills) {
    List<Employee> employees = employeeRepository.findAllByDaysAvailableContaining(date.getDayOfWeek());
    List<Employee> availEmployees= new ArrayList<>();
            for (Employee e : employees){
            if ( e.getSkills().containsAll(skills)) {
                availEmployees.add(e);
            }
        }
    return availEmployees;

}

    public List<Employee> findEmployeesById(List<Long> id){
        return employeeRepository.findAllById(id);
    }



}
