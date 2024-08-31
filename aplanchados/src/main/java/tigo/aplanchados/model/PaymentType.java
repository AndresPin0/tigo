package tigo.aplanchados.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name="payment_type")
public class PaymentType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;
    private String description;

    @OneToMany(mappedBy = "paymentType")
    private List<Income> incomes;

    @OneToMany(mappedBy = "paymentType")
    private List<Expense> expenses;
}
