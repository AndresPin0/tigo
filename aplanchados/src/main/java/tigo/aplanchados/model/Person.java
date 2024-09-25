package tigo.aplanchados.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

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
