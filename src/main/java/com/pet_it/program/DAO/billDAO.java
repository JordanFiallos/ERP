package com.pet_it.program.DAO;

import com.pet_it.program.domain.Bill;
import com.pet_it.program.domain.Customer;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Administrador
 */
public interface billDAO extends JpaRepository<Bill, Long> {

    List<Bill> findByCustomer(Customer customer);

    List<Bill> findByIssueDateBetween(LocalDate startDate, LocalDate endDate);

    List<Bill> findByRevenue(int revenue);

    List<Bill> findByRevenueLessThan(int revenue);

    List<Bill> findByRevenueGreaterThan(int revenue);
}
