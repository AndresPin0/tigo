package tigo.aplanchados.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tigo.aplanchados.model.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}