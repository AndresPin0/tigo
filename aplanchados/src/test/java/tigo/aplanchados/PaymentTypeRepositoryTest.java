package tigo.aplanchados;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tigo.aplanchados.model.PaymentType;
import tigo.aplanchados.repositories.PaymentTypeRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class PaymentTypeRepositoryTest {

    @Autowired
    private PaymentTypeRepository paymentTypeRepository;

    @Test
    void testSaveAndFindById() {
        PaymentType paymentType = new PaymentType();
        paymentType.setDescription("Online Payment");

        PaymentType savedPaymentType = paymentTypeRepository.save(paymentType);

        Optional<PaymentType> foundPaymentType = paymentTypeRepository.findById(savedPaymentType.getCode());

        assertTrue(foundPaymentType.isPresent());
        assertEquals("Online Payment", foundPaymentType.get().getDescription());
    }

    @Test
    void testDelete() {
        PaymentType paymentType = new PaymentType();

        paymentType.setDescription("Cash Payment");

        PaymentType savedPaymentType = paymentTypeRepository.save(paymentType);
        paymentTypeRepository.deleteById(savedPaymentType.getCode());

        Optional<PaymentType> foundPaymentType = paymentTypeRepository.findById(savedPaymentType.getCode());

        assertTrue(foundPaymentType.isEmpty());
    }
}
