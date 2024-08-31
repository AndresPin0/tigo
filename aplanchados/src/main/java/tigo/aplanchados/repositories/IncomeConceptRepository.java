package tigo.aplanchados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tigo.aplanchados.model.IncomeConcept;

public interface IncomeConceptRepository extends JpaRepository<IncomeConcept, String> {
}