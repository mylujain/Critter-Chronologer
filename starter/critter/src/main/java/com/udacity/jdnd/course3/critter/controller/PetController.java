package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.DTO.PetDTO;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetService petService;

    @Autowired
    CustomerService customerService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        pet.setCustomer(customerService.findCustomer(petDTO.getOwnerId()));
        Pet storedPet = petService.savePet(pet);
        petDTO.setId(storedPet.getId());

        return petDTO;
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pet= petService.getPet(petId);
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        petDTO.setOwnerId(pet.getCustomer().getId());
        return  petDTO;
    }

    @GetMapping
    public List<PetDTO> getPets(){

        List<Pet> pets =petService.findAllPets();
        List<PetDTO> petDTOList = new ArrayList<>();
        PetDTO eachPet = new PetDTO();
        for(Pet p : pets){
            //copy properties from Pets to petDTOS
            BeanUtils.copyProperties(p, eachPet);
            petDTOList.add(eachPet);
        }
        return petDTOList;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> pets = petService.getPetsByOwner(ownerId);
        List<PetDTO> petDTOList = new ArrayList<>();
        for(Pet pet : pets){
            //copy properties from Pets to petDTOS
            PetDTO petDTO = new PetDTO();
            BeanUtils.copyProperties(pet, petDTO);
            petDTO.setOwnerId(pet.getCustomer().getId());
            petDTOList.add(petDTO);
        }
        return petDTOList;
    }



}
