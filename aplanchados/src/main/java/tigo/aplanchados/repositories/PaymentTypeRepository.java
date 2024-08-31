package tigo.aplanchados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tigo.aplanchados.model.PaymentMethod;
import tigo.aplanchados.model.PaymentType;

public interface PaymentTypeRepository extends JpaRepository<PaymentType, Long> {
}