package com.pet_it.program.DAO;

import com.pet_it.program.domain.Customer;
import com.pet_it.program.domain.bBill;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Administrador
 */
public interface bBillDAO extends JpaRepository<bBill, Long> {

    

}
