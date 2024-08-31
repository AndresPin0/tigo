package tigo.aplanchados;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tigo.aplanchados.model.ExpenseConcept;
import tigo.aplanchados.repositories.ExpenseConceptRepository;
import java.util.Optional;

@SpringBootTest
public class ExpenseConceptRepositoryTest {


    @Test
    void testSaveAndFind() {
        ExpenseConceptRepository expenseConceptRepository;
        ExpenseConcept expenseConcept = new ExpenseConcept();
        expenseConcept.setCode(123L);
        expenseConcept.setDescription("Concepto de prueba");

        

    }





    
    
}
