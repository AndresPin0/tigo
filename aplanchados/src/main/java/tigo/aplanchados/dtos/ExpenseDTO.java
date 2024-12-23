package tigo.aplanchados.dtos;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ExpenseDTO {
    private Long id;
    private Long userId;
    private Integer value;
    private String additionalDetail;
    private String personDocumentNumber;
    private String personName;
    private LocalDateTime date;
    private Long paymentMethodCode;
    private Long paymentTypeCode;
    private Long expenseConceptCode;
    
}
