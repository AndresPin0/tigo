package tigo.aplanchados;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import tigo.aplanchados.model.PaymentMethod;
import tigo.aplanchados.repositories.PaymentMethodRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
//check
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class PaymentMethodRepositoryTest {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Test
    void testSaveAndFindById() {
        PaymentMethod paymentMethod = new PaymentMethod();

        paymentMethod.setDescription("Credit Card");

        PaymentMethod savedPaymentMethod = paymentMethodRepository.save(paymentMethod);

        Optional<PaymentMethod> foundPaymentMethod = paymentMethodRepository.findById(savedPaymentMethod.getCode());

        assertTrue(foundPaymentMethod.isPresent());
        assertEquals("Credit Card", foundPaymentMethod.get().getDescription());
    }

    @Test
    void testDelete() {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setCode(1L);
        paymentMethod.setDescription("Wire Transfer");

        PaymentMethod savedPaymentMethod = paymentMethodRepository.save(paymentMethod);
        paymentMethodRepository.deleteById(savedPaymentMethod.getCode());

        Optional<PaymentMethod> foundPaymentMethod = paymentMethodRepository.findById(savedPaymentMethod.getCode());

        assertTrue(foundPaymentMethod.isEmpty());
    }
}
