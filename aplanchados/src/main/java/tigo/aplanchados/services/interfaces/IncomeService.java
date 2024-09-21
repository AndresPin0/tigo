package tigo.aplanchados.services.interfaces;

import tigo.aplanchados.model.Income;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public interface IncomeService {

    List<Income> findAllIncomes();

    Optional<Income> findIncomeById(Long id);

    Income createIncome(Income income);

    void deleteIncome(Long id);

    boolean updateIncome(Income income);

}
