package tigo.aplanchados.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name="expense_concept")
public class ExpenseConcept implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;
    private String description;

    @OneToMany(mappedBy = "expenseConcept")
    private List<Expense> expenses;
}
