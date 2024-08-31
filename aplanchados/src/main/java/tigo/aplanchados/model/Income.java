package tigo.aplanchados.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name="income")
public class Income implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer value;
    private String additionalDetail;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name="person_document_type", referencedColumnName="document_type_id"),
        @JoinColumn(name="person_document_number", referencedColumnName="numeroDeDocumento")
    })
    private Person person;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "payment_method_code")
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "payment_type_code")
    private PaymentType paymentType;

    @ManyToOne
    @JoinColumn(name = "income_concept_code")
    private IncomeConcept incomeConcept;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
