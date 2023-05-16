package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.DTO.ScheduleDTO;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
        @Autowired
        ScheduleService scheduleService;

        @Autowired
        EmployeeService employeeService;

        @Autowired
        PetService petService;

        @PostMapping
        public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {

                Schedule schedule = new Schedule();
                //copy properties from  scheduleDTO to schedule
                BeanUtils.copyProperties(scheduleDTO, schedule);
                List<Pet> pets = petService.getPetsById(scheduleDTO.getPetIds());
                schedule.setPets(pets);
                List<Employee> employees = employeeService.findEmployeesById(scheduleDTO.getEmployeeIds());
                schedule.setEmployee(employees);
                Schedule storedSchedule = scheduleService.saveSchedule(schedule);
                scheduleDTO.setId(storedSchedule.getId());
                return scheduleDTO;
        }

        @GetMapping
        public List<ScheduleDTO> getAllSchedules() {
                List<Schedule> schedules = scheduleService.getAllSchedules();
                List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
                for (Schedule schedule : schedules) {
                        //copy properties from Schedules to SchedulesDTOS
                        ScheduleDTO scheduleDTO = new ScheduleDTO();
                        BeanUtils.copyProperties(schedule, scheduleDTO);

                        List<Long> petIds;
                        if (schedule.getPets() != null) {
                                petIds = schedule.getPets().stream().map(Pet::getId).collect(Collectors.toList());
                        } else {
                                petIds = new ArrayList<>();
                        }
                        scheduleDTO.setPetIds(petIds);

                        List<Long> employeeIds;
                        if (schedule.getEmployee() != null) {
                                employeeIds = schedule.getEmployee().stream().map(Employee::getId).collect(Collectors.toList());
                        } else {
                                employeeIds = new ArrayList<>();
                        }
                        scheduleDTO.setEmployeeIds(employeeIds);

                        scheduleDTOS.add(scheduleDTO);


                }
                return scheduleDTOS;
        }


        @GetMapping("/pet/{petId}")
        public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
                List<Schedule> schedules = scheduleService.getAllSchedulesByPetId(petId);
                List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
                for (Schedule schedule : schedules) {
                        //copy properties from Schedules to SchedulesDTOS
                        ScheduleDTO scheduleDTO = new ScheduleDTO();
                        BeanUtils.copyProperties(schedule, scheduleDTO);

                        List<Long> petIds;
                        if (schedule.getPets() != null) {
                                petIds = schedule.getPets().stream().map(Pet::getId).collect(Collectors.toList());
                        } else {
                                petIds = new ArrayList<>();
                        }
                        scheduleDTO.setPetIds(petIds);

                        List<Long> employeeIds;
                        if (schedule.getEmployee() != null) {
                                employeeIds = schedule.getEmployee().stream().map(Employee::getId).collect(Collectors.toList());
                        } else {
                                employeeIds = new ArrayList<>();
                        }
                        scheduleDTO.setEmployeeIds(employeeIds);

                        scheduleDTOS.add(scheduleDTO);


                }
                return scheduleDTOS;
        }

        @GetMapping("/employee/{employeeId}")
        public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
                List<Schedule> schedules = scheduleService.getAllSchedulesByEmployeeId(employeeId);
                List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
                for (Schedule schedule : schedules) {
                        //copy properties from Schedules to SchedulesDTOS
                        ScheduleDTO scheduleDTO = new ScheduleDTO();
                        BeanUtils.copyProperties(schedule, scheduleDTO);

                        List<Long> petIds;
                        if (schedule.getPets() != null) {
                                petIds = schedule.getPets().stream().map(Pet::getId).collect(Collectors.toList());
                        } else {
                                petIds = new ArrayList<>();
                        }
                        scheduleDTO.setPetIds(petIds);

                        List<Long> employeeIds;
                        if (schedule.getEmployee() != null) {
                                employeeIds = schedule.getEmployee().stream().map(Employee::getId).collect(Collectors.toList());
                        } else {
                                employeeIds = new ArrayList<>();
                        }
                        scheduleDTO.setEmployeeIds(employeeIds);

                        scheduleDTOS.add(scheduleDTO);


                }
                return scheduleDTOS;
        }

        @GetMapping("/customer/{customerId}")
        public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
                List<Schedule> schedules = scheduleService.getAllSchedulesByCustomerId(customerId);
                List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
                for (Schedule schedule : schedules) {
                        //copy properties from Schedules to SchedulesDTOS
                        ScheduleDTO scheduleDTO = new ScheduleDTO();
                        BeanUtils.copyProperties(schedule, scheduleDTO);

                        List<Long> petIds;
                        if (schedule.getPets() != null) {
                                petIds = schedule.getPets().stream().map(Pet::getId).collect(Collectors.toList());
                        } else {
                                petIds = new ArrayList<>();
                        }
                        scheduleDTO.setPetIds(petIds);

                        List<Long> employeeIds;
                        if (schedule.getEmployee() != null) {
                                employeeIds = schedule.getEmployee().stream().map(Employee::getId).collect(Collectors.toList());
                        } else {
                                employeeIds = new ArrayList<>();
                        }
                        scheduleDTO.setEmployeeIds(employeeIds);

                        scheduleDTOS.add(scheduleDTO);


                }
                return scheduleDTOS;
        }

}