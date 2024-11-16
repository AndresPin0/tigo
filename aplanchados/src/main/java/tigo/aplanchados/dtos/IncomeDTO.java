package tigo.aplanchados.dtos;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class IncomeDTO {
    private Long id;
    private Long userId;
    private Integer value;
    private String additionalDetail;
    private String personDocumentNumber;
    private LocalDateTime date;
    private String personName;
    private Long paymentMethodCode;
    private Long paymentTypeCode;
    private Long incomeConceptCode;

}
