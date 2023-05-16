package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class CustomerService {
    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerRepository customerRepository;


    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer findCustomer(Long id) {
        return customerRepository.getOne(id);
    }

    public List<Customer> getCustomersByName(String name) {
        return customerRepository.getCustomersByName(name);
    }

    public Customer getCustomerByPetId(Long petId){
      return petRepository.getOne(petId).getCustomer();}

    public List<Pet> getAllPets(Long id) {
        return customerRepository.getOne(id).getPets();
    }
}