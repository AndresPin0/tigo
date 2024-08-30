package tigo.aplanchados.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "person")
public class Person implements Serializable {
    @EmbeddedId
    private PersonPK personPK;

    private String name;

    @OneToMany(mappedBy = "person")
    private List<Income> incomes;

    @OneToMany(mappedBy = "person")
    private List<Expense> expenses;
}
