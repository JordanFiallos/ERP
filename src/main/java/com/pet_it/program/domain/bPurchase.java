/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;

/**
 *
 * @author Ricard
 */
@Entity
@Table(name = "Purchase")
@Data
public class bPurchase extends bBill {
    @ManyToOne
    @JoinColumn(name="id_product")
    private Product product;
    @ManyToOne
    @JoinColumn(name="id_suppler")
    private Supplier supplier;
    @Column(name="scheduled_delivery_date")
    private LocalDateTime scheduledDeliveryDate;
}
