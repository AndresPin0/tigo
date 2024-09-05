package tigo.aplanchados;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tigo.aplanchados.model.ExpenseConcept;
import tigo.aplanchados.repositories.ExpenseConceptRepository;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

//check
@SpringBootTest
public class ExpenseConceptRepositoryTest {
    @Autowired
    ExpenseConceptRepository expenseConceptRepository;



    @Test
    void testSaveAndFind() {
        ExpenseConcept expenseConcept = new ExpenseConcept();
        expenseConcept.setDescription("Concepto de prueba");
        expenseConceptRepository.save(expenseConcept);
        Optional<ExpenseConcept> foundExpenseConcept = expenseConceptRepository.findById(expenseConcept.getCode());
        assertTrue(foundExpenseConcept.isPresent());

        

    }

    @Test()

    void testDelete(){
        ExpenseConcept expenseConcept = new ExpenseConcept();
        expenseConcept.setDescription("Concepto de prueba 2");
        ExpenseConcept savedExpenseConcept = expenseConceptRepository.save(expenseConcept);
        expenseConceptRepository.deleteById(savedExpenseConcept.getCode());
        Optional<ExpenseConcept> foundExpenseConcept = expenseConceptRepository.findById(savedExpenseConcept.getCode());
        assertTrue(foundExpenseConcept.isEmpty());
    }





    
    
}
