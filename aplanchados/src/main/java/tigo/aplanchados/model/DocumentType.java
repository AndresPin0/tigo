package tigo.aplanchados.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import java.io.Serializable;

@Entity
@Data
@Table(name = "document_type")
public class DocumentType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "personPK.documentType")
    private List<Person> persons;
}
