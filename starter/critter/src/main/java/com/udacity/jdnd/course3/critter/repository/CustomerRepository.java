package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

   Customer findAllByPetsContaining(Long pet);
    List<Customer> getCustomersByName(String name);
}
