package tigo.aplanchados.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name="payment_method")
public class PaymentMethod implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;
    private String description;

    @OneToMany(mappedBy = "paymentMethod")
    private List<Income> incomes;

    @OneToMany(mappedBy = "paymentMethod")
    private List<Expense> expenses;
}
