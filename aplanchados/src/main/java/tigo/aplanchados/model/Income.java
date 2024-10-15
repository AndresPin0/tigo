package tigo.aplanchados.model;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="income")
public class Income implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Income(Long id, Integer value, String additionalDetail, Person person, LocalDateTime date,
                  PaymentMethod paymentMethod, PaymentType paymentType, IncomeConcept incomeConcept, User user) {
        this.id = id;
        this.value = value;
        this.additionalDetail = additionalDetail;
        this.person = person;
        this.date = date;
        this.paymentMethod = paymentMethod;
        this.paymentType = paymentType;
        this.incomeConcept = incomeConcept;
        this.user = user;
    }

    public Income() {
        // Auto-generated constructor stub
    }

    private Integer value;
    private String additionalDetail;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name="person_document_type", referencedColumnName="document_type_id"),
        @JoinColumn(name="person_document_number", referencedColumnName="documentNumber")
    })
    private Person person;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime date;

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
