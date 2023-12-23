/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.services;

import com.pet_it.program.DAO.productDAO;
import com.pet_it.program.domain.Product;
import com.pet_it.program.domain.Sells;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
 *
 * @author Jordan
 */
@Service
public class productServiceImpl implements productService {

    @Autowired
    private productDAO productodao;

    @Autowired
    private sellService sellservice;

    @Override
    public List<Product> llistarProductos() {
        List<Product> listaProduct = productodao.findAll();

        for (Product products : listaProduct) {
            int quantity = products.getQuantity();
            int minimQuantity = products.getMinimQuantity();

            if (isQuantityValid(quantity, minimQuantity)) {
                int updatedRestminim = products.getMinimQuantity() - quantity;
                products.setRestminim(updatedRestminim);
            }
        }
        return listaProduct;
    }

    @Override
    public Product afegirProducto(Product product) {
        product.setDatebuy(LocalDate.now());
        product.setInterest(0);
        return this.productodao.save(product);
    }

    public String afegirProducto2(Product product, int quantitySumar) {
        int minimQuantity = product.getMinimQuantity();

        if (isQuantityValid(quantitySumar, minimQuantity)) {
            int oldQuantity = product.getQuantity();
            int quantityFinal = oldQuantity + quantitySumar;
            product.setQuantity(quantityFinal);

            afegirProducto(product);

            return "Producto añadido exitosamente.";
        } else {
            return "La cantidad ingresada no es válida.";
        }
    }

    @Override
    public void eliminarProducto(Product product) {
        this.productodao.delete(product);
    }

    @Override
    public Product getProductoById(Long productId) {
        return productodao.findById(productId).orElse(null);
    }

    @Override
    public Product cercarProducto(Product product) {
        return productodao.findById(product.getId()).orElse(null);
    }

    public boolean isQuantityValid(int quantity, int minimQuantity) {
        return quantity >= 0 && quantity <= minimQuantity;
    }

    @Override
    public double getTotalPrice(double quantity, int price) {
        return quantity * price;
    }
}
