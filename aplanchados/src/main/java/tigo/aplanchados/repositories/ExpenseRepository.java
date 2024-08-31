package tigo.aplanchados.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import tigo.aplanchados.model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}