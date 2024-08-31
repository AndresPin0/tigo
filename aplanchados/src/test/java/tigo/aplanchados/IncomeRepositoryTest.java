package tigo.aplanchados;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tigo.aplanchados.model.*;
import tigo.aplanchados.repositories.*;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class IncomeRepositoryTest {

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private IncomeConceptRepository incomeConceptRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testSaveAndFindById() {


        Income income = new Income();
        income.setValue(5000);

        Income savedIncome = incomeRepository.save(income);

        Optional<Income> foundIncome = incomeRepository.findById(savedIncome.getId());

        assertTrue(foundIncome.isPresent());
        assertEquals(5000, foundIncome.get().getValue());
    }

    @Test
    void testDelete() {

        Income income = new Income();
        income.setValue(3000);
        income.setAdditionalDetail("Bonus");

        incomeRepository.save(income);

        incomeRepository.deleteById(income.getId());

        Optional<Income> foundIncome = incomeRepository.findById(income.getId());

        assertTrue(foundIncome.isEmpty());
    }
}
