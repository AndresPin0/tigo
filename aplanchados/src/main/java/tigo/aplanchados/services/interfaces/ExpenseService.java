package tigo.aplanchados.services.interfaces;

import tigo.aplanchados.model.Expense;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import java.time.LocalDate;

@Repository
public interface ExpenseService {

    List<Expense> findAllExpenses();

    List<Expense> findAllExpenseForDate(LocalDate date);

    Optional<Expense> findExpenseById(Long id);

    Expense createExpense(Expense expense);

    void deleteExpense(Long id);

    boolean updateExpense(Expense expense);

    List<Expense> findAllExpensesByMonth(int month);

}
