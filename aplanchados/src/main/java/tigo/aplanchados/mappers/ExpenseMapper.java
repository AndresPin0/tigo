package tigo.aplanchados.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import tigo.aplanchados.dtos.ExpenseDTO;
import tigo.aplanchados.model.Expense;

@Mapper
public interface ExpenseMapper {
    ExpenseMapper INSTANCE = Mappers.getMapper(ExpenseMapper.class);

    @Mapping(source = "person.personPK.documentNumber", target = "personDocumentNumber")
    @Mapping(source = "paymentMethod.code", target = "paymentMethodCode")
    @Mapping(source = "paymentType.code", target = "paymentTypeCode")
    @Mapping(source = "expenseConcept.code", target = "expenseConceptCode")
    @Mapping(source = "user.id", target = "userId")
    ExpenseDTO toDTO(Expense expense);

    @Mapping(source = "personDocumentNumber", target = "person.personPK.documentNumber")
    @Mapping(source = "paymentMethodCode", target = "paymentMethod.code")
    @Mapping(source = "paymentTypeCode", target = "paymentType.code")
    @Mapping(source = "expenseConceptCode", target = "expenseConcept.code")
    @Mapping(source = "userId", target = "user.id")
    Expense toEntity(ExpenseDTO expenseDTO);
}
