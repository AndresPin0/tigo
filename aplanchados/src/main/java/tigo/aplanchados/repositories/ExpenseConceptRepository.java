package tigo.aplanchados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tigo.aplanchados.model.ExpenseConcept;

public interface ExpenseConceptRepository extends JpaRepository<ExpenseConcept, String> {
    ExpenseConcept findExpenseConceptByCode(String id);
}