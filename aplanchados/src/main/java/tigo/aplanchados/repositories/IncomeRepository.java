package tigo.aplanchados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tigo.aplanchados.model.Income;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
}