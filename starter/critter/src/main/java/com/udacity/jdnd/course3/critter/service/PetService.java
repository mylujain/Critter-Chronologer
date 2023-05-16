package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class PetService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    PetRepository petRepository;


    public Pet savePet(Pet p) {
        Pet pet = petRepository.save(p);
        savePetToCustomer(pet);
        return pet;
    }
    public void savePetToCustomer(Pet pet) {
        Customer customer = pet.getCustomer();
        List<Pet> customerPets = customer.getPets();
        boolean notEmpty = customerPets != null;
        if(!notEmpty){
            customerPets = new ArrayList<>();
        }
        customerPets.add(pet);

        customer.setPets(customerPets);
        customerRepository.save(customer);
    }


    public List<Pet> getPetsById(List<Long> id){
        return petRepository.findAllById(id);
    }

    public List<Pet> findAllPets() {
        return petRepository.findAll();
    }

    public Pet getPet(Long id) {
        return petRepository.getOne(id);
    }

    public List<Pet> getPetsByOwner(Long id){
        Optional<Customer> customer = customerRepository.findById(id);
        boolean notEmpty = customer.isPresent() ;

        if(notEmpty){
            return customer.get().getPets();
        } else {
            return null;

        }
    }




}