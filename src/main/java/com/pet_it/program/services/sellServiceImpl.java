/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.services;

import com.pet_it.program.DAO.sellDAO;
import com.pet_it.program.domain.Product;
import com.pet_it.program.domain.Sells;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jordan
 */
@Service
public class sellServiceImpl implements sellService {

    @Autowired
    private sellDAO sellDAO;

    @Override
    public List<Sells> getPurchases() {

        return (List<Sells>) sellDAO.findAll();

    }

    @Override
    public List<Sells> getSales() {
        return (List<Sells>) sellDAO.findAll();

    }

    @Override
    public void eliminarSells(Sells sells) {
        this.sellDAO.delete(sells);
    }

    @Override
    public Sells getSellsById(Long sellsid) {
        return this.sellDAO.findById(sellsid).orElse(null);
    }

    @Override
    public Sells cercarSells(Sells sells) {
        return this.sellDAO.findById(sells.getId()).orElse(null);
    }

    @Override
    public Sells afegirSells(Sells sells) {
        return this.sellDAO.save(sells);
    }

}
