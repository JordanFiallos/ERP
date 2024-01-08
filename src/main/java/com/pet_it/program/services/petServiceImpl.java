/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.services;

import com.pet_it.program.DAO.petDAO;
import com.pet_it.program.domain.Pet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jordan
 */
@Service
public class petServiceImpl implements petService{

    @Autowired
    private petDAO petdao;
    
    @Override
    public List<Pet> llistarPets() {
        List<Pet> pets = petdao.findAll();
        return pets;
    
    }
    public List<Pet> llistarPetsHabilitats(){
        List<Pet> pets = petdao.llistaPetsHabilitats();
        return pets;
    }
    

    @Override
    public void afegirPets(Pet pets) {
        petdao.save(pets);
    }

    @Override
    public void eliminarPets(Pet pets) {
        petdao.delete(pets);
    }

    @Override
    public Pet buscarPets(Pet pet) {
        return petdao.findById(pet.getId()).orElse(null);
    }

    @Override
    public Pet getPetById(Long id) {
        return petdao.findById(id).orElse(null);
    }

    
}
