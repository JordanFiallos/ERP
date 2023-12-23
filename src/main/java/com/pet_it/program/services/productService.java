/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pet_it.program.services;

import com.pet_it.program.domain.Product;
import java.util.List;

/**
 *
 * @author Jordan
 */
public interface productService {

    public List<Product> llistarProductos();

    void eliminarProducto(Product product);

    Product getProductoById(Long productId);

    Product cercarProducto(Product product);

    Product afegirProducto(Product product);

    public double getTotalPrice(double quantity, int price);
}
