package tigo.aplanchados.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class PersonPK implements Serializable {
    @ManyToOne
    @JoinColumn(name = "document_type_id", referencedColumnName = "id")
    private DocumentType documentType;

    private String numeroDeDocumento;
}
