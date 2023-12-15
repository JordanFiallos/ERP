package com.pet_it.program.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Houssam
 */
@Entity
@Table(name = "customer")
@Data
public class Customer extends Person {
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Bill> bills;
    
}
