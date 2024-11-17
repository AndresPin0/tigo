package tigo.aplanchados.services.interfaces;

import tigo.aplanchados.model.Expense;
import tigo.aplanchados.model.Income;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import java.time.LocalDate;

@Repository
public interface IncomeService {

    List<Income> findAllIncomes();

    Optional<Income> findIncomeById(Long id);

    Income createIncome(Income income);

    void deleteIncome(Long id);

    boolean updateIncome(Income income);

    List<Income> findAllIncomesForDate(LocalDate date);

    List<Income> findAllIncomesByMonth(int month);

}
