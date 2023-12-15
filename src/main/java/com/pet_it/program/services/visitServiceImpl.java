/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.services;

import com.pet_it.program.DAO.visitDAO;
import com.pet_it.program.domain.Visit;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jordan
 */
@Service
public class visitServiceImpl implements visitService{

    @Autowired
    private visitDAO visitDao;
    
    @Override
    public List<Visit> llistarVisites() {
        List<Visit> visites = visitDao.findAll();
        return visites;
    }

    @Override
    public void agregarVisita(Visit visita) {
        visitDao.save(visita);
    }

    @Override
    public void eliminarVisita(Visit visita) {
        visitDao.delete(visita);
    }

    @Override
    public Visit getVisitById(Long id) {
        return visitDao.findById(id).orElse(null);
    }

   
}
