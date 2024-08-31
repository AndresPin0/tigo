package tigo.aplanchados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tigo.aplanchados.model.Income;

public interface IncomeRepository extends JpaRepository<Income, Long> {
}