package tigo.aplanchados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tigo.aplanchados.model.PaymentMethod;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, String> {
}