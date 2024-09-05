package tigo.aplanchados;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tigo.aplanchados.model.Expense;
import tigo.aplanchados.repositories.ExpenseRepository;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
//check
public class ExpenseRepositoryTest {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Test
    void testSaveAndFind() {
        Expense expense = new Expense();
        expense.setValue(1000);
        expense.setAdditionalDetail("Concepto de prueba");

        Expense savedExpense = expenseRepository.save(expense);

        Optional<Expense> foundExpense = expenseRepository.findById(savedExpense.getId());

        assertTrue(foundExpense.isPresent());

        assertEquals(1000, foundExpense.get().getValue());

        assertEquals("Concepto de prueba", foundExpense.get().getAdditionalDetail());

    }

    @Test
    void testDelete() {
        Expense expense = new Expense();
        expense.setValue(2000);
        expense.setAdditionalDetail("Concepto de prueba 2");

        Expense savedExpense = expenseRepository.save(expense);
        expenseRepository.deleteById(savedExpense.getId());

        Optional<Expense> foundExpense = expenseRepository.findById(savedExpense.getId());

        assertTrue(foundExpense.isEmpty());
    }
    
}
