package tigo.aplanchados.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import tigo.aplanchados.model.IncomeConcept;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@SpringBootTest
//check
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
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
        incomeConcept.setDescription("Overtime Pay");
        IncomeConcept savedIncomeConcept = incomeConceptRepository.save(incomeConcept);
        incomeConceptRepository.deleteById(savedIncomeConcept.getId());
        Optional<IncomeConcept> foundIncomeConcept = incomeConceptRepository.findById(savedIncomeConcept.getId());

        assertTrue(foundIncomeConcept.isEmpty());
    }
}