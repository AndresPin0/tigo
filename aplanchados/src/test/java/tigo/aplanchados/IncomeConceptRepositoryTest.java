package tigo.aplanchados;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tigo.aplanchados.model.IncomeConcept;
import tigo.aplanchados.repositories.IncomeConceptRepository;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tigo.aplanchados.model.Expense;
import tigo.aplanchados.repositories.ExpenseRepository;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@SpringBootTest
class IncomeConceptRepositoryTest {

    @Autowired
    private IncomeConceptRepository incomeConceptRepository;

    @Test
    void testSaveAndFindById() {
        IncomeConcept incomeConcept = new IncomeConcept();
        incomeConcept.setDescription("Performance Bonus");

        IncomeConcept savedIncomeConcept = incomeConceptRepository.save(incomeConcept);

        Optional<IncomeConcept> foundIncomeConcept = incomeConceptRepository.findById(savedIncomeConcept.getId());

        assertTrue(foundIncomeConcept.isPresent());
        assertEquals("Performance Bonus", foundIncomeConcept.get().getDescription());
    }

    @Test
    void testDelete() {
        IncomeConcept incomeConcept = new IncomeConcept();
        IncomeConcept savedIncomeConcept = incomeConceptRepository.save(incomeConcept);
        incomeConceptRepository.deleteById(savedIncomeConcept.getId());
        Optional<IncomeConcept> foundIncomeConcept = incomeConceptRepository.findById(savedIncomeConcept.getId());

        assertTrue(foundIncomeConcept.isEmpty());
    }
}
