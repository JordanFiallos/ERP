package com.pet_it.program.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Houssam
 */
@Entity
//@Table(name = "customer")
@DiscriminatorValue("Customer")
@Data
public class Customer extends Person {
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Bill> bills;
    
}
