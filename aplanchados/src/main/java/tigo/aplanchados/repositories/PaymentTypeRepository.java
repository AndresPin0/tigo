package tigo.aplanchados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tigo.aplanchados.model.PaymentMethod;

public interface PaymentTypeRepository extends JpaRepository<PaymentMethod, String> {
}