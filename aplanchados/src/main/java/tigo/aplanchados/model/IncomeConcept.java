package tigo.aplanchados.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name="income_concept")
public class IncomeConcept implements Serializable {
    @Id
    private String code;
    private String description;

    @OneToMany(mappedBy = "incomeConcept")
    private List<Income> incomes;
}
