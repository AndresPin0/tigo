package tigo.aplanchados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tigo.aplanchados.model.IncomeConcept;

@Repository
public interface IncomeConceptRepository extends JpaRepository<IncomeConcept, Long> {
}