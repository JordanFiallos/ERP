/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pet_it.program.services;

import com.pet_it.program.domain.Pet;
import java.util.List;

/**
 *
 * @author Jordan
 */
public interface petService {
    List<Pet> llistarPets();
    void afegirPets(Pet pets);
    void eliminarPets(Pet pets);
    Pet buscarPets(Pet pet);
    public Pet getPetById(Long id);
}
