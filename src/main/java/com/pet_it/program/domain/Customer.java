package com.pet_it.program.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

/**
 *
 * @author Houssam
 */
@Entity
@Table(name = "customer")
@Data
public class Customer extends Person {
    
}
