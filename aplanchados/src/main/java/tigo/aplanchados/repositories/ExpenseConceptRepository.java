package tigo.aplanchados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tigo.aplanchados.model.ExpenseConcept;

@Repository
public interface ExpenseConceptRepository extends JpaRepository<ExpenseConcept, Long> {

}