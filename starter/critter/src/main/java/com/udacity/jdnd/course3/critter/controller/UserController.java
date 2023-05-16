package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.DTO.CustomerDTO;
import com.udacity.jdnd.course3.critter.DTO.EmployeeDTO;
import com.udacity.jdnd.course3.critter.DTO.EmployeeRequestDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

@Autowired
private CustomerService customerService;
@Autowired
private EmployeeService employeeService;
@Autowired
private PetService petService;

@PostMapping("/customer")
public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        Customer customer = new Customer();
        //copy properties from customerDTOS to customers
        BeanUtils.copyProperties(customerDTO, customer);
        Customer storedCustomer = customerService.saveCustomer(customer);
        customerDTO.setId(storedCustomer.getId());
        return customerDTO;
        }



@GetMapping("/customer")
public List<CustomerDTO> getAllCustomers(){
        List<Customer> customers = customerService.getAllCustomers();
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        CustomerDTO eachCustomer = new CustomerDTO();
        for(Customer customer : customers) {
                CustomerDTO customerDTO = new CustomerDTO();
                if(customer != null) {
                        //copy properties from customer to customerDTO
                        BeanUtils.copyProperties(customer,customerDTO);
                        List<Long> petIds = new ArrayList<>();
                        if (customer.getPets()!=null) {
                                petIds = customer.getPets().stream().map(Pet::getId).collect(Collectors.toList());
                        }
                        customerDTO.setPetIds(petIds);
                        eachCustomer= customerDTO;
                        customerDTOS.add(eachCustomer);
                }
                eachCustomer= null;
        }
        return customerDTOS;
        }


@GetMapping("/customer/pet/{petId}")
public CustomerDTO getOwnerByPet(@PathVariable long petId){
        Customer customer =customerService.getCustomerByPetId(petId);

        CustomerDTO customerDTO = new CustomerDTO();
        if(customer != null) {
                //copy properties from customer to customerDTO
                BeanUtils.copyProperties(customer,customerDTO);
                List<Long> petIds = new ArrayList<>();
                if (customer.getPets()!=null) {
                        petIds = customer.getPets().stream().map(Pet::getId).collect(Collectors.toList());
                }
                customerDTO.setPetIds(petIds);
                return customerDTO;
        }
        return null;
}


@PostMapping("/employee")
public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        //copy properties from employeeDTO to employee
        BeanUtils.copyProperties(employeeDTO, employee);
        Employee savedEmployee = employeeService.saveEmployee(employee);
        employeeDTO.setId(savedEmployee.getId());
        return employeeDTO;
        }



@PostMapping("/employee/{employeeId}")
public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        Employee employee=employeeService.getEmployeeById(employeeId);
        EmployeeDTO employeeDTO = new EmployeeDTO();
        //copy properties from employee to employeeDTO
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
        }
private EmployeeDTO convertEmployeeToEmployeeDTO(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
        }
@PutMapping("/employee/{employeeId}")
public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeeService.setsAvailableDays(daysAvailable,employeeId);
        }

@GetMapping("/employee/availability")
public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        List<Employee> employees = employeeService.getEmployeesForService(employeeDTO.getDate(),employeeDTO.getSkills());
        List<EmployeeDTO> EmployeeDTO = new ArrayList<>();

        for(Employee employee : employees) {
                EmployeeDTO tempEmployeeDTO = new EmployeeDTO();
                //copy properties from employee to employeeDTO
                BeanUtils.copyProperties(employee, tempEmployeeDTO);
                EmployeeDTO.add(tempEmployeeDTO);

        }


        return EmployeeDTO;
}

        }
