/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pet_it.program.services;

import com.pet_it.program.domain.Product;
import com.pet_it.program.domain.Sells;
import java.util.List;

/**
 *
 * @author Jordan
 */
public interface sellService {

    public List<Sells> getPurchases();

    public List<Sells> getSales();

    void eliminarSells(Sells sells);

    Sells getSellsById(Long sellsid);

    Sells cercarSells(Sells sells);

    Sells afegirSells(Sells sells);

    /* public List<Sells> obtenerComprasPorMesYProducto(List<Product> listaProductos, Sells sells);*/
}
